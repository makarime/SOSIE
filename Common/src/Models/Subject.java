package Models;

import java.io.Serializable;
import java.util.List;

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

    public Eu getEu() {
        return DataBaseEnv.currentProxy.loadObjectById(Eu.class, euId);
    }

    public List<Mark> getMarks() {
        return DataBaseEnv.currentProxy.loadObjectByReverseId(Mark.class, Subject.class, subjectId);
    }

    public List<Course> getCourses() {
        return DataBaseEnv.currentProxy.loadObjectByReverseId(Course.class, Subject.class, subjectId);
    }

    /////////

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getEuId() {
        return euId;
    }

    public void setEuId(int euId) {
        this.euId = euId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
