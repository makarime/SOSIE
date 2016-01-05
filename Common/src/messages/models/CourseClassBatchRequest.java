package messages.models;

import utils.socket.IMessage;

public class CourseClassBatchRequest implements IMessage {
    private final int classBatchId;

    public CourseClassBatchRequest(int classBatchId) {
        this.classBatchId = classBatchId;
    }

    public int getClassBatchId() {
        return classBatchId;
    }
}
