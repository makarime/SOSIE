package Models.DataBaseModels;

import java.util.ArrayList;

public class Module {
    private int id;
    private String name = null;
    private String description = null;
    ArrayList<Course> courses = new ArrayList<>();

    public Module(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId(){
        return this.id;
    }
}
