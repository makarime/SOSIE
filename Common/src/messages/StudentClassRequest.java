package messages;


import utils.socket.IMessage;

public class StudentClassRequest implements IMessage {
    private final int userId;

    public StudentClassRequest(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return this.userId;
    }
}
