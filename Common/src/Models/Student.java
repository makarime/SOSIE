package Models;


public class Student extends User {
    private int idClass;

    public Student(int id, String firstName, String name, int idClass) {
        this.status = Status.student;
        this.id = id;
        this.firstName = firstName;
        this.name = name;
        this.idClass = idClass;
    }

    public Class getStudentClass() {
        Class c = DataBase.classes.get(this.idClass);

        if (c == null) {
            //TODO request
        }

        return c;
    }
}
