package utils.socket;

import javax.swing.event.EventListenerList;
import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SClient {
    private EventListenerList listeners = new EventListenerList();
    private DataArrivalThread thread = null;
    private Socket socket;
    private BufferedReader in = null;
    private DataOutputStream out = null;

    private final Charset UTF8_CHARSET = Charset.forName("UTF-8");

    private AtomicLong requestId = new AtomicLong();
    private ConcurrentHashMap<Long, SClientCallback> requestCallback = new ConcurrentHashMap<>();

    public SClient(String host, int port) throws IOException {this.connect(host, port);}
    public SClient(String host, int port, SClientListener listener) throws IOException {this.addListener(listener); this.connect(host, port);}
    public SClient(Socket socket) throws IOException {this.connect(socket);}
    public SClient(Socket socket, SClientListener listener) throws IOException {this.addListener(listener); this.connect(socket);}
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

    private void internalSend(String data) {
        try {
            out.writeBytes(data + "\r\n");
        } catch (IOException e) {
            e.printStackTrace();
            close();
        }
    }

    public void send(String data) {
        if(!isConnected())
            return;
        internalSend("s-1#" + Base64.getEncoder().encodeToString(data.getBytes(UTF8_CHARSET)));
    }

    public void beginSend(String data, SClientCallback callback) {
        if(!isConnected())
            return;
        long id = requestId.getAndIncrement();
        requestCallback.put(id, callback);
        internalSend("s" + id + "#" + Base64.getEncoder().encodeToString(data.getBytes(UTF8_CHARSET)));
    }

    public String sendResponse(String data) {
        StringBuilder sb = new StringBuilder();
        ReentrantLock l = new ReentrantLock();
        Condition c = l.newCondition();
        this.beginSend(data, new SClientCallback() {
            public Lock lock;
            public Condition condition;
            public StringBuilder response;
            @Override
            public void onResult(SClient sender, String result) {
                response.append(result);
                lock.lock();
                condition.signal();
                lock.unlock();
            }
            private SClientCallback init(Lock l, Condition c, StringBuilder sb) {
                lock = l;
                response = sb;
                condition = c;
                return this;
            }
        }.init(l, c, sb));
        l.lock();
        try {
            c.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        l.unlock();
        return sb.toString();
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
                    data = new String(Base64.getDecoder().decode(data), UTF8_CHARSET);
                    try {
                        if(!request && rid != -1) {
                            if(requestCallback.containsKey(rid)) {
                                requestCallback.get(rid).onResult(SClient.this, data);
                                requestCallback.remove(rid);
                            } else
                                System.err.println("Callback " + rid + " introuvable !");
                        } else {
                            // Flux standard //
                            StringWriter response = (rid != -1) ? new StringWriter() : null;
                            fireOnDataArrival(data, response);
                            if(response != null)
                                internalSend("r" + rid + "#" + Base64.getEncoder().encodeToString(response.toString().getBytes(UTF8_CHARSET)));
                        }
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

    public void addListener(SClientListener listener){
        listeners.add(SClientListener.class, listener);
    }

    public void removeListener(SClientListener listener){
        listeners.remove(SClientListener.class, listener);
    }

    private void fireOnConnected() {
        for(SClientListener listener : listeners.getListeners(SClientListener.class)) {
            listener.onConnected(this);
        }
    }

    private void fireOnDataArrival(String data, StringWriter response) {
        for(SClientListener listener : listeners.getListeners(SClientListener.class)) {
            listener.onDataArrival(this, data, response);
        }
    }

    private void fireOnClosed() {
        for(SClientListener listener : listeners.getListeners(SClientListener.class)) {
            listener.onClosed(this);
        }
    }
}
