package utils.socket;

import java.net.Socket;
import java.util.EventListener;

public interface ServerListener extends EventListener {
    void onNewConnection(Server sender, Socket socket);
}
