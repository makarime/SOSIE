package utils.socket;

import javax.swing.event.EventListenerList;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private EventListenerList listeners = new EventListenerList();
    private ListenThread thread = null;
    public ServerSocket socket = null;

    public Server(ServerListener listener) {this.addListener(listener);}
    public Server(ServerListener listener, int port) throws IOException {this.addListener(listener); this.listen(port);}
    public Server() {}

    public void listen(int port) throws IOException {
        if(thread != null && thread.isAlive())
            return;
        socket = new ServerSocket(port);
        thread = new ListenThread();
        thread.start();
    }

    public void stop () throws IOException {
        if(!thread.isAlive())
            return;
        thread.interrupt();
        socket.close();
        System.out.println("[Server] Arret de l'ecoute");
    }

    private class ListenThread extends Thread {
        @Override
        public void run() {
            System.out.println("[Server] Ecoute sur le port : " + socket.getLocalPort());
            while(true) {
                try {
                    Socket s = socket.accept();
                    System.out.println("[Server] Nouvelle connexion : " + s.getRemoteSocketAddress());
                    fireOnNewConnection(s);
                } catch (IOException e) {
                    if(Thread.currentThread().isInterrupted()) break;
                    e.printStackTrace();
                }
            }
        }
    }

    public void addListener(ServerListener listener){
        listeners.add(ServerListener.class, listener);
    }

    public void removeListener(ServerListener listener){
        listeners.remove(ServerListener.class, listener);
    }

    private void fireOnNewConnection(Socket socket) {
        for(ServerListener listener : listeners.getListeners(ServerListener.class)) {
            listener.onNewConnection(this, socket);
        }
    }
}
