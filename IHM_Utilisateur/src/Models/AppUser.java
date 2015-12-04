package Models;

import javafx.scene.control.Alert;
import messages.LoginRequest;
import messages.LoginResponse;
import utils.socket.SClient;

public class AppUser {
    public static User user = null;
    public static SClient sClient = null;


    public static boolean testServerAccess() {
        try {
            AppUser.sClient = new SClient("127.0.0.1", 3698);
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
            return true;
        } else
            return false;
    }
}
