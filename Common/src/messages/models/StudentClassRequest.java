package messages.models;


import utils.socket.IMessage;

public class StudentClassRequest implements IMessage {
    private final int classId;

    public StudentClassRequest(int classId) {
        this.classId = classId;
    }

    public int getClassId() {
        return this.classId;
    }
}
