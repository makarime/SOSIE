package Models;

import messages.models.EuClassRequest;
import messages.models.EuClassResponse;

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
        if(DataBase.classeHashtable.containsKey(classId))
            return DataBase.classeHashtable.get(classId);

        Class clazz = ((EuClassResponse) DataBase.currentProxy.load(new EuClassRequest(classId))).getClazz();
        DataBase.classeHashtable.put(classId, clazz);
        return clazz;
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
