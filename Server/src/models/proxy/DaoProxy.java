package models.proxy;

import Models.*;
import Models.Class;
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
        messagesCallback.put(UserAdditionalInfoRequest.class, onUserAdditionalInfoRequest);
        messagesCallback.put(ClassBatchProfessorInChargeRequest.class, onClassBatchProfessorInChargeRequest);
        messagesCallback.put(ClassBatchStudentsRequest.class, onClassBatchStudentsRequest);
        messagesCallback.put(ProfessorClassBatchesRequest.class, onProfessorClassBatchesRequest);
        messagesCallback.put(StudentClassBatchRequest.class, onStudentClassBatchRequest);
        messagesCallback.put(SubjectEuRequest.class, onSubjectEuRequest);
        messagesCallback.put(EuClassRequest.class, onEuClassRequest);
        messagesCallback.put(EuSubjectsRequest.class, onEuSubjectsRequest);
    }

    @Override
    public IMessage load(IMessage msg) {
        if (messagesCallback.containsKey(msg.getClass()))
            return messagesCallback.get(msg.getClass()).run(msg);
        System.err.println("[DaoProxy] Message not found: " + msg.getClass().getName());
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

    public IMessageCallback onClassBatchProfessorInChargeRequest = data -> {
        ClassBatchProfessorInChargeRequest msg = (ClassBatchProfessorInChargeRequest) data;
        return new ClassBatchProfessorInChargeResponse(new Professor(0, "Marc", "Landers"));
    };

    public IMessageCallback onClassBatchStudentsRequest = data -> {
        ClassBatchStudentsRequest msg = (ClassBatchStudentsRequest) data;
        return new ClassBatchStudentsResponse(new ArrayList<>(Arrays.asList(new Student(1, "toto", "titi"), new Student(2, "aaaa", "bbbb"))));
    };

    public IMessageCallback onProfessorClassBatchesRequest = data -> {
        ProfessorClassBatchesRequest msg = (ProfessorClassBatchesRequest) data;
        return new ProfessorClassBatchesResponse(new ArrayList<>(Arrays.asList(new ClassBatch(0, 0, 0, 0), new ClassBatch(1, 1, 1, 0))));
    };

    public IMessageCallback onStudentClassBatchRequest = data -> {
        StudentClassBatchRequest msg = (StudentClassBatchRequest) data;
        return new StudentClassBatchResponse(new ClassBatch(0, 0, 0, 0));
    };

    public IMessageCallback onSubjectEuRequest = data -> {
        SubjectEuRequest msg = (SubjectEuRequest) data;
        return new SubjectEuResponse(new Eu(0, 0, "EU"));
    };

    public IMessageCallback onEuClassRequest = data -> {
        EuClassRequest msg = (EuClassRequest) data;
        return new EuClassResponse(new Class("Class", 1));
    };

    public IMessageCallback onEuSubjectsRequest = data -> {
        EuSubjectsRequest msg = (EuSubjectsRequest) data;
        return new EuSubjectsResponse(new ArrayList<>(Arrays.asList(new Subject(0, 0, "MATIERE"), new Subject(1, 0, "MATIERE"))));
    };

}
