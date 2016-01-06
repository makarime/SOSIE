package dao;

import Models.Eu;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EuRepository extends DaoBase<Eu> {

    private static EuRepository instance = null;
    public static EuRepository getInstance() {
        if(instance == null)
            instance = new EuRepository();
        return instance;
    }

    private static final String SElECTREQUEST = "SELECT * FROM Ue ";

    public static Eu getById(int id) {
        ArrayList<Eu> ar = getInstance().select(SElECTREQUEST + "WHERE UeId = " + id);
        return ar.size() > 0 ? ar.get(0) : null;
    }

    @Override
    public Eu dataToClass(ResultSet rs) throws SQLException {
        return new Eu(
                rs.getInt("UeId"),
                rs.getInt("ClassId"),
                rs.getString("Nom")
        );
    }
}
