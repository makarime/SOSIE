package dao;

import Models.Professor;
import Models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository extends DaoBase<User>{

    private static final String SElECTREQUEST = "SELECT * FROM Utilisateurs " +
            "  LEFT JOIN Eleves ON Eleves.EleveId = Utilisateurs.IdUtilisateur " +
            "  LEFT JOIN Enseignants ON Enseignants.EnseignantId = Utilisateurs.IdUtilisateur ";
    public static final String TABLENAME = "Utilisateurs"; //TODO Erreur heritage
    public interface Columns{
        String ID           = "IdUtilisateur";
        String FIRSTNAME    = "FirstName";
        String LASTNAME     = "LastName";
        String EMAIL        = "Email";
        String URLIMAGE     = "Photo";
    }

    private static UserRepository instance = null;
    public static UserRepository getInstance() {
        if(instance == null)
            instance = new UserRepository();
        return instance;
    }


    public UserRepository() {
        super(TABLENAME, Columns.ID);
    }

    public static User getById(int id) {
        ArrayList<User> ar = getInstance().select(SElECTREQUEST + "WHERE IdUtilisateur = " + id);
        return ar.size() > 0 ? ar.get(0) : null;
    }

    public static User getByCredential(String login, String password) {
        ArrayList<User> ar = getInstance().select(SElECTREQUEST + "WHERE Login = ? AND Mdp = ?", login, password);
        return ar.size() > 0 ? ar.get(0) : null;
    }

    public static List<User> getById(int[] ids) {
        if(ids.length == 0) return new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < ids.length; i++) {
            builder.append(Integer.toString(ids[i]));
            if(i + 1 != ids.length) builder.append(", ");
        }
        return getInstance().select(SElECTREQUEST + "WHERE IdUtilisateur IN (" + builder.toString() + ")");
    }

    public static boolean update(Integer id, String columnName, String newValue) {
        return getInstance().updateRow(columnName, newValue, id);
    }

    public static boolean changePassword(int id, String oldPassword, String newPassword) {
        if(getInstance().select(SElECTREQUEST + "WHERE IdUtilisateur = ? AND MdP = ?", id, oldPassword).size() != 1)
            return false;
        return getInstance().updateRow("MdP", newPassword, id);
    }

    @Override
    public User dataToClass(ResultSet data) throws SQLException {
        if(data.getObject("EleveId") != null) {
            return StudentRepository.getInstance().dataToClass(data);
        } else if(data.getObject("EnseignantId") != null)  {
            return new Professor(
                    data.getInt(Columns.ID),
                    data.getString(Columns.LASTNAME),
                    data.getString(Columns.FIRSTNAME),
                    data.getString(Columns.EMAIL));
        } else {
            System.err.println(String.format("Unknown user (Id: %d, Login: %s)", data.getInt("IdUtilisateur"), data.getString("Login")));
            return null;
        }
    }
}
