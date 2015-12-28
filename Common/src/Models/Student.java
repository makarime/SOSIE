package Models;


public class Student extends User {
    private int studentId;

    public Student(int userId, int studentId, String lastName, String firstName) {
        this.status = Status.student;
        this.userId = userId;
        this.studentId = studentId;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public int getStudentId() {
        return this.studentId;
    }
}
