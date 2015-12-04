package Models;

import javafx.scene.image.Image;

public class Student extends User {
    private int idClass;

    public Student(int id, String pseudo, String firstName, String name, int idClass) {
        this.status = Status.student;
        this.id = id;
        this.pseudo = pseudo;
        this.firstName = firstName;
        this.name = name;
        this.idClass = idClass;
    }

    public Student(int id, String pseudo, String firstName, String name, Image profileImage, int idClass) {
        this.status = Status.student;
        this.id = id;
        this.pseudo = pseudo;
        this.firstName = firstName;
        this.name = name;
        this.profileImage = profileImage;
        this.idClass = idClass;
    }
}
