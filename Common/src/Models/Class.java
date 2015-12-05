package Models;


public class Class {
    private int id;
    private int year;
    private int idProfessorInCharge;

    public Class(int id, int year, int idProfessorInCharge) {
        this.id = id;
        this.year = year;
        this.idProfessorInCharge = idProfessorInCharge;
    }

    public String toString() {
        return String.valueOf(this.year);
    }
}
