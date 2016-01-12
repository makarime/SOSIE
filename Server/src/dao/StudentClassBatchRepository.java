package dao;

import Models.ClassBatch;
import Models.Student;
import Models.StudentClassBatch;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentClassBatchRepository extends DaoBase<StudentClassBatch> {

    private static final String SElECTREQUEST = "SELECT * FROM EleveCP ";
    public static final String TABLENAME = "EleveCP";
    public interface Columns{
        String ID           = "EleveCPId";
        String STUDENTID    = "EleveId";
        String CLASSBATCHID = "ClassPromoId";
    }

    private static StudentClassBatchRepository instance = null;
    public static StudentClassBatchRepository getInstance() {
        if(instance == null)
            instance = new StudentClassBatchRepository();
        return instance;
    }

    public StudentClassBatchRepository() {
        super(TABLENAME, Columns.ID);
    }

    public static StudentClassBatch getById(int id) {
        ArrayList<StudentClassBatch> ar = getInstance().select(SElECTREQUEST + "WHERE EleveCPId = " + id);
        return ar.size() > 0 ? ar.get(0) : null;
    }

    public static List<StudentClassBatch> getByReverseId(Class<?> clazz, int id) {
        String column = null;
        if(clazz == Student.class)      column = "EleveId";
        if(clazz == ClassBatch.class)   column = "ClassPromoId";
        if(column == null) {
            System.err.println(String.format("%s.getByReverseId: Class not found : %s",
                    getInstance().getClass().getSimpleName(), clazz.getClass().getSimpleName()));
            return null;
        }
        return getInstance().select(SElECTREQUEST + "WHERE " + column + " = " + id);
    }

    public static boolean update(Integer id, String columnName, String newValue) {
        return getInstance().updateRow(columnName, newValue, id);
    }

    @Override
    public StudentClassBatch dataToClass(ResultSet rs) throws SQLException {
        return new StudentClassBatch(
                rs.getInt(Columns.ID),
                rs.getInt(Columns.STUDENTID),
                rs.getInt(Columns.CLASSBATCHID)
        );
    }
}
