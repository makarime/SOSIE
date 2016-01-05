package messages.models;

import utils.socket.IMessage;

@Deprecated
public class CourseClassBatchRequest implements IMessage {
    private final int classBatchId;

    public CourseClassBatchRequest(int classBatchId) {
        this.classBatchId = classBatchId;
    }

    public int getClassBatchId() {
        return classBatchId;
    }
}
