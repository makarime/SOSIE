package messages.models;

import utils.socket.IMessage;

@Deprecated
public class BatchClassBatchRequest implements IMessage {
    private final int batchId;

    public BatchClassBatchRequest(int batchId) {
        this.batchId = batchId;
    }

    public int getBatchId() {
        return batchId;
    }
}
