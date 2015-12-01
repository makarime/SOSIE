package Models.DataBaseModels;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Hashtable;

public class DataBase {
    // Fields
    //TODO config data base access
    public static String dataBaseUrl = null;
    public static String dataBaseUser = null;
    public static String dataBaseUserPassword = null;
    public static Connection dataBaseConnection = null;
    public static boolean isConnected = false;

    public static Hashtable<Integer, Professor> professorsHashtable = new Hashtable<>();
    public static Hashtable<Integer, Module> modulesHashtable = new Hashtable<>();

    /* Test the database connectivity */
    public static void ConnectToDataBase() {
        DataBase.isConnected = true;

        try {
            DataBase.dataBaseConnection = DriverManager.getConnection(DataBase.dataBaseUrl, DataBase.dataBaseUser, DataBase.dataBaseUserPassword);
            DataBase.isConnected = true;
        } catch (SQLException e) {
            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de connexion à la base de données");
            alert.setHeaderText(null);
            alert.setContentText("Impossible de se connecter à la base de données.");
            alert.showAndWait();
        }
    }

    public static void CloseDataBaseConnection() {
        if (DataBase.dataBaseConnection != null) //Avoid null error caused by DataBase.dataBaseConnection
        {
            try {
                DataBase.dataBaseConnection.close();
            } catch (SQLException ignored) {
            } //ignore error
        }
    }

    public static void setProfessors() {
        Professor p1 = new Professor(0, "Pierre", "Richard");
        DataBase.professorsHashtable.put(p1.getId(), p1);
    }

    public static void loadModules(){
        Module module = new Module(0, "toto");
        DataBase.modulesHashtable.put(module.getId(), module);
    }
}
