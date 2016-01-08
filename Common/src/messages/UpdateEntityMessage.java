package messages;

import Models.IEntity;
import utils.socket.IMessage;

public class UpdateEntityMessage implements IMessage {
    private final IEntity entity;

    public UpdateEntityMessage(IEntity entity) {
        this.entity = entity;
    }

    public IEntity getEntity() {
        return entity;
    }
}
