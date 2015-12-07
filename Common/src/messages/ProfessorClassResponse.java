package messages;

import utils.socket.IMessage;

import java.util.ArrayList;

public class ProfessorClassResponse implements IMessage{
    public final ArrayList<Class> classes;

    public ProfessorClassResponse(ArrayList<Class> classes) {
        this.classes = classes;
    }

    public ArrayList<Class> getClasses() {
        return classes;
    }
}
