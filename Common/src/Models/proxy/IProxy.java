package Models.proxy;

import utils.socket.IMessage;

import java.util.List;

public interface IProxy {
    IMessage load(IMessage msg);
    <T> T loadObjectById(Class<T> clazz, int id);
    <T> List<T> loadObjectByReverseId(Class<T> target, Class<?> source, int id);
}
