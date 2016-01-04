package Models;


import messages.models.ProfessorClassBatchesRequest;
import messages.models.ProfessorClassBatchesResponse;

import java.util.ArrayList;

public class Professor extends User {
    private ArrayList<ClassBatch> classBatches = null;

    public Professor(int userId, String lastName, String firstName) {
        this.status = Status.professor;
        this.userId = userId;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public ArrayList<ClassBatch> getClassBatches() {
        if (this.classBatches == null) {
            this.classBatches = new ArrayList<>();
            ProfessorClassBatchesResponse response = ((ProfessorClassBatchesResponse) DataBase.currentProxy.load(new ProfessorClassBatchesRequest(this.getUserId())));

            for (ClassBatch classBatch : response.getClassBatches()) {
                if (DataBase.classBatchHashtable.containsKey(classBatch.getClassBatchId()))
                    this.classBatches.add(DataBase.classBatchHashtable.get(classBatch.getClassBatchId()));
                else {
                    DataBase.classBatchHashtable.put(classBatch.getClassBatchId(), classBatch);
                    this.classBatches.add(classBatch);
                }
            }
        }

        return this.classBatches;
    }
}
