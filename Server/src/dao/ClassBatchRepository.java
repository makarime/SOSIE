package dao;

import Models.Batch;
import Models.ClassBatch;
import Models.Professor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassBatchRepository extends DaoBase<ClassBatch> {

    private static final String SElECTREQUEST = "SELECT * FROM ClassePromo ";
    public static final String TABLENAME = "ClassePromo";
    public interface Columns{
        String ID           = "PromoClassId";
        String CLASSID      = "ClassId";
        String BATCHID      = "PromotionId";
        String PROFESSORID  = "ResponsableId";
    }

    private static ClassBatchRepository instance = null;
    public static ClassBatchRepository getInstance() {
        if(instance == null)
            instance = new ClassBatchRepository();
        return instance;
    }

    public ClassBatchRepository() {
        super(TABLENAME);
    }

    public static ClassBatch getById(int id) {
        ArrayList<ClassBatch> ar = getInstance().select(SElECTREQUEST + "WHERE PromoClassId = " + id);
        return ar.size() > 0 ? ar.get(0) : null;
    }

    public static List<ClassBatch> getByReverseId(Class<?> clazz, int id) {
        String column = null;
        if(clazz == Batch.class)        column = "PromotionId";
        if(clazz == Models.Class.class) column = "ClasseId";
        if(clazz == Professor.class)    column = "ResponsableId";
        if(column == null) {
            System.err.println(String.format("%s.getByReverseId: Class not found : %s",
                    getInstance().getClass().getSimpleName(), clazz.getClass().getSimpleName()));
            return null;
        }
        return getInstance().select(SElECTREQUEST + "WHERE " + column + " = " + id);
    }

    public static boolean update(String columnName, String newValue) {
        return getInstance().updateRow(columnName, newValue);
    }

    @Override
    public ClassBatch dataToClass(ResultSet rs) throws SQLException {
        return new ClassBatch(
                rs.getInt(Columns.ID),
                rs.getInt(Columns.CLASSID),
                rs.getInt(Columns.BATCHID),
                rs.getInt(Columns.PROFESSORID)
        );
    }
}
