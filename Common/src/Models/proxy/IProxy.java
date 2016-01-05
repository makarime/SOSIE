package Models.proxy;

import utils.socket.IMessage;

public interface IProxy {
    IMessage load(IMessage msg);
    <T> T loadObjectById(Class<T> clazz, int id);
}
