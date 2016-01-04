package messages.models;

import Models.ClassBatch;
import utils.socket.IMessage;
import java.util.List;

public class BatchClassBatchResponse implements IMessage {
    private final List<ClassBatch> classBatches;

    public BatchClassBatchResponse(List<ClassBatch> classBatches) {
        this.classBatches = classBatches;
    }

    public List<ClassBatch> getClassBatches() {
        return classBatches;
    }
}
