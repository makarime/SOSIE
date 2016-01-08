package messages;


import utils.socket.IMessage;

public class ChangeUserProfileImageRequest implements IMessage {
    private final byte[] profileImage;

    public ChangeUserProfileImageRequest(byte[] profileImage) {
        this.profileImage = profileImage;
    }

    public byte[] getProfileImage() {
        return profileImage;
    }
}
