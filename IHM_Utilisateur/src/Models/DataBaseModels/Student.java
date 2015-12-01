package Models.DataBaseModels;

public class Student extends User {
    public Student(int id, String firstName, String name) {
        this.status = Status.student;
        this.id = id;
        this.firstName = firstName;
        this.name = name;
    }
}
