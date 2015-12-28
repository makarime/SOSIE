package messages.models;


import utils.socket.IMessage;

public class ChangeUserProfileImageRequest implements IMessage {
    private final int userId;
    private final byte[] profileImage;

    public ChangeUserProfileImageRequest(int userId, byte[] profileImage) {
        this.userId = userId;
        this.profileImage = profileImage;
    }

    public int getUserId() {
        return userId;
    }

    public byte[] getProfileImage() {
        return profileImage;
    }
}
