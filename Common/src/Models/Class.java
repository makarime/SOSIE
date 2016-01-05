package Models;

import java.io.Serializable;
import java.util.List;

public class Class implements Serializable {
    private int classId;
    private String name = null;

    public Class(String name, int classId) {
        this.name = name;
        this.classId = classId;
    }

    public List<Eu> getEus() {
        return DataBase.currentProxy.loadObjectByReverseId(Eu.class, Class.class, classId);
    }

    public List<ClassBatch> getClassBatches() {
        return DataBase.currentProxy.loadObjectByReverseId(ClassBatch.class, Class.class, classId);
    }

    public int getClassId() {
        return this.classId;
    }

    public String getName() {
        return this.name;
    }
}
