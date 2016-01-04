package Models;

import messages.models.EuClassRequest;
import messages.models.EuClassResponse;
import messages.models.EuSubjectsRequest;
import messages.models.EuSubjectsResponse;

import java.util.List;

public class Eu {
    private int euId;
    private int classId;
    private String name;
    private List<Subject> subjects = null;

    public Eu(int euId, int classId, String name) {
        this.euId = euId;
        this.classId = classId;
        this.name = name;
    }

    public Class getClassObj() {
        if(DataBase.classHashtable.containsKey(classId))
            return DataBase.classHashtable.get(classId);

        Class clazz = ((EuClassResponse) DataBase.currentProxy.load(new EuClassRequest(classId))).getClazz();
        DataBase.classHashtable.put(classId, clazz);
        return clazz;
    }

    public List<Subject> getSubjects() {
        if(subjects == null)
            subjects = ((EuSubjectsResponse) DataBase.currentProxy.load(new EuSubjectsRequest(euId))).getSubjects();

        return subjects;
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
