package messages.models;

import utils.socket.IMessage;

public class ClassroomCourseRequest implements IMessage {
    private final int classroomId;

    public ClassroomCourseRequest(int classroomId) {
        this.classroomId = classroomId;
    }

    public int getClassroomId() {
        return classroomId;
    }
}
