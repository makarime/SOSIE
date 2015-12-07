package Models;


import utils.socket.SClient;

import java.util.Hashtable;

public class DataBase {
    public static SClient sClient = null;
    public static Hashtable<Integer, User> users = new Hashtable<>();
    public static Hashtable<Integer, Class> classes = new Hashtable<>();
}
