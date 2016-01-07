package Models;

import java.util.List;

public class Student extends User {
    private int currentCP;

    public Student(int userId, String lastName, String firstName, int currentCP) {
        this.userId = userId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.currentCP = currentCP;
    }

    public ClassBatch getClassBatch() {
        return DataBase.currentProxy.loadObjectById(ClassBatch.class, currentCP);
    }

    public List<StudentClassBatch> getStudentClassBatches() {
        return DataBase.currentProxy.loadObjectByReverseId(StudentClassBatch.class, Student.class, userId);
    }

}
