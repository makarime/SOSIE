package Models;

import messages.models.StudentClassBatchRequest;
import messages.models.StudentClassBatchResponse;

public class Student extends User {
    private ClassBatch currentCB = null;

    public Student(int userId, String lastName, String firstName) {
        this.userId = userId;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public ClassBatch getClassBatch() {
        if (this.currentCB == null) {
            StudentClassBatchResponse response = ((StudentClassBatchResponse) DataBase.currentProxy.load(new StudentClassBatchRequest(this.getUserId())));

            if (DataBase.classBatchHashtable.containsKey(response.getClassBatch().getClassBatchId()))
                this.currentCB = DataBase.classBatchHashtable.get(response.getClassBatch().getClassBatchId());
            else {
                DataBase.classBatchHashtable.put(response.getClassBatch().getClassBatchId(), response.getClassBatch());
                this.currentCB = response.getClassBatch();
            }
        }

        return this.currentCB;
    }
}
