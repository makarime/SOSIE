package Models;


import messages.ProfessorClassRequest;
import messages.ProfessorClassResponse;

import java.util.ArrayList;


public class Professor extends User {
    private ArrayList<Class> classes = null;

    public Professor(int id, String firstName, String name) {
        this.status = Status.professor;
        this.id = id;
        this.firstName = firstName;
        this.name = name;
    }

    public ArrayList<Class> getClasses() {
        if (this.classes == null) {
            this.classes = new ArrayList<>();
            ProfessorClassResponse professorClassResponse = ((ProfessorClassResponse) DataBase.sClient.sendRequest(new ProfessorClassRequest(this.id)));

            for (Class c : professorClassResponse.getClasses()) {
                if (DataBase.classes.containsKey(c.getId()))
                    this.classes.add(DataBase.classes.get(c.getId()));
                else {
                    DataBase.classes.put(c.getId(), c);
                    this.classes.add(c);
                }
            }
        }

        return this.classes;
    }
}
