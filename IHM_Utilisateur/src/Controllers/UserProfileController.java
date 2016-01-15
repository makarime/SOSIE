package Controllers;


import Models.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class UserProfileController implements Initializable {
    @FXML
    private ImageView userProfileImageView;
    @FXML
    private Label userNameLabel;
    @FXML
    private Label userEmailLabel;
    @FXML
    private ChoiceBox<ClassBatch> classBatchesChoiceBox;
    @FXML
    private Button classBatchInfoButton;

    private User user = null;

    public UserProfileController(User user) {
        this.user = user;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.initUserProfileImageView();
        this.initUserNameLabel();
        this.initUserEmailLabel();
        this.initClassBatchesChoiceBox();
    }

    public void initUserProfileImageView() {
        Async.execute(() -> {
            Image image = new Image(new ByteArrayInputStream(this.user.getProfileImageByteArray()));
            Platform.runLater(() -> this.userProfileImageView.setImage(image));
        });
    }

    public void initUserNameLabel() {
        this.userNameLabel.setText(this.user.toString());
    }

    public void initUserEmailLabel() {
        this.userEmailLabel.setText("Email : " + this.user.getEmail());
    }

    public void initClassBatchesChoiceBox() {
        this.classBatchesChoiceBox.converterProperty().setValue(new StringConverter<ClassBatch>() {
            @Override
            public String toString(ClassBatch object) {
                return object.getName();
            }

            @Override
            public ClassBatch fromString(String string) {
                return null;
            }
        });
        this.classBatchInfoButton.disableProperty().bind(this.classBatchesChoiceBox.getSelectionModel().selectedItemProperty().isNull());
        this.classBatchesChoiceBox.setItems(FXCollections.observableArrayList());

        Async.execute(() -> {
            if (this.user.isStudent())
                this.classBatchesChoiceBox.getItems().addAll(((Student) this.user).getCurrentClassBatch());
            else if (this.user.isProfessor())
                this.classBatchesChoiceBox.getItems().addAll(((Professor) this.user).getClassBatches());

            Platform.runLater(() -> this.classBatchesChoiceBox.setValue(this.classBatchesChoiceBox.getItems().get(0)));
        });
    }

    @FXML
    public void classBatchInfoAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/ClassBatchView.fxml"));
            Stage classWindowStage = new Stage();
            loader.setController(new ClassBatchController(this.classBatchesChoiceBox.getValue()));
            classWindowStage.setTitle("Visionneur de promotion");
            classWindowStage.setScene(new Scene(loader.load()));
            classWindowStage.setResizable(false);
            classWindowStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Impossible d'ouvrir la fênetre de visionneur de promotion.");
            alert.showAndWait();
        }
    }
}
