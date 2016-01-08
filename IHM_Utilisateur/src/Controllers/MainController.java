package Controllers;

import Models.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    /* Fields */

    @FXML
    public Label appUserNameLabel;
    @FXML
    public ImageView profileImageView;
    @FXML
    public ChoiceBox<ClassBatch> classBatchesChoiceBox;
    @FXML
    public Button detailsClassBatchButton;
    @FXML
    public DatePicker datePicker;
    @FXML
    public Label weekSpanLabel;
    @FXML
    public Button weekBeforeButton;
    @FXML
    public Label mondayLabel;
    @FXML
    public Label tuesdayLabel;
    @FXML
    public Label wednesdayLabel;
    @FXML
    public Label thursdayLabel;
    @FXML
    public Label fridayLabel;
    @FXML
    public HBox mondayHBox;
    @FXML
    public HBox tuesdayHBox;
    @FXML
    public HBox wednesdayHBox;
    @FXML
    public HBox thursdayHBox;
    @FXML
    public HBox fridayHBox;

    private Stage stage = null;
    private ArrayList<Week> weeks = null;
    private int weekOffset;

    public MainController(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.weeks = new ArrayList<>();
        this.weekOffset = 0;
        this.weeks.add(new Week(0));

        this.setInitializationUI();
        this.setUI();
    }

    private void setProfileTitledPane() {
        this.appUserNameLabel.setText(AppUser.user.getName());
        this.profileImageView.setImage(AppUser.user.getProfileImage());
    }

    private void setDatePicker() {
        this.datePicker.setValue(LocalDateTime.ofInstant(AppCalendar.currentDate.toInstant(), ZoneId.systemDefault()).plusDays(1).toLocalDate());
        Callback<DatePicker, DateCell> dayCellFactory = dp -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if ((item.getDayOfWeek() == DayOfWeek.SATURDAY) || (item.getDayOfWeek() == DayOfWeek.SUNDAY) || (AppCalendar.daysBetween(AppCalendar.currentDate, item) <= 0))
                    setDisable(true);
            }
        };
        this.datePicker.setDayCellFactory(dayCellFactory);
    }

    private void setClassBatchesChoiceBox() {
        if (AppUser.user.isStudent()) {
            this.classBatchesChoiceBox.setItems(FXCollections.observableArrayList(((Student) AppUser.user).getCurrentClassBatch()));
            this.classBatchesChoiceBox.setValue(this.classBatchesChoiceBox.getItems().get(0));
        } else if (AppUser.user.isProfessor()) {
            this.classBatchesChoiceBox.setItems(FXCollections.observableArrayList(((Professor) AppUser.user).getClassBatches()));
            this.classBatchesChoiceBox.setValue(this.classBatchesChoiceBox.getItems().get(0));
        }
    }

    private void setInitializationUI() {
        this.setDatePicker();
        this.setProfileTitledPane();
        this.setClassBatchesChoiceBox();
    }

    private void setDisableWeekBeforeButton() {
        if (this.weekOffset == 0)
            this.weekBeforeButton.setDisable(true);
        else
            this.weekBeforeButton.setDisable(false);
    }

    private void setDayLabels() {
        this.mondayLabel.setText("Lundi\n" + this.weeks.get(this.weekOffset).getDay(0).getDateToString());
        this.tuesdayLabel.setText("Mardi\n" + this.weeks.get(this.weekOffset).getDay(1).getDateToString());
        this.wednesdayLabel.setText("Mercredi\n" + this.weeks.get(this.weekOffset).getDay(2).getDateToString());
        this.thursdayLabel.setText("Jeudi\n" + this.weeks.get(this.weekOffset).getDay(3).getDateToString());
        this.fridayLabel.setText("Vendredi\n" + this.weeks.get(this.weekOffset).getDay(4).getDateToString());
    }

    private void setUI() {
        this.weekSpanLabel.setText(this.weeks.get(this.weekOffset).getSpanWeekString());
        this.setDisableWeekBeforeButton();
        this.setDayLabels();
    }


    /* Actions */
    @FXML
    public void logOutAction() {
        AppUser.user = null;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/UserConnectionView.fxml"));
            Stage userConnectionWindowStage = new Stage();
            loader.setController(new UserConnectionController(userConnectionWindowStage));
            userConnectionWindowStage.setTitle("Connexion");
            userConnectionWindowStage.setScene(new Scene(loader.load()));
            userConnectionWindowStage.setResizable(false);
            userConnectionWindowStage.setOnCloseRequest(action -> AppUser.sClient.close());
            userConnectionWindowStage.show();

            this.stage.close();
        } catch (Exception e) {
            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Impossible d'ouvrir la fênetre de connexion.");
            alert.showAndWait();
        }
    }

    @FXML
    public void exitAction() {
        AppUser.sClient.close();
        System.exit(0);
    }

    @FXML
    public void weekBeforeAction() {
        this.weekOffset--;
        this.setUI();
    }

    @FXML
    public void weekAfterAction() {
        if (this.weekOffset + 1 == this.weeks.size())
            this.weeks.add(new Week(this.weekOffset + 1));

        this.weekOffset++;
        this.setUI();
    }

    @FXML
    public void datePickerAction() {
        int weekOffset = AppCalendar.daysBetweenFirstOfWeek(this.datePicker.getValue()) / 7;
        if (this.weeks.size() - 1 < weekOffset) {
            for (int i = this.weeks.size(); i <= weekOffset; i++) {
                this.weeks.add(new Week(i));
            }
        }

        this.weekOffset = weekOffset;
        this.setUI();
    }

    @FXML
    public void editProfileAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/ProfileEditorView.fxml"));
            Stage profileEditorWindowStage = new Stage();
            loader.setController(new ProfileEditorController(profileEditorWindowStage));
            profileEditorWindowStage.setTitle("Editeur de profile");
            profileEditorWindowStage.setScene(new Scene(loader.load()));
            profileEditorWindowStage.setResizable(false);
            profileEditorWindowStage.showAndWait();

            this.profileImageView.setImage(AppUser.user.getProfileImage());
        } catch (Exception e) {
            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Impossible d'ouvrir la fênetre d'editeur de profile.");
            alert.showAndWait();
        }
    }

    @FXML
    public void detailClassBatchAction() {
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
