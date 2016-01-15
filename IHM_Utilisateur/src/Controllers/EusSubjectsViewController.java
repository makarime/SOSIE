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
import javafx.util.StringConverter;

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
        this.initSubjectsChoiceBox();
        this.initEusChoiceBox();
    }

    public EusSubjectsViewController(ClassBatch classBatch) {
        this.classBatch = classBatch;
    }

    private void initEusChoiceBox() {
        this.eusChoiceBox.converterProperty().setValue(new StringConverter<Eu>() {
            @Override
            public String toString(Eu object) {
                return object.getName();
            }

            @Override
            public Eu fromString(String string) {
                return null;
            }
        });
        this.eusChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            Async.execute(() -> {
                EusSubjectsViewController.this.subjectsChoiceBox.getItems().addAll(newValue.getSubjects());
                Platform.runLater(() -> EusSubjectsViewController.this.subjectsChoiceBox.getSelectionModel().select(0));
            });
        });

        this.eusChoiceBox.setItems(FXCollections.observableArrayList());
        Async.execute(() -> {
            this.eusChoiceBox.getItems().addAll(this.classBatch.getClasss().getEus());
            Platform.runLater(() -> this.eusChoiceBox.getSelectionModel().select(0));
        });

    }

    private void initSubjectsChoiceBox() {
        this.subjectsChoiceBox.converterProperty().setValue(new StringConverter<Subject>() {
            @Override
            public String toString(Subject object) {
                return object.getName();
            }

            @Override
            public Subject fromString(String string) {
                return null;
            }
        });
        this.subjectsChoiceBox.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            EusSubjectsViewController.this.nbHoursLabel.setText(Integer.toString(newValue.getNbHours()));
        }));
        this.subjectsChoiceBox.setItems(FXCollections.observableArrayList());
    }
}
