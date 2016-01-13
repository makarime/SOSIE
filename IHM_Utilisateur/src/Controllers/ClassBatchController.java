package Controllers;


import Models.Async;
import Models.ClassBatch;
import Models.Professor;
import Models.Student;
import javafx.application.Platform;
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
    public Label classBatchNameLabel;
    @FXML
    public Label professorInChargeLabel;
    @FXML
    public Button professorInChargeInfoButton;
    @FXML
    public ListView<Student> studentsListView;
    @FXML
    public Button studentInfoButton;
    @FXML
    public Button euInfoButton;

    private ClassBatch classBatch = null;

    public ClassBatchController(ClassBatch classBatch) {
        this.classBatch = classBatch;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.initClassNameLabel();
        this.initProfessorInChargeLabel();
        this.initStudentsListView();
    }

    private void initClassNameLabel() {
        this.classBatchNameLabel.setText("Nom de la promotion : " + this.classBatch.toString());
    }

    private void initProfessorInChargeLabel() {
        this.professorInChargeInfoButton.disableProperty().bind(this.professorInChargeLabel.textProperty().isEmpty());
        this.professorInChargeInfoButton.setText("Chargement...");

        Async.execute(() -> {
            Professor professor = this.classBatch.getProfessorInCharge();
            Platform.runLater(() -> {
                this.professorInChargeLabel.setText(professor.toString());
                this.professorInChargeInfoButton.setText("Voir détails");
            });
        });
    }

    private void initStudentsListView() {
        this.studentInfoButton.disableProperty().bind(this.studentsListView.getSelectionModel().selectedItemProperty().isNull());
        this.studentInfoButton.setText("Chargement...");
        this.studentsListView.setItems(FXCollections.observableArrayList());

        Async.execute(() -> {
            this.studentsListView.getItems().addAll(this.classBatch.getStudents());
            Platform.runLater(() -> this.studentInfoButton.setText("Voir détails"));
        });
    }

    @FXML
    public void professorInChargeInfoAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/UserProfileView.fxml"));
            Stage mainWindowStage = new Stage();
            loader.setController(new UserProfileController(this.classBatch.getProfessorInCharge()));
            mainWindowStage.setTitle("Profile utilisateur");
            mainWindowStage.setScene(new Scene(loader.load()));
            mainWindowStage.setResizable(false);
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
    public void studentInfoAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/UserProfileView.fxml"));
            Stage mainWindowStage = new Stage();
            loader.setController(new UserProfileController(this.studentsListView.getSelectionModel().getSelectedItem()));
            mainWindowStage.setTitle("Profile utilisateur");
            mainWindowStage.setResizable(false);
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
    public void euInfoAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/EusSubjectsView.fxml"));
            Stage mainWindowStage = new Stage();
            loader.setController(new EusSubjectsViewController(this.classBatch));
            mainWindowStage.setTitle("UEs - Matières visionneur");
            mainWindowStage.setResizable(false);
            mainWindowStage.setScene(new Scene(loader.load()));
            mainWindowStage.show();
        } catch (Exception e) {
            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Impossible d'ouvrir le visionneur d'UEs - Matières.");
            alert.showAndWait();
        }
    }
}
