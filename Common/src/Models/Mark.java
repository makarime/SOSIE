package Models;

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
