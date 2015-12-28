package Models;

import Models.proxy.SocketProxy;
import javafx.scene.control.Alert;
import messages.LoginRequest;
import messages.LoginResponse;
import utils.socket.SClient;

import java.net.URL;
import java.net.URLConnection;

public class AppUser {
    public static User user = null;
    public static SClient sClient = null;

    public static boolean TestConnectivity() {
        try {
            final URL url = new URL("http://www.google.com");
            final URLConnection connection = url.openConnection();
            connection.setConnectTimeout(1000);
            connection.connect();
            return true;
        } catch (Exception e) {
            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur de connexion internet");
            alert.setHeaderText(null);
            alert.setContentText("Aucune connexion internet détéctée.");
            alert.showAndWait();
            return false;
        }
    }

    public static boolean testServerAccess() {
        try {
            AppUser.sClient = new SClient("127.0.0.1", 3698);
            DataBase.currentProxy = new SocketProxy(AppUser.sClient);
            return true;
        } catch (Exception e) {
            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur de connexion au server");
            alert.setHeaderText(null);
            alert.setContentText("Aucun access au serveur.");
            alert.showAndWait();

            return false;
        }
    }

    public static boolean loginRequest(String userName, String password) {
        LoginResponse loginResponse = ((LoginResponse) AppUser.sClient.sendRequest(new LoginRequest(userName, password)));

        if (loginResponse.getSuccess()) {
            AppUser.user = loginResponse.getUser();
            DataBase.users.put(AppUser.user.getUserId(), AppUser.user);
            if (AppUser.user.isStudent())
                DataBase.students.put(((Student) AppUser.user).getStudentId(), (Student) AppUser.user);
            else if (AppUser.user.isProfessor())
                DataBase.professors.put(((Professor) AppUser.user).getProfessorId(), (Professor) AppUser.user);

            return true;
        } else
            return false;
    }
}
