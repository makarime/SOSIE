package messages.models;

import utils.socket.IMessage;


public class ClassBatchStudentsRequest implements IMessage {
    private final int classBatchId;

    public ClassBatchStudentsRequest(int classBatchId) {
        this.classBatchId = classBatchId;
    }

    public int getClassBatchId() {
        return this.classBatchId;
    }
}
