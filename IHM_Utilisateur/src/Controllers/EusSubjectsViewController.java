package Controllers;


import Models.Async;
import Models.ClassBatch;
import Models.Eu;
import Models.Subject;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class EusSubjectsViewController implements Initializable {
    private ClassBatch classBatch = null;
    @FXML
    public ChoiceBox<Eu> eusChoiceBox;
    @FXML
    public ChoiceBox<Subject> subjectsChoiceBox;
    @FXML
    public Label nbHoursLabel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public EusSubjectsViewController(ClassBatch classBatch) {
        this.classBatch = classBatch;

        this.initEusChoiceBox();
    }

    private void initEusChoiceBox() {
        this.eusChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            EusSubjectsViewController.this.subjectsChoiceBox.setDisable(true);
            EusSubjectsViewController.this.subjectsChoiceBox.getItems().clear();
            Async.execute(() -> {
                EusSubjectsViewController.this.subjectsChoiceBox.getItems().addAll(newValue.getSubjects());
                Platform.runLater(() -> {
                    EusSubjectsViewController.this.subjectsChoiceBox.setDisable(false);
                    EusSubjectsViewController.this.subjectsChoiceBox.getSelectionModel().select(0);
                });
            });
        });

        this.eusChoiceBox.setDisable(true);
        this.eusChoiceBox.setItems(FXCollections.observableArrayList());
        Async.execute(() -> {
            this.eusChoiceBox.getItems().addAll(this.classBatch.getClasss().getEus());
            Platform.runLater(() -> {
                this.eusChoiceBox.setDisable(false);
                this.eusChoiceBox.getSelectionModel().select(0);
                this.initSubjectsChoiceBox();
            });
        });

    }

    private void initSubjectsChoiceBox() {
        this.subjectsChoiceBox.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            EusSubjectsViewController.this.nbHoursLabel.setText(Integer.toString(newValue.getNbHours()));
        }));

        this.subjectsChoiceBox.setDisable(true);
        this.subjectsChoiceBox.setItems(FXCollections.observableArrayList());
        Async.execute(() -> {
            this.subjectsChoiceBox.getItems().addAll(this.eusChoiceBox.getSelectionModel().getSelectedItem().getSubjects());
            Platform.runLater(() -> {
                this.subjectsChoiceBox.setDisable(false);
                this.subjectsChoiceBox.getSelectionModel().select(0);
            });
        });
    }
}
