package Models;

import java.io.Serializable;
import java.util.List;

public class Batch implements Serializable, IEntity {
    private int batchId;
    private int year;
    private String name;

    public Batch(int batchId, int year, String name) {
        this.batchId = batchId;
        this.year = year;
        this.name = name;
    }

    @Override
    public int getPrimaryKey() {
        return batchId;
    }

    public List<ClassBatch> getClassBatches() {
        return DataBase.currentProxy.loadObjectByReverseId(ClassBatch.class, Batch.class, batchId);
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
