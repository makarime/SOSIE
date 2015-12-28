package Models;

import java.io.Serializable;

public class Class implements Serializable {
    private int classId;
    private String name = null;

    public Class(String name, int classId) {
        this.name = name;
        this.classId = classId;
    }

    public int getClassId() {
        return this.classId;
    }

    public String getName() {
        return this.name;
    }
}
