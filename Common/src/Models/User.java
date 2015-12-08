package Models;

import javafx.scene.image.Image;
import messages.UserAdditionalInfoRequest;
import messages.UserAdditionalInfoResponse;

import java.io.ByteArrayInputStream;
import java.io.Serializable;

public abstract class User implements Serializable {
    protected Status status = null;
    protected int id;
    protected String firstName = null;
    protected String name = null;
    protected String email = null;
    protected Image profileImage = null;

    public String toString() {
        return this.firstName + " " + this.name;
    }

    public int getId() {
        return this.id;
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
            UserAdditionalInfoResponse userAdditionalInfoResponse = ((UserAdditionalInfoResponse) DataBase.sClient.sendRequest(new UserAdditionalInfoRequest(this.id)));
            this.profileImage = new Image(new ByteArrayInputStream(userAdditionalInfoResponse.getProfileImage()));
            this.email = userAdditionalInfoResponse.getEmail();
        }
    }

    public boolean isStudent() {
        return this.status == Status.student;
    }

    public boolean isProfessor() {
        return this.status == Status.professor;
    }

    protected enum Status {
        professor,
        student
    }
}
