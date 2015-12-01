package Models;

import Models.DataBaseModels.Appointment;

import java.util.ArrayList;

public class Week {
    private Day[] days = new Day[5];
    private int offset;
    private boolean daysAreLoaded = false;

    public Week(int offset) {
        this.offset = offset;
    }

    private void loadDays() {
        if (this.daysAreLoaded)
            return;

        this.days = AppCalendar.getDaysOfWeek(this.offset);
        this.daysAreLoaded = true;
    }

    public Day getDay(int index) {
        this.loadDays();
        return this.days[index];
    }

    public String getSpanWeekString() {
        this.loadDays();
        return this.days[0].getDateToString() + " au " + this.days[4].getDateToString();
    }

    public ArrayList<ArrayList<Appointment>> getAppointments() {
        this.loadDays();
        ArrayList<ArrayList<Appointment>> l = new ArrayList<>();

        for (Day d : this.days)
            l.add(d.getAppointments());

        return l;
    }
}
