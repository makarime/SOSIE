package utils.socket;

public class DataArrivalEvent {
    private final boolean request;
    private final IMessage message;
    private IMessage response;

    public DataArrivalEvent(IMessage msg, boolean request) {
        this.message = msg;
        this.request = request;
    }

    public IMessage getMessage() {
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
