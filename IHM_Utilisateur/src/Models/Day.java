package Models;

import Models.DataBaseModels.Appointment;

import java.util.ArrayList;
import java.util.Date;

public class Day {
    private Date date;
    private ArrayList<Appointment> appointments = new ArrayList<>();
    private ArrayList<Appointment> newAppointments = new ArrayList<>();

    public Day(Date date) {
        this.date = date;
    }

    public String getDateToString() {
        return AppCalendar.dateFormat.format(this.date);
    }

    public ArrayList<Appointment> getAppointments() {
        ArrayList<Appointment> al = new ArrayList<>();
        al.addAll(this.appointments);
        al.addAll(this.newAppointments);

        return al;
    }
}
