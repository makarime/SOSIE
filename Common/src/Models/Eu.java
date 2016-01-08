package Models;

import java.io.Serializable;

public class Eu implements Serializable, IEntity {
    private int euId;
    private int classId;
    private String name;

    public Eu(int euId, int classId, String name) {
        this.euId = euId;
        this.classId = classId;
        this.name = name;
    }

    @Override
    public int getPrimaryKey() {
        return euId;
    }
}
