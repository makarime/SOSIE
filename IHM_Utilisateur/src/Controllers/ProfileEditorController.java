package Controllers;


import Models.AppUser;
import Models.Async;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import messages.*;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

public class ProfileEditorController implements Initializable {
    @FXML
    public ImageView profileImageView;
    @FXML
    public Button changePictureButton;
    @FXML
    public TextField emailFirstPart;
    @FXML
    public TextField emailSecondPart;
    @FXML
    public TextField emailThirdPart;
    @FXML
    public PasswordField oldPasswordField;
    @FXML
    public PasswordField passwordField1;
    @FXML
    public PasswordField passwordField2;
    @FXML
    public Button validateButton;

    private Stage stage = null;
    private boolean profileImageChanged = false;
    private Image oldProfileImage = null;
    private File newProfileImageFile = null;

    public ProfileEditorController(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.setProfileImageViewInitialization();
        this.setEmailTextFieldsInitialization();
    }

    private void setProfileImageViewInitialization() {
        this.changePictureButton.setDisable(true);
        this.changePictureButton.setText("Chargement...");
        Async.execute(() -> {
            Image image = AppUser.user.getProfileImage();
            Platform.runLater(() -> {
                this.profileImageView.setImage(image);
                this.changePictureButton.setDisable(false);
                this.changePictureButton.setText("Changer");
            });
        });
    }

    private void setEmailTextFieldsInitialization() {
        this.validateButton.setDisable(true);
        this.validateButton.setText("Chargement...");
        Async.execute(() -> {
            String[] ss = AppUser.user.getEmail().split("@");
            String[] ss2 = ss[1].split("\\.");
            Platform.runLater(() -> {
                this.emailFirstPart.setText(ss[0]);
                this.emailSecondPart.setText(ss2[0]);
                this.emailThirdPart.setText(ss2[1]);
                this.validateButton.setDisable(false);
                this.validateButton.setText("Valider");
            });
        });
    }

    /* Actions */

    public void changeProfileImageAction() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisissez une image");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Toutes les image", "*.*"), new FileChooser.ExtensionFilter("jpg", "*.jpg"), new FileChooser.ExtensionFilter("png", "*.png"));
        File file = fileChooser.showOpenDialog(this.stage);

        if (file == null)
            return;

        this.profileImageChanged = true;
        this.oldProfileImage = this.profileImageView.getImage();
        this.newProfileImageFile = file;
        this.profileImageView.setImage(new Image("file:" + file.getPath()));
    }

    public void validateAction() throws NoSuchAlgorithmException {
        this.changeEmail();
        this.changePassword();
        this.changeProfileImage();
    }

    private void changeEmail() {
        if ((!this.emailFirstPart.getText().isEmpty()) && (!this.emailSecondPart.getText().isEmpty()) && (!this.emailThirdPart.getText().isEmpty())) {
            String email = this.emailFirstPart.getText() + "@" + this.emailSecondPart.getText() + "." + this.emailThirdPart.getText();

            if (!email.equals(AppUser.user.getEmail())) {
                ChangeUserEmailResponse changeUserEmailResponse = ((ChangeUserEmailResponse) AppUser.sClient.sendRequest(new ChangeUserEmailRequest(email)));

                if (changeUserEmailResponse.getResult()) {
                    AppUser.user.setEmail(email);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Votre adresse mail a bien  été changé.");
                    alert.showAndWait();
                }
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

    private void changePassword() throws NoSuchAlgorithmException {
        if (!(this.oldPasswordField.getText().isEmpty()) && (!this.passwordField1.getText().isEmpty())) {
            if (this.passwordField2.getText().equals(this.passwordField1.getText())) {
                HexBinaryAdapter hexBinaryAdapter = new HexBinaryAdapter();
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                ChangeUserPasswordResponse changeUserPasswordResponse = ((ChangeUserPasswordResponse) AppUser.sClient.sendRequest(new ChangeUserPasswordRequest(hexBinaryAdapter.marshal(messageDigest.digest(this.oldPasswordField.getText().getBytes())), hexBinaryAdapter.marshal(messageDigest.digest(this.passwordField1.getText().getBytes())))));

                if (changeUserPasswordResponse.getResult()) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Votre mot de passe a bien  été changé.");
                    alert.showAndWait();
                } else {
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

    private void changeProfileImage() {
        if (this.profileImageChanged) {
            try {
                ChangeUserProfileImageResponse changeUserProfileImageResponse = ((ChangeUserProfileImageResponse) AppUser.sClient.sendRequest(new ChangeUserProfileImageRequest(Files.readAllBytes(this.newProfileImageFile.toPath()))));

                if (changeUserProfileImageResponse.getResult()) {
                    AppUser.user.setProfileImage(this.profileImageView.getImage());
                    this.profileImageChanged = false;
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Votre image de profile a bien  été changé.");
                    alert.showAndWait();
                } else {
                    this.profileImageView.setImage(this.oldProfileImage);
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Une erreur est survenue lors du changement de l'image sur le serveur.");
                    alert.showAndWait();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
