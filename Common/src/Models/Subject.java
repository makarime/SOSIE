package Models;

import java.io.Serializable;

public class Subject implements Serializable, IEntity {
    private int subjectId;
    private int euId;
    private String name;

    public Subject(int subjectId, int euId, String name) {
        this.subjectId = subjectId;
        this.euId = euId;
        this.name = name;
    }

    @Override
    public int getPrimaryKey() {
        return subjectId;
    }
}
