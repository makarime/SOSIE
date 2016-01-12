package Models;


public class Week {
    private Day[] days = new Day[5];

    public Week(int offset) {
        this.days = AppCalendar.getDaysOfWeek(offset);
    }

    public Day getDay(int index) {
        return this.days[index];
    }

    public String getSpanWeekString() {
        return this.days[0].getDateToString() + " au " + this.days[4].getDateToString();
    }
}
