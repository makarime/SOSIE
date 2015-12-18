package models.proxy;

import Models.Class;
import Models.Student;
import Models.proxy.IProxy;
import messages.models.*;
import utils.socket.IMessage;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class DaoProxy implements IProxy {
    private final HashMap<java.lang.Class, IMessageCallback> messagesCallback = new HashMap<>();
    public interface IMessageCallback {
        IMessage run(IMessage e);
    }

    public DaoProxy() {
        messagesCallback.put(StudentClassRequest.class, onStudentClassRequest);
        messagesCallback.put(ProfessorClassRequest.class, onProfessorClassRequest);
        messagesCallback.put(ClassStudentRequest.class, onClassStudentRequest);
        messagesCallback.put(UserAdditionalInfoRequest.class, onUserAdditionalInfoRequest);
    }

    @Override
    public IMessage load(IMessage msg) {
        if (messagesCallback.containsKey(msg.getClass()))
            return messagesCallback.get(msg.getClass()).run(msg);
        System.err.println("[DaoProxy] Message not found: " + msg.getClass().getName());
        return null;
    }

    public IMessageCallback onStudentClassRequest = data -> {
        StudentClassRequest msg = (StudentClassRequest) data;
        return new StudentClassResponse(new Class(1, 2015, "IATIC3", 1));
    };

    public IMessageCallback onProfessorClassRequest = data -> {
        ProfessorClassRequest msg = (ProfessorClassRequest) data;
        return new ProfessorClassResponse(new ArrayList<>(Arrays.asList(
                new Class(2, 2015, "IATIC4", 1),
                new Class(3, 2016, "IATIC5", 1)
        )));
    };

    public IMessageCallback onClassStudentRequest = data -> {
        ClassStudentRequest msg = (ClassStudentRequest) data;
        return new ClassStudentResponse(new ArrayList<>(Arrays.asList(
                new Student(3, "Nicolas", "Cage", 1),
                new Student(4, "Jack", "pot", 2))
        ));
    };

    public IMessageCallback onUserAdditionalInfoRequest = data -> {
        UserAdditionalInfoRequest msg = (UserAdditionalInfoRequest) data;
        try {
            return new UserAdditionalInfoResponse("test@gmail.com", Files.readAllBytes(Paths.get("IHM_Utilisateur\\src\\Nicolas Cage.jpg")));
        } catch (Exception e) {
            e.printStackTrace();
            //TODO handle image fail
            return null;
        }
    };

}
