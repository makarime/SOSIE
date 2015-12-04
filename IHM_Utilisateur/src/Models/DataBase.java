package Models;


import java.util.Hashtable;

public class DataBase {
    public static Hashtable<Integer, User> users = new Hashtable<>();
    public static Hashtable<Integer, Class> classes = new Hashtable<>();

    public User getUser(Integer id) {
        User user = DataBase.users.get(id);

        if (user == null) {
            //AppUser.sClient.sendRequest(new )
        }

        return user;
    }

    public Class getClass(Integer id) {
        Class c = DataBase.classes.get(id);

        if (c == null) {
            //TODO database request
        }

        return c;
    }
}
