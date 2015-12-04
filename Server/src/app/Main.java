package app;

import utils.socket.SClient;
import utils.socket.Server;
import utils.socket.ServerListener;
import messages.PingRequest;

import java.io.IOException;

public class Main {

    public static Server server = new Server();

    public static void main(String[] args) {
        try {
            System.out.println("Démarrage de l'application ...");

            //TODO: JDBC - Connexion MySQL

            // Configuration du serveur //
            server.addListener(onNewConnection);
            server.listen(3698);

            //TODO>> Debug
            SClient oo = new SClient("127.0.0.1",3698);
            System.out.println(oo.sendResponse(new PingRequest()));
            //TODO<<
        }
        catch (IOException e) {
            System.err.println("Erreur d'initialisation !");
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static ServerListener onNewConnection = (sender, socket) -> {
        // Nouvelle connexion //
        new Client(socket);
    };
}
