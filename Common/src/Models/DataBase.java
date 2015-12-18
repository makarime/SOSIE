package Models;


import Models.proxy.SocketProxy;
import Models.proxy.IProxy;
import utils.socket.SClient;

import java.util.Hashtable;

public class DataBase {
    public static IProxy currentProxy = new SocketProxy();
    public static SClient sClient = null; //TODO: A Voir si on laisse l'instance ici?
    public static Hashtable<Integer, User> users = new Hashtable<>();
    public static Hashtable<Integer, Class> classes = new Hashtable<>();
}
