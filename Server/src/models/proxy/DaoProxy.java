package models.proxy;

import Models.Class;
import Models.proxy.IProxy;
import Models.proxy.ProxyOpcode;

public class DaoProxy implements IProxy {

    @Override
    public Object load(ProxyOpcode requestType, Object... params) {
        switch (requestType) {
            case Student_GetClass:
                return new Class(1, 2015, "IATIC3", 1);
            default:
                return null;
        }
    }

}
