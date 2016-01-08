package utils.socket;

public class DataArrivalEvent<T extends IMessage> {
    private final boolean request;
    private final T message;
    private IMessage response;

    public DataArrivalEvent(T msg, boolean request) {
        this.message = msg;
        this.request = request;
    }

    public T getMessage() {
        return message;
    }

    public boolean isRequest() {
        return request;
    }

    public IMessage getResponse() {
        return response;
    }

    public void setResponse(IMessage response) {
        this.response = response;
    }

    public Class getMessageClass() {
        return message.getClass();
    }
}
