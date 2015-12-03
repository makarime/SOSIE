package utils.socket;

public class DataArrivalEvent {
    private IMessage message;
    public IMessage response;
    private boolean request;

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
}
