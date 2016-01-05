package Models;

import java.util.Date;

public class Day {
    private Date date;

    public Day(Date date) {
        this.date = date;
    }

    public String getDateToString() {
        return AppCalendar.dateFormat.format(this.date);
    }
}
