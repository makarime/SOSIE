package Models;

import java.util.List;

public class Mark {
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

    public Subject getSubject() {
        return DataBase.currentProxy.loadObjectById(Subject.class, subjectId);
    }

    public List<StudentClassBatch> getStudentClassBatches() {
        return DataBase.currentProxy.loadObjectByReverseId(StudentClassBatch.class, Mark.class, markId);
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
