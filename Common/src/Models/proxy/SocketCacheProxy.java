package Models.proxy;

import messages.models.*;
import utils.socket.IMessage;
import utils.socket.SClient;

import java.util.HashMap;
import java.util.List;

public class SocketCacheProxy extends SocketProxy {
    private final HashMap<String, Object> cache = new HashMap<>();

    public SocketCacheProxy(SClient socket) {
        super(socket);
    }

    @Override
    public <T> T loadObjectById(Class<T> clazz, int id) {
        //TODO Mettre un timeout sur le cache; possibilité de le vider etc
        if(cache.containsKey(clazz.getName() + ";" + id))
            return clazz.cast(cache.get(clazz.getName() + ";" + id));

        Object response = super.loadObjectById(clazz, id);
        cache.put(clazz.getName() + ";" + id, response);
        return clazz.cast(response);
    }

    @Override
    public <T> List<T> loadObjectByReverseId(Class<T> target, Class<?> source, int id) {
        if(cache.containsKey(target.getName() + ";" + source.getName() + ";" + id))
            return (List<T>) cache.get(target.getName() + ";" + source.getName() + ";" + id);

        Object response = super.loadObjectByReverseId(target, source, id);
        cache.put(target.getName() + ";" + source.getName() + ";" + id, response);
        return (List<T>) response;
    }
}
