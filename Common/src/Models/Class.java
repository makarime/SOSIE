package Models;


import java.util.ArrayList;

public class Class {
    private int id;
    private String name = null;
    private Professor profInCharge = null;
    private ArrayList<Student> students = new ArrayList<>();

    public Class(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}
