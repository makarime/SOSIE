package Models;

import javafx.scene.image.Image;
import messages.models.UserAdditionalInfoRequest;
import messages.models.UserAdditionalInfoResponse;

import java.io.ByteArrayInputStream;
import java.io.Serializable;

public abstract class User implements Serializable, IEntity {
    protected int userId;
    private String login;
    protected String lastName = null;
    protected String firstName = null;
    protected String email = null;
    protected Image profileImage = null;

    @Override
    public int getPrimaryKey() {
        return userId;
    }

    public String toString() {
        return this.firstName + " " + this.lastName;
    }

    public int getUserId() {
        return this.userId;
    }

    public String getEmail() {
        this.loadAdditionalInformation();
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Image getProfileImage() {
        this.loadAdditionalInformation();
        return this.profileImage;
    }

    public void setProfileImage(Image image) {
        this.profileImage = image;
    }

    private void loadAdditionalInformation() {
        if ((this.profileImage == null) && (this.email == null)) {
            UserAdditionalInfoResponse response = ((UserAdditionalInfoResponse) DataBase.currentProxy.load(new UserAdditionalInfoRequest(this.userId)));
            this.profileImage = new Image(new ByteArrayInputStream(response.getProfileImage()));
            this.email = response.getEmail();
        }
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean isStudent() {
        return this instanceof Student;
    }

    public boolean isProfessor() {
        return this instanceof Professor;
    }
}
