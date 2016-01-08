package Models;

import java.io.Serializable;

public class Mark implements Serializable, IEntity {
    private int markId;
    private int studentClassBatchId;
    private int subjectId;
    private int value;

    public Mark(int markId, int studentClassBatchId, int subjectId, int value) {
        this.markId = markId;
        this.studentClassBatchId = studentClassBatchId;
        this.subjectId = subjectId;
        this.value = value;
    }

    @Override
    public int getPrimaryKey() {
        return markId;
    }
}
