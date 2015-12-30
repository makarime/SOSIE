package messages.models;


import utils.socket.IMessage;

public class StudentClassBatchRequest implements IMessage {
    private final int studentId;

    public StudentClassBatchRequest(int studentId) {
        this.studentId = studentId;
    }

    public int getStudentId() {
        return this.studentId;
    }
}
