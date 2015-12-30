package Models;


import messages.models.ClassBatchProfessorInChargeRequest;
import messages.models.ClassBatchProfessorInChargeResponse;
import messages.models.ClassBatchStudentsRequest;
import messages.models.ClassBatchStudentsResponse;

import java.io.Serializable;
import java.util.ArrayList;

public class ClassBatch implements Serializable {
    private int classBatchId;
    private int classId;
    private int batchId;
    private int professorInChargeId;

    private ArrayList<Student> students = null;

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
            ClassBatchProfessorInChargeResponse response = ((ClassBatchProfessorInChargeResponse) DataBase.currentProxy.load(new ClassBatchProfessorInChargeRequest(this.professorInChargeId)));
            DataBase.userHashtable.put(response.getProfessor().getUserId(), response.getProfessor());
            DataBase.professorHashtable.put(response.getProfessor().getProfessorId(), response.getProfessor());
            return response.getProfessor();
        }
    }

    public ArrayList<Student> getStudents() {
        if (this.students == null) {
            this.students = new ArrayList<>();
            ClassBatchStudentsResponse response = ((ClassBatchStudentsResponse) DataBase.currentProxy.load(new ClassBatchStudentsRequest(this.classBatchId)));
            for (Student student : response.getStudents()) {
                if (DataBase.studentHashtable.containsKey(student.getStudentId()))
                    this.students.add(DataBase.studentHashtable.get(student.getStudentId()));
                else {
                    DataBase.userHashtable.put(student.getUserId(), student);
                    DataBase.studentHashtable.put(student.getStudentId(), student);
                    this.students.add(student);
                }
            }
        }

        return this.students;
    }
}
