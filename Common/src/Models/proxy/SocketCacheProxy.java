package Models.proxy;

import Models.IEntity;
import utils.socket.SClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SocketCacheProxy extends SocketProxy {
    @Deprecated
    private final HashMap<String, List<?>> cache = new HashMap<>();
    private final HashMap<Class<?>, HashMap<Integer, IEntity>> elementCache = new HashMap<>();

    public SocketCacheProxy(SClient socket) {
        super(socket);
    }

    @Override
    public <T> T loadObjectById(Class<T> clazz, int id) {
        T response = this.getCache(clazz, id);
        // Si pas de cache dispo, execute la requete //
        if(response == null) {
            response = super.loadObjectById(clazz, id);

            // Si entité du type IEntity, on peut le mettre en cache //
            if(response instanceof IEntity){
                this.updateCache(clazz, (IEntity) response);
            }
        }
        return response;
    }

    @Override
    public <T> List<T> loadObjectByReverseId(Class<T> target, Class<?> source, int id) {
        // Si presence d'un cache request //
        if(cache.containsKey(target.getName() + ";" + source.getName() + ";" + id)) {
            List<T> result = new ArrayList<>();
            for(Object item : cache.get(target.getName() + ";" + source.getName() + ";" + id)) {
                result.add(this.getCache(target, ((IEntity)item).getPrimaryKey()));
            }
            return result;
        }

        List<?> response = super.loadObjectByReverseId(target, source, id);

        // Si c'est une liste IEntity, on peut mettre a jour le cache pour chacun des elements //
        if(IEntity.class.isAssignableFrom(target)) {
            for (Object entity : response) {
                this.updateCache(target, (IEntity) entity);
            }
            cache.put(target.getName() + ";" + source.getName() + ";" + id, response);
        }

        return (List<T>) response;
    }

    public void updateCache(IEntity obj) {
        this.updateCache(obj.getClass(), obj);
    }

    private void updateCache(Class<?> clazz, IEntity obj) {
        if(!elementCache.containsKey(clazz))
            elementCache.put(clazz, new HashMap<>());
        elementCache.get(clazz).put(obj.getPrimaryKey(), obj);
    }

    public <T> T getCache(Class<T> clazz, int id) {
        if(!elementCache.containsKey(clazz))
            return null;
        if(!elementCache.get(clazz).containsKey(id))
            return null;
        return clazz.cast(elementCache.get(clazz).get(id));
    }
}
