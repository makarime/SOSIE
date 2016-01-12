package messages;


import utils.socket.IMessage;

public class ChangeUserPasswordRequest implements IMessage {
    private final String oldUserPassword;
    private final String userPassword;

    public ChangeUserPasswordRequest(String oldUserPassword, String userPassword) {
        this.oldUserPassword = oldUserPassword;
        this.userPassword = userPassword;
    }

    public String getOldUserPassword() {
        return this.oldUserPassword;
    }

    public String getUserPassword() {
        return this.userPassword;
    }
}
