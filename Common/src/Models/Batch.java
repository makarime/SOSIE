package Models;

import messages.models.BatchClassBatchRequest;
import messages.models.BatchClassBatchResponse;

import java.util.List;

public class Batch {
    private int batchId;
    private int year;
    private String name;

    public Batch(int batchId, int year, String name) {
        this.batchId = batchId;
        this.year = year;
        this.name = name;
    }

    public List<ClassBatch> getClassBatches() {
        return ((BatchClassBatchResponse)DataBase.currentProxy.load(new BatchClassBatchRequest(this.batchId))).getClassBatches();
    }

    public int getBatchId() {
        return batchId;
    }

    public int getYear() {
        return year;
    }

    public String getName() {
        return name;
    }
}
