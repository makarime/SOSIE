package Models;


import messages.ClassStudentRequest;
import messages.ClassStudentResponse;

import java.io.Serializable;
import java.util.ArrayList;

public class Class implements Serializable {
    private int id;
    private int year;
    private String name = null;
    private int idProfessorInCharge;
    private ArrayList<Student> students = null;

    public Class(int id, int year, String name, int idProfessorInCharge) {
        this.id = id;
        this.year = year;
        this.name = name;
        this.idProfessorInCharge = idProfessorInCharge;
    }

    public String toString() {
        return this.name + " - " + String.valueOf(this.year);
    }

    public int getId() {
        return this.id;
    }

    public ArrayList<Student> getStudents() {
        if (this.students == null) {
            this.students = new ArrayList<>();

            ClassStudentResponse classStudentResponse = ((ClassStudentResponse) DataBase.sClient.sendRequest(new ClassStudentRequest(this.id)));

            for (Student student : classStudentResponse.getStudents()) {
                if (DataBase.users.containsKey(student.getId()))
                    this.students.add((Student) DataBase.users.get(student.getId()));
                else {
                    DataBase.users.put(student.getId(), student);
                    this.students.add(student);
                }
            }
        }

        return this.students;
    }
}
