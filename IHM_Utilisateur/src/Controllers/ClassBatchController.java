package Controllers;


import Models.ClassBatch;
import Models.Student;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class ClassBatchController implements Initializable {
    @FXML
    public Label classNameLabel;
    @FXML
    public ListView<Student> studentsListView;
    @FXML
    public Button viewInfoStudentButton;

    private ClassBatch classBatch = null;

    public ClassBatchController(ClassBatch classBatch) {
        this.classBatch = classBatch;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.setClassNameLabel();
        this.setStudentsListView();
    }

    private void setClassNameLabel() {
        this.classNameLabel.setText("Nom de la promotion : " + this.classBatch.toString());
    }

    private void setStudentsListView() {
        this.studentsListView.setItems(FXCollections.observableArrayList(this.classBatch.getStudents()));
    }

    @FXML
    public void viewInfoStudentAction() {

    }
}
