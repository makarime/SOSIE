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

    public Subject getSubject() {
        return DataBaseEnv.currentProxy.loadObjectById(Subject.class, subjectId);
    }

    public StudentClassBatch getStudentClassBatch() {
        return DataBaseEnv.currentProxy.loadObjectById(StudentClassBatch.class, studentClassBatchId);
    }

    public int getMarkId() {
        return markId;
    }

    public int getStudentClassBatchId() {
        return studentClassBatchId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public int getValue() {
        return value;
    }
}
