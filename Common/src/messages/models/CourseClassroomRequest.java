package messages.models;

import utils.socket.IMessage;

public class CourseClassroomRequest implements IMessage {
    private final int classroomId;

    public CourseClassroomRequest(int classroomId) {
        this.classroomId = classroomId;
    }

    public int getClassroomId() {
        return classroomId;
    }
}
