package messages.models;

import utils.socket.IMessage;

public class ClassBatchEusRequest implements IMessage {
    private final int classBatchId;

    public ClassBatchEusRequest(int classBatchId) {
        this.classBatchId = classBatchId;
    }

    public int getClassBatchId() {
        return this.classBatchId;
    }
}
