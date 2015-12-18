package Models.proxy;

public interface IProxy {
    Object load(ProxyOpcode requestType, Object... params);
}
