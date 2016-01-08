package messages;


import utils.socket.IMessage;

public class ChangeUserEmailRequest implements IMessage {
    private final String email;

    public ChangeUserEmailRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }
}
