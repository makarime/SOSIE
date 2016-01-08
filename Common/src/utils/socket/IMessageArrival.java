package utils.socket;

public interface IMessageArrival<T extends IMessage> {
    void onMessageArrival(SClient sender, DataArrivalEvent<T> event);
}
