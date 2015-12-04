package Models;

import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class User implements Serializable {
    protected Status status = null;
    protected int id;
    protected String firstName = null;
    protected String name = null;
    protected String email = null;
    protected Image profileImage = null;

    public boolean isStudent() {
        return this.status == Status.student;

    }

    public boolean isProfessor(){
        return this.status == Status.professor;

    }

    public String toString() {
        return this.firstName + " " + this.name;
    }

    public int getId()
    {
        return this.id;
    }

    public String getEmail() {
        this.loadAdditionalInformation();
        return this.email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public Image getProfileImage() {
        this.loadAdditionalInformation();
        return this.profileImage;
    }

    public void setProfileImage(Image image)
    {
        this.profileImage = image;
    }

    private void loadAdditionalInformation(){
        if (this.profileImage == null) {
            //Exemple with byte array (what will probably be received from the database)
            byte[] byteArrayImage = null;

            try {
                byteArrayImage = Files.readAllBytes(Paths.get("IHM_Utilisateur\\src\\Nicolas Cage.jpg"));
            } catch (Exception e) {
                e.printStackTrace();
            }

            this.profileImage = new Image(new ByteArrayInputStream(byteArrayImage));
        }

        if (this.email == null)
            this.email = "test@gmail.com";
    }

    protected enum Status {
        professor,
        student
    }
}
