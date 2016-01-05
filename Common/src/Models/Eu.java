package Models;

import java.util.List;

public class Eu {
    private int euId;
    private int classId;
    private String name;

    public Eu(int euId, int classId, String name) {
        this.euId = euId;
        this.classId = classId;
        this.name = name;
    }

    public Class getClassObj() {
        if(DataBase.classHashtable.containsKey(classId))
            return DataBase.classHashtable.get(classId);

        Class clazz = DataBase.currentProxy.loadObjectById(Class.class, classId);
        DataBase.classHashtable.put(classId, clazz);
        return clazz;
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
