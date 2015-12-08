package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoConnection {
    public Connection jdbc;

    private static DaoConnection instance = null;
    public static DaoConnection getInstance() {
        if(instance == null)
            instance = new DaoConnection();
        return instance;
    }

    public void connect(String host, int port, String user, String password, String db) throws ClassNotFoundException, SQLException {
        // Enregistre le driver //
        Class.forName("com.mysql.jdbc.Driver");

        // Connexion à la base //
        jdbc = DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s", host, port, db), user, password);
        System.out.println(String.format("[JDBC] Connecté sur %s:%s", host, port));
    }

    public void close() {
        // TODO ...
    }


}
