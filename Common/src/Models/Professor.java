package Models;

public class Professor extends User {
    public Professor(int id, String firstName, String name) {
        this.status = Status.professor;
        this.id = id;
        this.firstName = firstName;
        this.name = name;
    }
}
