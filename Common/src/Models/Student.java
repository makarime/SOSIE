package Models;


import messages.models.StudentClassBatchRequest;
import messages.models.StudentClassBatchResponse;

public class Student extends User {
    private int studentId;
    private ClassBatch classBatch = null;

    public Student(int userId, int studentId, String lastName, String firstName) {
        this.status = Status.student;
        this.userId = userId;
        this.studentId = studentId;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public int getStudentId() {
        return this.studentId;
    }

    public ClassBatch getClassBatch() {
        if (this.classBatch == null) {
            StudentClassBatchResponse response = ((StudentClassBatchResponse) DataBase.currentProxy.load(new StudentClassBatchRequest(this.studentId)));

            if (DataBase.classBatchHashtable.containsKey(response.getClassBatch().getClassBatchId()))
                this.classBatch = DataBase.classBatchHashtable.get(response.getClassBatch().getClassBatchId());
            else {
                DataBase.classBatchHashtable.put(response.getClassBatch().getClassBatchId(), response.getClassBatch());
                this.classBatch = response.getClassBatch();
            }
        }

        return this.classBatch;
    }
}
