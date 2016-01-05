package messages.models;


import utils.socket.IMessage;

@Deprecated
public class StudentClassBatchRequest implements IMessage {
    private final int studentId;

    public StudentClassBatchRequest(int studentId) {
        this.studentId = studentId;
    }

    public int getStudentId() {
        return this.studentId;
    }
}
