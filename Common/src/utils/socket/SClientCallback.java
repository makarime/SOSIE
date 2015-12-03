package utils.socket;

public interface SClientCallback {
    void onResult(SClient sender, IMessage result);
}
