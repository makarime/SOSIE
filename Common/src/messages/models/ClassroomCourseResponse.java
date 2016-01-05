package messages.models;

import Models.Course;
import utils.socket.IMessage;

import java.util.List;

public class ClassroomCourseResponse implements IMessage {
    public final List<Course> courses;

    public ClassroomCourseResponse(List<Course> courses) {
        this.courses = courses;
    }

    public List<Course> getCourses() {
        return courses;
    }
}
