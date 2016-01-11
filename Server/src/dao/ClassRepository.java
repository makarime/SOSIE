package dao;

import Models.Class;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClassRepository extends DaoBase<Class> {

    private static final String SElECTREQUEST = "SELECT * FROM Classes ";
    public static final String TABLENAME = "Classes";
    public interface Columns{
        String ID       = "ClasseId";
        String NAME     = "Nom";
        String RANK     = "rank";
    }

    private static ClassRepository instance = null;
    public static ClassRepository getInstance() {
        if(instance == null)
            instance = new ClassRepository();
        return instance;
    }

    public ClassRepository() {
        super(TABLENAME);
    }

    public static Class getById(int id) {
        ArrayList<Class> ar = getInstance().select(SElECTREQUEST + "WHERE ClasseId = " + id);
        return ar.size() > 0 ? ar.get(0) : null;
    }

    public static boolean update(String columnName, String newValue) {
        return getInstance().updateRow(columnName, newValue);
    }

    @Override
    public Class dataToClass(ResultSet rs) throws SQLException {
        return new Class(
                rs.getInt(Columns.ID),
                rs.getString(Columns.NAME),
                rs.getInt(Columns.RANK)
        );
    }
}
