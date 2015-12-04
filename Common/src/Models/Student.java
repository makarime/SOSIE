package Models;

import javafx.scene.image.Image;

public class Student extends User {
    public Student(int id, String firstName, String name) {
        this.status = Status.student;
        this.id = id;
        this.firstName = firstName;
        this.name = name;
    }

    public Student(int id, String firstName, String name, Image profileImage) {
        this.status = Status.student;
        this.id = id;
        this.firstName = firstName;
        this.name = name;
        this.profileImage = profileImage;
    }
}
