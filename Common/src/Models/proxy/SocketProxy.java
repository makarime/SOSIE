package Models.proxy;

import Models.DataBase;
import messages.ModelProxyRequest;
import messages.ModelProxyResponse;

public class SocketProxy implements IProxy {

    public Object load(ProxyOpcode requestType, Object... params) {
        if(DataBase.sClient == null)
            System.err.println("sClient is null");
        return ((ModelProxyResponse) DataBase.sClient.sendRequest(new ModelProxyRequest(requestType, params))).getValue();
    }

}
