package messages.models;


import utils.socket.IMessage;

public class UserAdditionalInfoResponse implements IMessage {
    private final byte[] profileImage;

    public UserAdditionalInfoResponse(byte[] profileImage) {
        this.profileImage = profileImage;
    }

    public byte[] getProfileImage() {
        return this.profileImage;
    }
}
