package Models;

import java.util.List;

public class Classroom {
    private int classroomId;
    private int number;
    private int capacity;
    private boolean hasPC;
    private boolean hasProjector;
    private boolean hasDigitalPanel;
    private boolean canDisabledPerson;

    public Classroom(int classroomId, int number, int capacity, boolean hasPC, boolean hasProjector, boolean hasDigitalPanel, boolean canDisabledPerson) {
        this.classroomId = classroomId;
        this.number = number;
        this.capacity = capacity;
        this.hasPC = hasPC;
        this.hasProjector = hasProjector;
        this.hasDigitalPanel = hasDigitalPanel;
        this.canDisabledPerson = canDisabledPerson;
    }

    public List<?> getCourse() {
        return null; //TODO
    }

    public int getClassroomId() {
        return classroomId;
    }

    public int getNumber() {
        return number;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isHasPC() {
        return hasPC;
    }

    public boolean isHasProjector() {
        return hasProjector;
    }

    public boolean isHasDigitalPanel() {
        return hasDigitalPanel;
    }

    public boolean isCanDisabledPerson() {
        return canDisabledPerson;
    }
}
