package dao;

import Models.Student;
import Models.StudentClassBatch;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public static List<StudentClassBatch> getByReverseId(Class<?> clazz, int id) {
        String column = null;
        if(clazz == Student.class) column = "EleveId";
        if(column == null) {
            System.err.println(String.format("%s.getByReverseId: Class not found : %s",
                    getInstance().getClass().getSimpleName(), clazz.getClass().getSimpleName()));
            return null;
        }
        return getInstance().select(SElECTREQUEST + "WHERE " + column + " = " + id);
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
