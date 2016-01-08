package Models;

import java.io.Serializable;

public class Class implements Serializable, IEntity {
    private int classId;
    private String name = null;

    public Class(String name, int classId) {
        this.name = name;
        this.classId = classId;
    }

    @Override
    public int getPrimaryKey() {
        return classId;
    }

    public String getName() {
        return this.name;
    }
}
