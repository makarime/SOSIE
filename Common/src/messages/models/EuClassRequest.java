package messages.models;

import utils.socket.IMessage;

public class EuClassRequest implements IMessage {
    private final int classId;

    public EuClassRequest(int classId) {
        this.classId = classId;
    }

    public int getClassId() {
        return classId;
    }

}
