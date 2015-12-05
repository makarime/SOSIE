package Controllers;

import Models.AppUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UserConnectionWindowController {
    public Stage stage;
    @FXML
    public TextField loginTextField;
    @FXML
    public PasswordField userPasswordTextField;
    @FXML
    public Button loginButton;

    @FXML
    public void LoginAction() {
        if (AppUser.loginRequest(loginTextField.getText(), userPasswordTextField.getText())) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/MainWindowView.fxml"));
                Parent root = loader.load();
                MainWindowController mainWindowController = loader.getController();
                Stage mainWindowStage = new Stage();
                mainWindowController.stage = mainWindowStage;
                mainWindowStage.setTitle("SOSIE");
                mainWindowStage.setMaximized(true);
                mainWindowStage.setScene(new Scene(root));
                mainWindowStage.show();

                this.stage.close();
            } catch (Exception e) {
                e.printStackTrace();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Impossible d'ouvrir la fênetre principale.");
                alert.showAndWait();
            }
        }
    }
}
