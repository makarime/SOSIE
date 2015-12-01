package Models.DataBaseModels;

public class Course {
    private String name = null;
    private Professor personInCharge = null;
    private int ECTS;
    private float CMhours;
    private float TDhours;
    private float TPhours;

    public Course(String name, Professor personInCharge){
        this.name = name;
        this.personInCharge = personInCharge;
    }
}
