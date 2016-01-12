package Models;

import messages.UserCoursesRequest;
import messages.UserCoursesResponse;

import java.util.Date;
import java.util.List;

public class Day {
    private Date date;

    public Day(Date date) {
        this.date = date;
    }

    public String getDateToString() {
        return AppCalendar.dateFormat.format(this.date);
    }

    public List<Course> getCourses() {
        return ((UserCoursesResponse) AppUser.sClient.sendRequest(new UserCoursesRequest(this.date))).getCourses();
    }
}
