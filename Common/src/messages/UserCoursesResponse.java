package messages;


import Models.Course;
import utils.socket.IMessage;

import java.util.List;

public class UserCoursesResponse implements IMessage {
    private final List<Course> courses;

    public UserCoursesResponse(List<Course> courses) {
        this.courses = courses;
    }

    public List<Course> getCourses() {
        return this.courses;
    }
}
