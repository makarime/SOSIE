package utils.socket;

import utils.socket.interfaces.SClientClosed;
import utils.socket.interfaces.SClientConnected;
import utils.socket.interfaces.SClientDataArrival;
import utils.socket.message.NullResponse;
import utils.socket.message.StringMessage;
import utils.EventManagerList;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Base64;
import java.util.EventListener;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static utils.Serialization.deserialize;
import static utils.Serialization.serialize;

public class SClient {
    private final EventManagerList listeners = new EventManagerList();
    private DataArrivalThread thread = null;
    private Socket socket;
    private BufferedReader in = null;
    private DataOutputStream out = null;

    private final AtomicLong requestId = new AtomicLong();
    private final ConcurrentHashMap<Long, SClientCallback> requestCallback = new ConcurrentHashMap<>();

    public SClient(String host, int port) throws IOException {this.connect(host, port);}
    public SClient(String host, int port, EventListener listener) throws IOException {this.addListener(listener); this.connect(host, port);}
    public SClient(Socket socket) throws IOException {this.connect(socket);}
    public SClient(Socket socket, EventListener listener) throws IOException {this.addListener(listener); this.connect(socket);}
    public SClient() {}

    public void connect(String host, int port) throws IOException {
        this.connect(new Socket(host, port));
    }

    public void connect(Socket socket) throws IOException {
        if(isConnected())
            return;
        this.socket = socket;

        // Ouverture des flux //
        in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        out = new DataOutputStream(this.socket.getOutputStream());

        // Demarrage du thread de reception des données //
        thread = new DataArrivalThread();
        thread.start();

        // RaiseEvent //
        fireOnConnected();
    }

    public void close() {
        if(!isConnected())
            return;
        // Cloture du thread //
        thread.interrupt();
        // Fermeture du socket //
        try {
            socket.close();
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        fireOnClosed();
    }

    private void internalSend(String header, IMessage message) {
        try {
            //TODO: NonUrgent: Ici pour chiffrer le flux (Encode64 << Chiffrement << Serialisation << IMessage)
            out.write((header + "#" + Base64.getEncoder().encodeToString(serialize(message)) + "\r\n").getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            close();
        } catch (Exception e) { //Serialization error
            e.printStackTrace();
        }
    }

    public void send(IMessage msg) {
        if(!isConnected())
            return;
        internalSend("s-1", msg);
    }

    public void sendRequest(IMessage msg, SClientCallback callback) {
        if(!isConnected())
            return;
        long id = requestId.getAndIncrement();
        requestCallback.put(id, callback);
        internalSend("s" + id, msg);
    }

    public IMessage sendRequest(IMessage msg) { //TODO: A Voir si on fait un throw si ErrorMessage ?
        AtomicReference<IMessage> result = new AtomicReference<>(); //TODO: Autre classe pour faire des references?
        ReentrantLock l = new ReentrantLock();
        Condition c = l.newCondition();
        this.sendRequest(msg, new SClientCallback() {
            public Lock lock;
            public Condition condition;
            public AtomicReference<IMessage> response;
            @Override
            public void onResult(SClient sender, IMessage result) {
                response.set(result);
                lock.lock();
                condition.signal();
                lock.unlock();
            }
            private SClientCallback init(Lock l, Condition c, AtomicReference<IMessage> r) {
                lock = l;
                response = r;
                condition = c;
                return this;
            }
        }.init(l, c, result));
        l.lock();
        try {
            c.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        l.unlock();
        return result.get();
    }

    @Deprecated
    public void send(String data) {
        send(new StringMessage(data));
    }

    @Deprecated
    public void sendRequest(String data, SClientCallback callback) {
        sendRequest(new StringMessage(data), callback);
    }

    @Deprecated
    public String sendRequest(String data) {
        IMessage msg = sendRequest(new StringMessage(data));
        return (msg instanceof StringMessage ? ((StringMessage)msg).get() : null);
    }

    public Socket getSocket() {
        return socket;
    }

    public boolean isConnected() { return socket != null && !socket.isClosed() && socket.isConnected(); }

    private class DataArrivalThread extends Thread {
        @Override
        public void run() {
            try {
                while(true) {
                    String data = in.readLine();
                    if(Thread.currentThread().isInterrupted() || data == null || !data.contains("#")) break; //data==null >> Remote connection closed
                    boolean request = data.substring(0,1).equals("s");
                    long rid = Long.parseLong(data.substring(1, data.indexOf("#")));
                    data = data.substring(data.indexOf("#") + 1);
                    try {
                        //TODO: NonUrgent: Ici pour dechiffrer le flux (IMessage << Deserialisation << Decode64 << Dechiffrement << Flux)
                        IMessage msg = ((IMessage) deserialize(Base64.getDecoder().decode(data)));
                        new Thread(() -> {
                            try {
                                if (!request && rid != -1) {
                                    if (requestCallback.containsKey(rid)) {
                                        requestCallback.get(rid).onResult(SClient.this, msg);
                                        requestCallback.remove(rid);
                                    } else
                                        System.err.println("Callback " + rid + " introuvable !");
                                } else {
                                    // Flux standard //
                                    DataArrivalEvent e = new DataArrivalEvent<>(msg, rid != -1);
                                    fireOnMessageArrival(e);
                                    fireOnDataArrival(e);
                                    if (e.isRequest() && e.getResponse() == null)
                                        e.setResponse(new NullResponse());
                                    if (e.getResponse() != null)
                                        internalSend("r" + rid, e.getResponse());
                                }
                            } catch (Exception e) { // Probleme traitement du message
                                e.printStackTrace();
                            }
                        }).start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                if (!Thread.currentThread().isInterrupted())
                    e.printStackTrace();
            } finally {
                close();
            }
        }
    }

    public <T extends IMessage> void addMessageArrival(Class<T> clazz, IMessageArrival<T> callback) {
        listeners.add(new SClientMessageArrivalListener<>(clazz, callback));
    }

    public <T extends IMessage> void removeMessageArrival(Class<T> clazz, IMessageArrival<T> callback) {
        SClientMessageArrivalListener obj = null;
        for(SClientMessageArrivalListener listener : listeners.getListeners(SClientMessageArrivalListener.class)) {
            if(listener.clazz == clazz && listener.callback == callback) {
                obj = listener;
                break;
            }
        }
        if(obj != null) {
            listeners.remove(obj);
        }
    }

    public void addListener(EventListener listener){
        listeners.add(listener);
    }

    public void removeListener(EventListener listener){
        listeners.remove(listener);
    }

    private void fireOnMessageArrival(DataArrivalEvent event) {
        final Class<?> clazz = event.getMessageClass();
        for(SClientMessageArrivalListener listener : listeners.getListeners(SClientMessageArrivalListener.class)) {
            if(listener.clazz == clazz) {
                listener.callback.onMessageArrival(this, event);
            }
        }
    }

    private void fireOnConnected() {
        for(SClientConnected listener : listeners.getListeners(SClientConnected.class)) {
            listener.onConnected(this);
        }
    }

    private void fireOnDataArrival(DataArrivalEvent event) {
        for(SClientDataArrival listener : listeners.getListeners(SClientDataArrival.class)) {
            listener.onDataArrival(this, event);
        }
    }

    private void fireOnClosed() {
        for(SClientClosed listener : listeners.getListeners(SClientClosed.class)) {
            listener.onClosed(this);
        }
    }
}
