package Models;

import Models.DataBaseModels.Professor;
import Models.DataBaseModels.User;

public class AppUser {
    public static User user = null;

    public static boolean IsInDataBase(String userName, String password) {
        AppUser.user = new Professor(1, "Nicolas", "Cage");
        return true;
    }
}
