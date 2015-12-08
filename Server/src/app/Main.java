package app;

import dao.DaoConnection;
import utils.socket.Server;
import utils.socket.ServerListener;

public class Main {

    public static Server server = new Server();

    public static void main(String[] args) {
        try {
            System.out.println("Démarrage de l'application ...");

            // Connexion JDBC //
            DaoConnection.getInstance().connect(null,0,null,null,null);

            // Configuration du serveur //
            server.addListener(onNewConnection);
            server.listen(3698);

        } catch (Exception e) {
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
