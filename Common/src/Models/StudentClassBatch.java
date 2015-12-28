package Models;


import java.io.Serializable;

public class StudentClassBatch implements Serializable {
    private int studentClassBatchId;
    private int studentId;
    private int classBatchId;

    public StudentClassBatch(int studentClassBatchId, int studentId, int classBatchId) {
        this.studentClassBatchId = studentClassBatchId;
        this.studentId = studentId;
        this.classBatchId = classBatchId;
    }

    public int getStudentClassBatchId() {
        return this.studentClassBatchId;
    }

    public int getStudentId() {
        return this.studentId;
    }

    public int getClassBatchId() {
        return this.classBatchId;
    }
}
