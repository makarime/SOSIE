package Models;

import java.io.Serializable;

public class Classroom implements Serializable, IEntity {
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

    @Override
    public int getPrimaryKey() {
        return classroomId;
    }
}
