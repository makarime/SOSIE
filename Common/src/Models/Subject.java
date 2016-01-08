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
        if(DataBase.euHashtable.containsKey(euId))
            return DataBase.euHashtable.get(euId);

        Eu eu = DataBase.currentProxy.loadObjectById(Eu.class, euId);
        DataBase.euHashtable.put(euId, eu);
        return eu;
    }

    public List<Mark> getMarks() {
        return DataBase.currentProxy.loadObjectByReverseId(Mark.class, Subject.class, subjectId);
    }

    public List<Course> getCourses() {
        return DataBase.currentProxy.loadObjectByReverseId(Course.class, Subject.class, subjectId);
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
