package Models;


public class Professor extends User {
    private int professorId;

    public Professor(int userId, int professorId, String lastName, String firstName) {
        this.status = Status.professor;
        this.userId = userId;
        this.professorId = professorId;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public int getProfessorId() {
        return this.professorId;
    }
}
