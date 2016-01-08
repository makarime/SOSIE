package Models;


import java.io.Serializable;
import java.util.List;

public class ClassBatch implements Serializable, IEntity {
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

    @Override
    public int getPrimaryKey() {
        return this.classBatchId;
    }

    public int getClassBatchId() {
        return this.classBatchId;
    }

    public int getClassId() {
        return this.classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getBatchId() {
        return this.batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    public int getProfessorInChargeId() {
        return this.professorInChargeId;
    }

    public void setProfessorInChargeId(int professorInChargeId) {
        this.professorInChargeId = professorInChargeId;
    }

    @Override
    public String toString() {
        return this.getClasss().toString() + " / " + this.getBatch().toString();
    }

    public List<StudentClassBatch> getStudentClassBatches() {
        return DataBaseEnv.currentProxy.loadObjectByReverseId(StudentClassBatch.class, ClassBatch.class, classBatchId);
    }

    public List<Course> getCourses() {
        return DataBaseEnv.currentProxy.loadObjectByReverseId(Course.class, ClassBatch.class, classBatchId);
    }

    public Professor getProfessorInCharge() {
        return DataBaseEnv.currentProxy.loadObjectById(Professor.class, this.professorInChargeId);
    }

    public List<Student> getStudents() {
        return DataBaseEnv.currentProxy.loadObjectByReverseId(Student.class, ClassBatch.class, this.classBatchId);
    }

    public Class getClasss() {
        return DataBaseEnv.currentProxy.loadObjectById(Class.class, this.classId);
    }

    public Batch getBatch() {
        return DataBaseEnv.currentProxy.loadObjectById(Batch.class, batchId);
    }
}