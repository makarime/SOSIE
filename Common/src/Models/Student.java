package Models;


import messages.StudentClassRequest;
import messages.StudentClassResponse;

public class Student extends User {
    private int idClass;
    private Class c = null;

    public Student(int id, String firstName, String name, int idClass) {
        this.status = Status.student;
        this.id = id;
        this.firstName = firstName;
        this.name = name;
        this.idClass = idClass;
    }

    public Class getStudentClass() {
        if (this.c == null) {
            StudentClassResponse studentClassResponse = ((StudentClassResponse) DataBase.sClient.sendRequest(new StudentClassRequest(this.idClass)));

            if (DataBase.classes.containsKey(studentClassResponse.getStudentClass().getId()))
                this.c = DataBase.classes.get(studentClassResponse.getStudentClass().getId());
            else {
                this.c = studentClassResponse.getStudentClass();
                DataBase.classes.put(this.c.getId(), this.c);
            }
        }

        return this.c;
    }
}
