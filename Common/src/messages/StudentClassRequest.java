package messages;


import utils.socket.IMessage;

@Deprecated
public class StudentClassRequest implements IMessage {
    private final int classId;

    public StudentClassRequest(int classId) {
        this.classId = classId;
    }

    public int getClassId() {
        return this.classId;
    }
}
