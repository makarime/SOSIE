package messages;


import utils.socket.IMessage;

public class StudentClassRequest implements IMessage {
    private final int idClass;

    public StudentClassRequest(int idClass) {
        this.idClass = idClass;
    }

    public int getIdClass() {
        return this.idClass;
    }
}
