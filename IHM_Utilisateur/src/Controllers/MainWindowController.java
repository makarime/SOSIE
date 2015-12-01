package Controllers;

import Models.*;
import Models.DataBaseModels.Appointment;
import Models.DataBaseModels.DataBase;
import Models.DataBaseModels.Professor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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

public class MainWindowController implements Initializable {
    /* Fields */

    @FXML
    public Label appUserNameLabel;
    @FXML
    public ImageView profileImageView;
    @FXML
    public ChoiceBox<Integer> startHourChoiceBox;
    @FXML
    public ChoiceBox<Integer> startMinutesChoiceBox;
    @FXML
    public ChoiceBox<Integer> endHourChoiceBox;
    @FXML
    public ChoiceBox<Integer> endMinutesChoiceBox;
    @FXML
    public TitledPane addAppointmentTitledPane;
    @FXML
    public DatePicker datePicker;
    @FXML
    public Button addAppointmentButton;
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
    @FXML
    public ChoiceBox<Professor> professorsChoiceBox;

    public Stage stage = null;
    private ArrayList<Week> weeks = null;
    private int weekOffset;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.weeks = new ArrayList<>();
        this.weekOffset = 0;
        this.weeks.add(new Week(0));
        DataBase.setProfessors();

        this.setInitializationUI();
        this.setUI();
    }

    private void setProfileTitledPane() {
        this.appUserNameLabel.setText(AppUser.user.toString());
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

    private void setCourseHourChoiceBoxes() {
        this.startHourChoiceBox.getSelectionModel().selectFirst();
        this.startMinutesChoiceBox.getSelectionModel().selectFirst();
        this.endHourChoiceBox.getSelectionModel().select(1);
        this.endMinutesChoiceBox.getSelectionModel().selectFirst();
    }

    private void setProfessorsChoiceBox() {
        ObservableList<Professor> list = FXCollections.observableArrayList(DataBase.professorsHashtable.values());
        list.sort((p1, p2) -> p1.toString().compareTo(p2.toString()));
        this.professorsChoiceBox.setItems(list);
        this.professorsChoiceBox.getSelectionModel().selectFirst();
    }

    private void setAddCourseTitledPane() {
        if (AppUser.user.isStudent()) {
            this.addAppointmentTitledPane.setDisable(true);
            this.addAppointmentTitledPane.setExpanded(false);
        } else {
            this.addAppointmentTitledPane.setDisable(false);
            this.setDatePicker();
            this.setCourseHourChoiceBoxes();
            this.setProfessorsChoiceBox();
        }
    }

    private void setInitializationUI() {
        this.setAddCourseTitledPane();
        this.setProfileTitledPane();
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

    private void setHBox() {
        this.mondayHBox.getChildren().clear();
        this.tuesdayHBox.getChildren().clear();
        this.wednesdayHBox.getChildren().clear();
        this.thursdayHBox.getChildren().clear();
        this.fridayHBox.getChildren().clear();

        ArrayList<ArrayList<Appointment>> l = this.weeks.get(this.weekOffset).getAppointments();
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/UserConnectionWindowView.fxml"));
            Parent root = loader.load();
            UserConnectionWindowController userConnectionWindowController = loader.getController();
            Stage userConnectionWindowStage = new Stage();
            userConnectionWindowController.stage = userConnectionWindowStage;
            userConnectionWindowStage.setTitle("Connexion");
            userConnectionWindowStage.setScene(new Scene(root));
            userConnectionWindowStage.setResizable(false);
            userConnectionWindowStage.setOnCloseRequest(action -> DataBase.CloseDataBaseConnection());
            userConnectionWindowStage.show();

            this.stage.close();
        } catch (Exception e) {
            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Impossible d'ouvrir la fênetre de connexion.");
            alert.showAndWait();
        }
    }

    @FXML
    public void exitAction() {
        DataBase.CloseDataBaseConnection();
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
    public void addCourseAction() {
        int dayOffset = AppCalendar.daysBetweenFirstOfWeek(this.datePicker.getValue());
        Day day = this.weeks.get(dayOffset / 7).getDay(dayOffset % 7);

        try {

        } catch (Exception ignored) {
            ignored.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Impossible d'ajouter un cours avec les horraires entrées.");
            alert.showAndWait();
        }
    }

    @FXML
    public void editProfileAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/ProfileEditorView.fxml"));
            Parent root = loader.load();
            ProfileEditorController profileEditorController = loader.getController();
            Stage profileEditorWindowStage = new Stage();
            profileEditorController.stage = profileEditorWindowStage;
            profileEditorWindowStage.setTitle("Editeur de profile");
            profileEditorWindowStage.setScene(new Scene(root));
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
}
