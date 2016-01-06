package Models;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClassBatch implements Serializable {
    private int classBatchId;
    private int classId;
    private int batchId;
    private int professorInChargeId;

    public ClassBatch(int classBatchId, int classId, int batchId, int professorInChargeId) {
        this.classBatchId = classBatchId;
        this.classId = classId;
        this.batchId = batchId;
        this.professorInChargeId = professorInChargeId;
    }

    public int getClassBatchId() {
        return this.classBatchId;
    }

    public int getClassId() {
        return this.classId;
    }

    public int getBatchId() {
        return this.batchId;
    }

    public int getProfessorInChargeId() {
        return this.professorInChargeId;
    }

    public Professor getProfessorInCharge() {
        if (DataBase.professorHashtable.containsKey(this.professorInChargeId))
            return DataBase.professorHashtable.get(this.professorInChargeId);
        else {
            Professor professor = DataBase.currentProxy.loadObjectById(Professor.class, this.professorInChargeId);
            DataBase.userHashtable.put(professor.getUserId(), professor);
            DataBase.professorHashtable.put(professor.getUserId(), professor);
            return professor;
        }
    }

    public List<StudentClassBatch> getStudents() {
        return DataBase.currentProxy.loadObjectByReverseId(StudentClassBatch.class, ClassBatch.class, classBatchId);
    }

    public Batch getBatch() {
        return DataBase.currentProxy.loadObjectById(Batch.class, batchId);
    }

    public Class getClassObj() {
        return DataBase.currentProxy.loadObjectById(Class.class, classId);
    }

    public List<Course> getCourses() {
        return DataBase.currentProxy.loadObjectByReverseId(Course.class, ClassBatch.class, classBatchId);
    }

    public String toString() {
        return "No name yet";
    }
}
