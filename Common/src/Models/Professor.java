package Models;


import java.util.List;

public class Professor extends User {

    public Professor(int userId, String lastName, String firstName, String email) {
        super(userId, lastName, firstName, email);
    }

    public List<ClassBatch> getClassBatches() {
        return DataBaseEnv.currentProxy.loadObjectByReverseId(ClassBatch.class, Professor.class, this.getUserId());
    }

    public List<Course> getCourses() {
        return DataBaseEnv.currentProxy.loadObjectByReverseId(Course.class, Professor.class, userId);
    }
}
