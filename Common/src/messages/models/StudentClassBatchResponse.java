package messages.models;


import Models.ClassBatch;
import utils.socket.IMessage;

@Deprecated
public class StudentClassBatchResponse implements IMessage {
    private final ClassBatch classBatch;

    public StudentClassBatchResponse(ClassBatch classBatch) {
        this.classBatch = classBatch;
    }

    public ClassBatch getClassBatch() {
        return this.classBatch;
    }
}
