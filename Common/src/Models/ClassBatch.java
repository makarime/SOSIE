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
        return batchId;
    }

    public String getName() {
        return this.getClasss().getName() + " / " + this.getBatch().getName();
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

    @Override
    public String toString() {
        return this.getName();
    }
}
