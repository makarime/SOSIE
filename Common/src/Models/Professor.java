package Models;


import java.util.ArrayList;


public class Professor extends User {
    private ArrayList<Integer> classes = null;

    public Professor(int id, String firstName, String name) {
        this.status = Status.professor;
        this.id = id;
        this.firstName = firstName;
        this.name = name;
    }

    public ArrayList<Class> getClasses() {
        ArrayList<Class> classes = new ArrayList<>();

        if (this.classes == null) {
            //TODO request
        }

        return classes;
    }
}
