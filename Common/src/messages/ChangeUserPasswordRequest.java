package messages;


import utils.socket.IMessage;

public class ChangeUserPasswordRequest implements IMessage {
    private final String userPassword;

    public ChangeUserPasswordRequest(String userPassword) {
        this.userPassword = userPassword;
    }


    public String getUserPassword() {
        return this.userPassword;
    }
}
