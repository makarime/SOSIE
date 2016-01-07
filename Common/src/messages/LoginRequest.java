package messages;

import utils.socket.IMessage;

public class LoginRequest implements IMessage {
    private final String login;
    private final String passwordHash;

    public LoginRequest(String login, String passwordHash) {
        this.login = login;
        this.passwordHash = passwordHash;
    }

    public String getLogin() {
        return login;
    }

    public String getPasswordHash() {
        return passwordHash;
    }
}
