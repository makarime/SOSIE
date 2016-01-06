package dao;

import Models.StudentClassBatch;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentClassBatchRepository extends DaoBase<StudentClassBatch> {

    private static StudentClassBatchRepository instance = null;
    public static StudentClassBatchRepository getInstance() {
        if(instance == null)
            instance = new StudentClassBatchRepository();
        return instance;
    }

    private static final String SElECTREQUEST = "SELECT * FROM EleveCP ";

    public static StudentClassBatch getById(int id) {
        ArrayList<StudentClassBatch> ar = getInstance().select(SElECTREQUEST + "WHERE EleveCPId = " + id);
        return ar.size() > 0 ? ar.get(0) : null;
    }

    @Override
    public StudentClassBatch dataToClass(ResultSet rs) throws SQLException {
        return new StudentClassBatch(
                rs.getInt("EleveCPId"),
                rs.getInt("EleveId"),
                rs.getInt("ClassPromoId")
        );
    }
}
