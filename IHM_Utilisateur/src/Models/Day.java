package Models;

import messages.UserCoursesRequest;
import messages.UserCoursesResponse;

import java.util.Date;
import java.util.List;

public class Day {
    private Date date;
    private List<Course> courses = null;

    public Day(Date date) {
        this.date = date;
    }

    public String getDateToString() {
        return AppCalendar.dateFormat.format(this.date);
    }

    public List<Course> getCourses() {
        if (this.courses == null)
            this.courses = ((UserCoursesResponse) AppUser.sClient.sendRequest(new UserCoursesRequest(this.date))).getCourses();

        return this.courses;
    }
}
