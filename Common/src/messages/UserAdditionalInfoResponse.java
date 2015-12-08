package messages;


import utils.socket.IMessage;

public class UserAdditionalInfoResponse implements IMessage {
    private String email = null;
    private byte[] profileImage = null;

    public UserAdditionalInfoResponse(String email, byte[] profileImage) {
        this.email = email;
        this.profileImage = profileImage;
    }

    public String getEmail() {
        return this.email;
    }

    public byte[] getProfileImage() {
        return this.profileImage;
    }
}
