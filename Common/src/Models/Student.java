package Models;


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
}
