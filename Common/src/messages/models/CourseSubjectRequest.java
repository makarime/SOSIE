package messages.models;

import utils.socket.IMessage;

public class CourseSubjectRequest implements IMessage {
    private final int courseId;

    public CourseSubjectRequest(int courseId) {
        this.courseId = courseId;
    }

    public int getCourseId() {
        return courseId;
    }
}
