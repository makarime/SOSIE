package messages.models;

import Models.Subject;
import utils.socket.IMessage;

public class CourseSubjectResponse implements IMessage {
    private final Subject subject;

    public CourseSubjectResponse(Subject subject) {
        this.subject = subject;
    }

    public Subject getSubject() {
        return subject;
    }
}
