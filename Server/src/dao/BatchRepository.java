package dao;

import Models.Batch;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BatchRepository extends DaoBase<Batch> {

    private static final String SElECTREQUEST = "SELECT * FROM Promotion ";
    private static final String TABLENAME = "Promotion";

    private static BatchRepository instance = null;
    public static BatchRepository getInstance() {
        if(instance == null)
            instance = new BatchRepository();
        return instance;
    }

    public BatchRepository() {
        super(TABLENAME);
    }

    public static Batch getById(int id) {
        ArrayList<Batch> ar = getInstance().select(SElECTREQUEST + "WHERE idPromotion = " + id);
        return ar.size() > 0 ? ar.get(0) : null;
    }

    @Override
    public Batch dataToClass(ResultSet rs) throws SQLException {
        return new Batch(
                rs.getInt("idPromotion"),
                rs.getInt("Annee")
        );
    }
}
