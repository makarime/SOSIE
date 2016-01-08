package Controllers;


import Models.Async;
import Models.ClassBatch;
import Models.Professor;
import Models.Student;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ClassBatchController implements Initializable {
    @FXML
    public Label classNameLabel;
    @FXML
    public Label professorInChargeLabel;
    @FXML
    public Button professorInChargeInfoButton;
    @FXML
    public ListView<Student> studentsListView;
    @FXML
    public Button viewInfoStudentButton;
    @FXML
    public Button viewEUButton;

    private ClassBatch classBatch = null;

    public ClassBatchController(ClassBatch classBatch) {
        this.classBatch = classBatch;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.setClassNameLabel();
        this.setProfessorInChargeLabel();
        this.setStudentsListView();
    }

    private void setClassNameLabel() {
        this.classNameLabel.setText("Nom de la promotion : " + this.classBatch.toString());
    }

    private void setProfessorInChargeLabel() {
        this.professorInChargeInfoButton.setDisable(true);
        this.professorInChargeInfoButton.setText("Chargement...");

        Async.execute(() -> {
            Professor professor = this.classBatch.getProfessorInCharge();
            ObjectProperty<String> stringObjectPropertyLabel = new SimpleObjectProperty<>();
            ObjectProperty<String> stringObjectPropertyButton = new SimpleObjectProperty<>();
            Platform.runLater(() -> {
                this.professorInChargeLabel.textProperty().bind(stringObjectPropertyLabel);
                this.professorInChargeInfoButton.textProperty().bind(stringObjectPropertyButton);
                stringObjectPropertyLabel.setValue("Professeur en charge : " + professor.toString());
                stringObjectPropertyButton.setValue("Voir détails");
            });
            this.professorInChargeInfoButton.setDisable(false);
        });
    }

    private void setStudentsListView() {
        this.viewInfoStudentButton.setDisable(true);
        this.viewInfoStudentButton.setText("Chargement...");

        Async.execute(() -> {
            this.studentsListView.setItems(FXCollections.observableArrayList());
            this.studentsListView.getItems().addAll(this.classBatch.getStudents());
            this.studentsListView.getSelectionModel().select(0);
            ObjectProperty<String> stringObjectProperty = new SimpleObjectProperty<>();
            Platform.runLater(() -> {
                this.viewInfoStudentButton.textProperty().bind(stringObjectProperty);
                stringObjectProperty.setValue("Voir détails");
            });
            this.viewInfoStudentButton.setDisable(false);
        });
    }

    @FXML
    public void viewProfessorInChargeInfoAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/UserProfileView.fxml"));
            Stage mainWindowStage = new Stage();
            loader.setController(new UserProfileController(this.classBatch.getProfessorInCharge()));
            mainWindowStage.setTitle("Profile utilisateur");
            mainWindowStage.setScene(new Scene(loader.load()));
            mainWindowStage.show();
        } catch (Exception e) {
            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Impossible d'ouvrir la de profile d'utilisateur.");
            alert.showAndWait();
        }
    }

    @FXML
    public void viewInfoStudentAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/UserProfileView.fxml"));
            Stage mainWindowStage = new Stage();
            loader.setController(new UserProfileController(this.studentsListView.getSelectionModel().getSelectedItem()));
            mainWindowStage.setTitle("Profile utilisateur");
            mainWindowStage.setScene(new Scene(loader.load()));
            mainWindowStage.show();
        } catch (Exception e) {
            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Impossible d'ouvrir la de profile d'utilisateur.");
            alert.showAndWait();
        }
    }

    @FXML
    public void viewEUAction() {

    }
}
