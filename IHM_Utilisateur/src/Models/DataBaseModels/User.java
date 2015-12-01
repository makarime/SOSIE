package Models.DataBaseModels;

import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class User {
    protected enum Status {
        professor,
        student
    }
    protected Status status = null;

    protected int id;
    protected String pseudo = null;
    protected String firstName = null;
    protected String name = null;
    protected String email = null;

    protected byte[] password = null;
    protected Image profileImage = null;

    public boolean isStudent() {
        if (this.status == Status.student)
            return true;

        return false;
    }

    public boolean isProfessor(){
        if (this.status == Status.professor)
            return true;

        return false;
    }

    public String toString() {
        return this.firstName + " " + this.name;
    }

    public int getId()
    {
        return this.id;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getEmail() {
        this.loadAdditionalInformation();
        return this.email;
    }

    public void setProfileImage(Image image)
    {
        this.profileImage = image;
    }

    public Image getProfileImage() {
        this.loadAdditionalInformation();
        return this.profileImage;
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
}
