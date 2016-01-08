package Models;

import java.io.Serializable;
import java.util.List;

public class Batch implements Serializable, IEntity {
    private int batchId;
    private int year;

    public Batch(int batchId, int year) {
        this.batchId = batchId;
        this.year = year;
    }

    @Override
    public int getPrimaryKey() {
        return this.batchId;
    }

    public int getBatchId() {
        return batchId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return this.year + " - " + (this.year + 5);
    }

    public List<ClassBatch> getClassBatches() {
        return DataBaseEnv.currentProxy.loadObjectByReverseId(ClassBatch.class, Batch.class, batchId);
    }
}
