package utils.socket;

public interface SClientCallback<T extends IMessage> {
    void onResult(SClient sender, T result);
}
