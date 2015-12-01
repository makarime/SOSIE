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

    public Stage stage = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.setProfileImageViewInitialization();
        this.setEmailTextFieldsInitialization();
    }

    private void setProfileImageViewInitialization()
    {
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
        if ((!this.emailFirstPart.getText().isEmpty())&&(!this.emailSecondPart.getText().isEmpty())&&(!this.emailThirdPart.getText().isEmpty()))
        {
            AppUser.user.setProfileImage(this.profileImageView.getImage());
            AppUser.user.setEmail(this.emailFirstPart.getText() + "@" + this.emailSecondPart.getText() + "." + this.emailThirdPart.getText());

            if (!this.passwordField1.getText().isEmpty())
            {
                //TODO
            }

            this.stage.close();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Un des champs de l'addresse mail est vide.");
            alert.showAndWait();
        }
    }
}
