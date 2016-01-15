package Controllers;

import Models.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import messages.LogoutRequest;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class MainController implements Initializable {
    //region Interface fields
    @FXML
    public Label appUserNameLabel;
    @FXML
    public ImageView profileImageView;
    @FXML
    public Button editProfileButton;
    @FXML
    public ChoiceBox<ClassBatch> classBatchesChoiceBox;
    @FXML
    public Button classBatchInfoButton;
    @FXML
    public DatePicker datePicker;
    @FXML
    public Label weekSpanLabel;
    @FXML
    public Button weekBeforeButton;
    @FXML
    public Label exampleHourLabel;
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
    public GridPane courseGridPane;
    @FXML
    private Stage stage = null;
    //endregion

    //region Fields
    private ArrayList<Week> weeks = null;
    private int weekOffset;
    //endregion

    //region Constructor(s)
    public MainController(Stage stage) {
        this.stage = stage;
    }
    //endregion

    //region Methods
    private Week currentWeek() {
        return this.weeks.get(this.weekOffset);
    }
    //endregion

    //region Initialization
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.weeks = new ArrayList<>();
        this.weekOffset = 0;
        this.weeks.add(new Week(0));

        this.initUI();
        this.setUI();
    }

    private void initProfileTitledPane() {
        this.editProfileButton.disableProperty().bind(this.profileImageView.imageProperty().isNull());
        this.appUserNameLabel.setText(AppUser.user.toString());
        Async.execute(() -> {
            Image image = new Image(new ByteArrayInputStream(AppUser.user.getProfileImageByteArray()));
            Platform.runLater(() -> this.profileImageView.setImage(image));
        });
    }

    private void initDatePicker() {
        this.datePicker.setValue(LocalDateTime.ofInstant(AppCalendar.currentDate.toInstant(), ZoneId.systemDefault()).plusDays(1).toLocalDate());
        Callback<DatePicker, DateCell> dayCellFactory = dp -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if ((item.getDayOfWeek() == DayOfWeek.SATURDAY) || (item.getDayOfWeek() == DayOfWeek.SUNDAY) || (AppCalendar.daysBetween(AppCalendar.currentDate, item) <= 0))
                    setDisable(true);
            }
        };
        Async.execute(() -> this.datePicker.setDayCellFactory(dayCellFactory));
    }

    private void initClassBatchesChoiceBox() {
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
            if (AppUser.user.isStudent())
                this.classBatchesChoiceBox.getItems().addAll(((Student) AppUser.user).getCurrentClassBatch());
            else if (AppUser.user.isProfessor())
                this.classBatchesChoiceBox.getItems().addAll(((Professor) AppUser.user).getClassBatches());

            Platform.runLater(() -> this.classBatchesChoiceBox.setValue(this.classBatchesChoiceBox.getItems().get(0)));
        });
    }

    private void initUI() {
        this.initDatePicker();
        this.initProfileTitledPane();
        this.initClassBatchesChoiceBox();
    }
    //endregion

    //region Interface setters
    private void setDisableWeekBeforeButton() {
        if (this.weekOffset == 0)
            this.weekBeforeButton.setDisable(true);
        else
            this.weekBeforeButton.setDisable(false);
    }

    private void setDayLabels() {
        this.mondayLabel.setText("Lundi\n" + this.currentWeek().getDay(0).getDateToString());
        this.tuesdayLabel.setText("Mardi\n" + this.currentWeek().getDay(1).getDateToString());
        this.wednesdayLabel.setText("Mercredi\n" + this.currentWeek().getDay(2).getDateToString());
        this.thursdayLabel.setText("Jeudi\n" + this.currentWeek().getDay(3).getDateToString());
        this.fridayLabel.setText("Vendredi\n" + this.currentWeek().getDay(4).getDateToString());
    }

    private void setCourses() {
        List<Node> children = this.courseGridPane.getChildren();
        for (int i = 0; i < children.size(); i++) {
            Node node = children.get(i);
            if ((GridPane.getColumnIndex(node) != null) && (GridPane.getRowIndex(node) != null)) {
                this.courseGridPane.getChildren().remove(node);
                i--;
            }
        }

        for (int i = 0; i < 5; i++) {
            final int finalI = i;
            Async.execute(() -> {
                List<Course> l = this.currentWeek().getDay(finalI).getCourses();
                Platform.runLater(() -> {
                    Calendar calendar = GregorianCalendar.getInstance();
                    for (Course course : l) {
                        calendar.setTime(course.getDate());
                        int beginHour = calendar.get(Calendar.HOUR_OF_DAY);
                        int beginMinutes = calendar.get(Calendar.MINUTE);
                        calendar.add(Calendar.MINUTE, course.getDuree());
                        int endMinutes = calendar.get(Calendar.MINUTE);

                        int columnIndex = beginHour - 8;
                        double columnSpan = Math.ceil((double) course.getDuree() / 60);
                        if (course.getDuree() > columnSpan * 60 - beginMinutes)
                            columnSpan++;
                        int width = (int) (this.courseGridPane.getWidth() - this.mondayLabel.getWidth()) / 11;
                        int height = (int) (this.courseGridPane.getHeight() - this.exampleHourLabel.getHeight()) / 5;
                        int rightWidthOffset = (int) ((double) endMinutes * (double) width / 60);
                        int leftWidthOffset = (int) ((double) beginMinutes * (double) width / 60);

                        Label label = new Label(course.getInfos());
                        label.setAlignment(Pos.BASELINE_CENTER);
                        label.setPrefHeight(height);
                        label.setPrefWidth(width * columnSpan);
                        GridPane.setMargin(label, new Insets(2, rightWidthOffset + 2, 2, leftWidthOffset + 2));
                        label.setStyle("-fx-background-color: #F0F0F0; -fx-border-color: gray;");
                        this.courseGridPane.add(label, columnIndex + 1, finalI + 1);
                        GridPane.setColumnSpan(label, (int) columnSpan);
                    }
                });
            });
        }
    }

    private void setUI() {
        this.weekSpanLabel.setText(this.currentWeek().getSpanWeekString());
        this.setDisableWeekBeforeButton();
        this.setDayLabels();
        this.setCourses();
    }
    //endregion

    //region Actions
    @FXML
    public void logOutAction() {
        AppUser.user = null;
        AppUser.sClient.sendRequest(new LogoutRequest());

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

            this.profileImageView.setImage(new Image(new ByteArrayInputStream(AppUser.user.getProfileImageByteArray())));
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
    //endregion
}
