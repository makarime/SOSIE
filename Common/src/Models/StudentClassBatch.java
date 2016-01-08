package Models;


import java.io.Serializable;
import java.util.List;

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

    public List<Mark> getMarks() {
        return DataBase.currentProxy.loadObjectByReverseId(Mark.class, StudentClassBatch.class, studentClassBatchId);
    }

    public Student getStudent() {
        return DataBase.currentProxy.loadObjectById(Student.class, studentId);
    }

    public ClassBatch getClassBatch() {
        return DataBase.currentProxy.loadObjectById(ClassBatch.class, classBatchId);
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
