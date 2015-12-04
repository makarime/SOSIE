package utils.socket.message;

import utils.socket.IMessage;

public class ErrorMessage implements IMessage {

    private int id;
    private String description;

    public ErrorMessage(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

}
