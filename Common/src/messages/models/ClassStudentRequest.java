package messages.models;

import utils.socket.IMessage;

public class ClassStudentRequest implements IMessage{
    public final int classId;

    public ClassStudentRequest(int classId) {
        this.classId = classId;
    }

    public int getClassId() {
        return classId;
    }
}
