package Models;

import javafx.scene.image.Image;

public class Professor extends User {
    public Professor(int id, String pseudo, String firstName, String name) {
        this.status = Status.professor;
        this.id = id;
        this.pseudo = pseudo;
        this.firstName = firstName;
        this.name = name;
    }

    public Professor(int id, String pseudo, String firstName, String name, Image profileImage) {
        this.status = Status.professor;
        this.id = id;
        this.pseudo = pseudo;
        this.firstName = firstName;
        this.name = name;
        this.profileImage = profileImage;
    }
}
