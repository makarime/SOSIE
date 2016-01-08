package Models;

import java.io.Serializable;
import java.util.List;

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

    public Class getClassObj() {
        return DataBase.currentProxy.loadObjectById(Class.class, classId);
    }

    public List<Subject> getSubjects() {
        return DataBase.currentProxy.loadObjectByReverseId(Subject.class, Eu.class, euId);
    }


    ////////////////

    public int getEuId() {
        return euId;
    }

    public void setEuId(int euId) {
        this.euId = euId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
