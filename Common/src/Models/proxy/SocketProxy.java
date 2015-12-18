package Models.proxy;

import Models.DataBase;
import messages.ModelProxyRequest;
import messages.ModelProxyResponse;
import utils.socket.IMessage;

public class SocketProxy implements IProxy {

    @Override
    public IMessage load(IMessage msg) {
        if(DataBase.sClient == null)
            System.err.println("sClient is null");
        return ((ModelProxyResponse) DataBase.sClient.sendRequest(new ModelProxyRequest(msg))).getValue();
    }
}
