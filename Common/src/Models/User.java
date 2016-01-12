package Models;

import javafx.scene.image.Image;
import messages.models.UserAdditionalInfoRequest;
import messages.models.UserAdditionalInfoResponse;

import java.io.ByteArrayInputStream;
import java.io.Serializable;

public abstract class User implements Serializable, IEntity {
    protected int userId;
    protected String lastName = null;
    protected String firstName = null;
    protected String email = null;
    protected Image profileImage = null;

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

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Image getProfileImage() {
        this.loadAdditionalInformation();
        return this.profileImage;
    }

    public void setProfileImage(Image profileImage) {
        this.profileImage = profileImage;
    }

    @Override
    public String toString() {
        return this.firstName + " " + this.lastName;
    }

    private void loadAdditionalInformation() {
        if (this.profileImage == null) {
            UserAdditionalInfoResponse response = ((UserAdditionalInfoResponse) DataBaseEnv.currentProxy.load(new UserAdditionalInfoRequest(this.userId)));
            this.profileImage = new Image(new ByteArrayInputStream(response.getProfileImage()));
        }
    }

    public boolean isStudent() {
        return this instanceof Student;
    }

    public boolean isProfessor() {
        return this instanceof Professor;
    }
}
