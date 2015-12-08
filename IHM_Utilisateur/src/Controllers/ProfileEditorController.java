package Controllers;


import Models.AppUser;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import messages.ChangeUserEmailRequest;
import messages.ChangeUserEmailResponse;
import messages.ChangeUserPasswordRequest;
import messages.ChangeUserPasswordResponse;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileEditorController implements Initializable {
    @FXML
    public ImageView profileImageView;
    @FXML
    public TextField emailFirstPart;
    @FXML
    public TextField emailSecondPart;
    @FXML
    public TextField emailThirdPart;
    @FXML
    public PasswordField passwordField1;
    @FXML
    public PasswordField passwordField2;

    private Stage stage = null;

    public ProfileEditorController(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.setProfileImageViewInitialization();
        this.setEmailTextFieldsInitialization();
    }

    private void setProfileImageViewInitialization() {
        this.profileImageView.setImage(AppUser.user.getProfileImage());
    }

    private void setEmailTextFieldsInitialization() {
        String[] ss = AppUser.user.getEmail().split("@");
        String[] ss2 = ss[1].split("\\.");
        this.emailFirstPart.setText(ss[0]);
        this.emailSecondPart.setText(ss2[0]);
        this.emailThirdPart.setText(ss2[1]);
    }

    /* Actions */

    public void changeProfileImageAction() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisissez une image");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Toutes les image", "*.*"), new FileChooser.ExtensionFilter("jpg", "*.jpg"), new FileChooser.ExtensionFilter("png", "*.png"));
        File file = fileChooser.showOpenDialog(this.stage);

        if (file == null)
            return;

        this.profileImageView.setImage(new Image("file:" + file.getPath()));
    }

    public void validateAction() {
        this.changeEmail();
        this.changePassword();
    }

    private void changeEmail() {
        if ((!this.emailFirstPart.getText().isEmpty()) && (!this.emailSecondPart.getText().isEmpty()) && (!this.emailThirdPart.getText().isEmpty())) {
            String email = this.emailFirstPart.getText() + "@" + this.emailSecondPart.getText() + "." + this.emailThirdPart.getText();

            if (!email.equals(AppUser.user.getEmail())) {
                ChangeUserEmailResponse changeUserEmailResponse = ((ChangeUserEmailResponse) AppUser.sClient.sendRequest(new ChangeUserEmailRequest(AppUser.user.getId(), email)));

                if (changeUserEmailResponse.getResult())
                    AppUser.user.setEmail(email);
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Une erreur est survenue lors du changement d'adresse mail dans la base de données.");
                    alert.showAndWait();
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Un des champs de l'addresse mail est vide.");
            alert.showAndWait();
        }
    }

    private void changePassword() {
        if (!this.passwordField1.getText().isEmpty()) {
            if (this.passwordField2.getText().equals(this.passwordField1.getText())) {
                ChangeUserPasswordResponse changeUserPasswordResponse = ((ChangeUserPasswordResponse) AppUser.sClient.sendRequest(new ChangeUserPasswordRequest(AppUser.user.getId(), this.passwordField1.getText())));

                if (!changeUserPasswordResponse.getResult()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Une erreur est survenue lors du changement du mot de passe dans la base de données.");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Les mots de passe rentrés ne corresponde pas.");
                alert.showAndWait();
            }
        }
    }
}
