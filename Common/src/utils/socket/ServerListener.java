package utils.socket;

import java.util.EventListener;
import java.net.Socket;

public interface ServerListener extends EventListener {
    void onNewConnection(Server sender, Socket socket);
}
