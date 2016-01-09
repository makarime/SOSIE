package utils;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventManagerList {
    public final CopyOnWriteArrayList<EventListener> listeners = new CopyOnWriteArrayList<>();

    public void add(EventListener listener) {
        listeners.add(listener);
    }

    public void remove(EventListener listener) {
        listeners.remove(listener);
    }

    public <T extends EventListener> List<T> getListeners(Class<T> clazz) {
        List<T> result = new ArrayList<>();
        for(EventListener listener : listeners) {
            if(clazz.isInstance(listener)) {
                result.add(clazz.cast(listener));
            }
        }
        return result;
    }

}
