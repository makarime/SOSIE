package Models;


import java.io.Serializable;
import java.util.ArrayList;

public class Class implements Serializable {
    private int id;
    private int year;
    private int idProfessorInCharge;
    private ArrayList<Student> students = null;

    public Class(int id, int year, int idProfessorInCharge) {
        this.id = id;
        this.year = year;
        this.idProfessorInCharge = idProfessorInCharge;
    }

    public String toString() {
        return String.valueOf(this.year);
    }

    public int getId() {
        return this.id;
    }
}
