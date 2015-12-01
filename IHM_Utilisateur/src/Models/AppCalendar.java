package Models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AppCalendar {
    public static Calendar calendar = GregorianCalendar.getInstance();
    public static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    public static Date currentDate = AppCalendar.calendar.getTime();

    public static Day[] getDaysOfWeek(int offset) {
        Day[] days = new Day[5];

        AppCalendar.calendar.add(Calendar.DAY_OF_WEEK, 7 * offset);
        AppCalendar.calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        days[0] = new Day(AppCalendar.calendar.getTime());
        AppCalendar.calendar.add(Calendar.DAY_OF_WEEK, 1);
        days[1] = new Day(AppCalendar.calendar.getTime());
        AppCalendar.calendar.add(Calendar.DAY_OF_WEEK, 1);
        days[2] = new Day(AppCalendar.calendar.getTime());
        AppCalendar.calendar.add(Calendar.DAY_OF_WEEK, 1);
        days[3] = new Day(AppCalendar.calendar.getTime());
        AppCalendar.calendar.add(Calendar.DAY_OF_WEEK, 1);
        days[4] = new Day(AppCalendar.calendar.getTime());
        AppCalendar.calendar.setTime(AppCalendar.currentDate);

        return days;
    }

    public static int daysBetween(Date date, LocalDate localDate) {
        return (int) ChronoUnit.DAYS.between(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalDate(), localDate);
    }

    public static int daysBetweenFirstOfWeek(LocalDate localDate) {
        AppCalendar.calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        int dayOffset = AppCalendar.daysBetween(AppCalendar.calendar.getTime(), localDate);
        AppCalendar.calendar.setTime(AppCalendar.currentDate);

        return dayOffset;
    }
}