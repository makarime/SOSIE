package Models;


import messages.models.StudentClassBatchRequest;
import messages.models.StudentClassBatchResponse;

public class Student extends User {
    private ClassBatch classBatch = null;

    public Student(int userId, String lastName, String firstName) {
        this.userId = userId;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public ClassBatch getClassBatch() {
        if (this.classBatch == null) {
            StudentClassBatchResponse response = ((StudentClassBatchResponse) DataBase.currentProxy.load(new StudentClassBatchRequest(this.getUserId())));

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
