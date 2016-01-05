package messages.models;

import Models.ClassBatch;
import Models.Subject;
import utils.socket.IMessage;

public class CourseClassBatchResponse implements IMessage {
    private final ClassBatch classBatch;

    public CourseClassBatchResponse(ClassBatch classBatch) {
        this.classBatch = classBatch;
    }

    public ClassBatch getClassBatch() {
        return classBatch;
    }
}
