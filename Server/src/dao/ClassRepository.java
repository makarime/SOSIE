package dao;

import Models.Class;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClassRepository extends DaoBase<Class> {

    private static ClassRepository instance = null;
    public static ClassRepository getInstance() {
        if(instance == null)
            instance = new ClassRepository();
        return instance;
    }

    private static final String SElECTREQUEST = "SELECT * FROM Classes ";

    public static Class getById(int id) {
        ArrayList<Class> ar = getInstance().select(SElECTREQUEST + "WHERE ClasseId = " + id);
        return ar.size() > 0 ? ar.get(0) : null;
    }

    @Override
    public Class dataToClass(ResultSet rs) throws SQLException {
        return new Class(
                rs.getInt("ClasseId"),
                rs.getString("Nom"),
                rs.getInt("rank")
        );
    }
}
