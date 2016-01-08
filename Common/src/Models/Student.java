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

    public ClassBatch getCurrentClassBatch() {
        return DataBaseEnv.currentProxy.loadObjectById(ClassBatch.class, currentCP);
    }

    public List<StudentClassBatch> getStudentClassBatches() {
        return DataBaseEnv.currentProxy.loadObjectByReverseId(StudentClassBatch.class, Student.class, userId);
    }

}
