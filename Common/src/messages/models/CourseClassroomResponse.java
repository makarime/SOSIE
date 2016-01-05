package messages.models;

import Models.Classroom;
import Models.Professor;
import utils.socket.IMessage;

@Deprecated
public class CourseClassroomResponse implements IMessage {
    private final Classroom classroom;

    public CourseClassroomResponse(Classroom classroom) {
        this.classroom = classroom;
    }

    public Classroom getClassroom() {
        return classroom;
    }
}
