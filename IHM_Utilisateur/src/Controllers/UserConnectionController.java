package Controllers;

import Models.AppUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserConnectionController {
    @FXML
    public TextField loginTextField;
    @FXML
    public PasswordField userPasswordTextField;
    @FXML
    public Button loginButton;
    private Stage stage;

    public UserConnectionController(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void LoginAction() throws NoSuchAlgorithmException {
        if (AppUser.loginRequest(loginTextField.getText(), (new HexBinaryAdapter()).marshal(MessageDigest.getInstance("SHA-256").digest(userPasswordTextField.getText().getBytes())))) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/MainView.fxml"));
                Stage mainWindowStage = new Stage();
                loader.setController(new MainController(mainWindowStage));
                mainWindowStage.setTitle("SOSIE");
                mainWindowStage.setMaximized(true);
                mainWindowStage.setScene(new Scene(loader.load()));
                mainWindowStage.show();

                this.stage.close();
            } catch (Exception e) {
                e.printStackTrace();

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Impossible d'ouvrir la fênetre principale.");
                alert.showAndWait();
            }
        }
    }
}
