package Models;

import messages.models.UserAdditionalInfoRequest;
import messages.models.UserAdditionalInfoResponse;

import java.io.Serializable;

public abstract class User implements Serializable, IEntity {
    protected int userId;
    protected String lastName = null;
    protected String firstName = null;
    protected String email = null;
    protected byte[] profileImageByteArray = null;

    public User(int userId, String lastName, String firstName, String email) {
        this.userId = userId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
    }

    @Override
    public int getPrimaryKey() {
        return userId;
    }

    public int getUserId() {
        return this.userId;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getName() {
        return this.lastName + " " + this.firstName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getProfileImageByteArray() {
        this.loadAdditionalInformation();
        return this.profileImageByteArray;
    }

    public void setProfileImageByteArray(byte[] profileImageByteArray) {
        this.profileImageByteArray = profileImageByteArray;
    }

    @Override
    public String toString() {
        return this.firstName + " " + this.lastName;
    }

    private void loadAdditionalInformation() {
        if (this.profileImageByteArray == null) {
            UserAdditionalInfoResponse response = ((UserAdditionalInfoResponse) DataBaseEnv.currentProxy.load(new UserAdditionalInfoRequest(this.userId)));
            this.profileImageByteArray = response.getProfileImage();
        }
    }

    public boolean isStudent() {
        return this instanceof Student;
    }

    public boolean isProfessor() {
        return this instanceof Professor;
    }
}
