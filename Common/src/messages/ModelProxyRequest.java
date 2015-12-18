package messages;

import Models.proxy.ProxyOpcode;
import utils.socket.IMessage;

public class ModelProxyRequest implements IMessage {
    private final ProxyOpcode requestType;
    private final Object[] params;

    public ModelProxyRequest(ProxyOpcode requestType, Object[] params) {
        this.requestType = requestType;
        this.params = params;
    }

    public ProxyOpcode getRequestType() {
        return requestType;
    }

    public Object[] getParams() {
        return params;
    }

}
