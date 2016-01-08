package Models;

import java.io.Serializable;

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

    public String getName() {
        return this.year + " - " + (this.year + 5);
    }
}
