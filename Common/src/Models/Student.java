package Models;


import java.util.List;

public class Student extends User {
    public Student(int userId, String lastName, String firstName) {
        this.userId = userId;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public List<StudentClassBatch> getStudentClassBatches() {
        return DataBaseEnv.currentProxy.loadObjectByReverseId(StudentClassBatch.class, Student.class, userId);
    }

    public StudentClassBatch getCurrentStudentClassBatch() {
        return getStudentClassBatches().stream()
                .max((o1, o2) -> Integer.compare(o1.getClassBatch().getClasss().getRank(), o2.getClassBatch().getClasss().getRank()) * 10 +
                                 Integer.compare(o1.getClassBatch().getBatch().getYear(),  o2.getClassBatch().getBatch().getYear()))
                .get();
    }

    public ClassBatch getCurrentClassBatch() {
        return getCurrentStudentClassBatch().getClassBatch();
    }

}
