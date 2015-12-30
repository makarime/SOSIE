package app;

import Models.DataBase;
import dao.DaoConnection;
import models.proxy.DaoProxy;
import utils.socket.Server;
import utils.socket.ServerListener;

public class Main {

    public static Server server = new Server();

    public static void main(String[] args) {
        try {
            System.out.println("Démarrage de l'application ...");

            // Connexion JDBC //
            DaoConnection.getInstance().connect("sliverkin.fr", 3306, "arlo", "IstyArlo", "arlo3");

            // Change le proxy par defaut definie par les models //
            DataBase.currentProxy = new DaoProxy();

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
