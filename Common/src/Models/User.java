package Models;

import javafx.scene.image.Image;

import java.io.Serializable;
import java.nio.file.Paths;

public abstract class User implements Serializable {
    protected Status status = null;
    protected int id;
    protected String pseudo = null;
    protected String firstName = null;
    protected String name = null;
    protected String email = null;
    protected Image profileImage = null;

    public boolean isStudent() {
        return this.status == Status.student;
    }

    public boolean isProfessor() {
        return this.status == Status.professor;
    }

    public String getPseudo() {
        return this.pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

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
        //TODO server request

        if (this.profileImage == null)
            this.profileImage = new Image(Paths.get("IHM_Utilisateur\\src\\Nicolas Cage.jpg").toUri().toString());

        if (this.email == null)
            this.email = "test@gmail.com";
    }

    protected enum Status {
        professor,
        student
    }
}
