package Models;


import java.io.Serializable;

public class StudentClassBatch implements Serializable, IEntity {
    private int studentClassBatchId;
    private int studentId;
    private int classBatchId;

    public StudentClassBatch(int studentClassBatchId, int studentId, int classBatchId) {
        this.studentClassBatchId = studentClassBatchId;
        this.studentId = studentId;
        this.classBatchId = classBatchId;
    }

    @Override
    public int getPrimaryKey() {
        return studentClassBatchId;
    }
}
