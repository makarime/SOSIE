package Models.DataBaseModels;

import Models.Professor;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Appointment {
    private LocalDate localDate = null;
    private LocalTime localTimeStart = null;
    private LocalTime localTimeEnd = null;
    private Professor professor = null;

    public Appointment(LocalDate localDate, LocalTime localTimeStart, LocalTime localTimeEnd) {
        this.localDate = localDate;
        this.localTimeStart = localTimeStart;
        this.localTimeEnd = localTimeEnd;
    }

    public Button generateButton() {
        int widthForHour = 148;

        Button button = new Button();
        button.setAlignment(Pos.CENTER);
        button.setText(this.localTimeStart.toString() + " - " + this.localTimeEnd.toString());
        button.setPrefWidth(widthForHour * ChronoUnit.HOURS.between(this.localTimeStart, this.localTimeEnd));
        button.setPrefHeight(Region.USE_COMPUTED_SIZE);

        return button;
    }
}
