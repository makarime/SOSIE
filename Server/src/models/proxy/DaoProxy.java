package models.proxy;

import Models.Class;
import Models.*;
import Models.proxy.IProxy;
import messages.models.*;
import utils.socket.IMessage;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DaoProxy implements IProxy {
    private final HashMap<java.lang.Class, IMessageCallback> messagesCallback = new HashMap<>();
    public interface IMessageCallback {
        IMessage run(IMessage e);
    }

    public DaoProxy() {
        messagesCallback.put(UserAdditionalInfoRequest.class, onUserAdditionalInfoRequest);
        messagesCallback.put(StudentClassBatchRequest.class, onStudentClassBatchRequest);
    }

    @Override
    public IMessage load(IMessage msg) {
        if (messagesCallback.containsKey(msg.getClass()))
            return messagesCallback.get(msg.getClass()).run(msg);
        System.err.println("[DaoProxy] Message not found: " + msg.getClass().getName());
        return null;
    }

    @Override
    public <T> T loadObjectById(java.lang.Class<T> clazz, int id) {
        if(clazz == Professor.class) return clazz.cast(new Professor(0, "Marc", "Landers"));
        if(clazz == ClassBatch.class) return clazz.cast(new ClassBatch(0, 0, 0, 0));
        if(clazz == Classroom.class) return clazz.cast(new Classroom(0,0,30,true,true,true,true));
        if(clazz == Subject.class) return clazz.cast(new Subject(0,0,"EU"));
        if(clazz == Class.class) return clazz.cast(new Class("Class", 1));
        return null;
    }

    @Override
    public <T> List<T> loadObjectByReverseId(java.lang.Class<T> target, java.lang.Class<?> source, int id) {
        if(target == ClassBatch.class) return (List<T>) new ArrayList<>(Arrays.asList(new ClassBatch(0, 0, 0, 0), new ClassBatch(1, 1, 1, 0)));
        if(target == Eu.class) return (List<T>) new ArrayList<>(Arrays.asList(new Eu(0, 0, "Eu1"), new Eu(1, 0, "Eu2")));
        if(target == Student.class) return (List<T>) new ArrayList<>(Arrays.asList(new Student(1, "toto", "titi"), new Student(2, "aaaa", "bbbb")));
        if(target == Course.class) return (List<T>) new ArrayList<>(Arrays.asList(new Course(0,0,0,0,0,null), new Course(1,0,0,0,0,null)));
        if(target == Subject.class) return (List<T>) new ArrayList<>(Arrays.asList(new Subject(0,0,"mat1"), new Subject(1,0,"mat2")));
        return null;
    }

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

    public IMessageCallback onStudentClassBatchRequest = data -> {
        StudentClassBatchRequest msg = (StudentClassBatchRequest) data;
        return new StudentClassBatchResponse(new ClassBatch(0, 0, 0, 0));
    };

}
