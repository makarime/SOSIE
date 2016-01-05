package messages.models;


import Models.ClassBatch;
import utils.socket.IMessage;

import java.util.ArrayList;

@Deprecated
public class ProfessorClassBatchesResponse implements IMessage {
    private final ArrayList<ClassBatch> classBatches;

    public ProfessorClassBatchesResponse(ArrayList<ClassBatch> classBatches) {
        this.classBatches = classBatches;
    }

    public ArrayList<ClassBatch> getClassBatches() {
        return this.classBatches;
    }
}
