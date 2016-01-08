package Models.proxy;

import Models.IEntity;
import utils.socket.SClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SocketCacheProxy extends SocketProxy {
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
        final String cacheKey = target.getName() + ";" + source.getName() + ";" + id;
        final List<T> response;

        // Si presence d'un cache request //
        if(cache.containsKey(cacheKey)) {
            response = new ArrayList<>();
            for(Object item : cache.get(cacheKey)) {
                response.add(this.getCache(target, ((IEntity)item).getPrimaryKey()));
            }
        } else { // Pas de presence de cache //
             response = super.loadObjectByReverseId(target, source, id);

            // Si c'est une liste IEntity, on met a jour le cache pour chacun des elements //
            if (IEntity.class.isAssignableFrom(target)) {
                for (Object entity : response) {
                    this.updateCache(target, (IEntity) entity);
                }
                cache.put(cacheKey, response);
            }
        }

        return response;
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
