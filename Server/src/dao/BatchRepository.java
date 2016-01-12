package dao;

import Models.Batch;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BatchRepository extends DaoBase<Batch> {

    private static final String SElECTREQUEST = "SELECT * FROM Promotion ";
    public static final String TABLENAME = "Promotion";
    public interface Columns{
        String ID   = "IdPromotion";
        String YEAR = "Annee";
    }

    private static BatchRepository instance = null;
    public static BatchRepository getInstance() {
        if(instance == null)
            instance = new BatchRepository();
        return instance;
    }

    public BatchRepository() {
        super(TABLENAME, Columns.ID);
    }

    public static Batch getById(int id) {
        ArrayList<Batch> ar = getInstance().select(SElECTREQUEST + "WHERE idPromotion = " + id);
        return ar.size() > 0 ? ar.get(0) : null;
    }

    public static boolean update(Integer id, String columnName, String newValue) {
        return getInstance().updateRow(columnName, newValue, id);
    }

    @Override
    public Batch dataToClass(ResultSet rs) throws SQLException {
        return new Batch(
                rs.getInt(Columns.ID),
                rs.getInt(Columns.YEAR)
        );
    }
}
