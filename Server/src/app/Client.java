package app;

import Models.Class;
import Models.Professor;
import Models.Student;
import messages.*;
import utils.socket.DataArrivalEvent;
import utils.socket.SClient;
import utils.socket.SClientAdapter;
import utils.socket.SClientListener;
import utils.socket.message.ErrorMessage;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Client {
    private final HashMap<java.lang.Class, IMessageCallback> messagesCallback = new HashMap<>();
    public SClientListener clientEvent = new SClientAdapter() {
        @Override
        public void onDataArrival(SClient sender, DataArrivalEvent event) {
            //TODO: Si non connecté & non LoginRequest, retourne une erreur ?
            if (messagesCallback.containsKey(event.getMessageClass()))
                messagesCallback.get(event.getMessageClass()).run(event);
            else if (event.isRequest())
                event.setResponse(new ErrorMessage(0, "Request not found"));
        }

        @Override
        public void onClosed(SClient sender) {
            System.out.println("[Serveur] Deconnexion du client");
        }
    };
    public IMessageCallback onPingRequest = data -> {
        data.setResponse(new PingResponse());
    };
    public IMessageCallback onLoginRequest = data -> {
        LoginRequest msg = (LoginRequest) data.getMessage();
        System.out.println(String.format("[Serveur] LoginRequest {Login: '%s'; Password: '%s'}", msg.getLogin(), msg.getPassword()));

        //Student version
        //data.setResponse(new LoginResponse(true, new Student(1, "Nicolas", "Cage", 2)));
        //Professor version
        data.setResponse(new LoginResponse(true, new Professor(1, "Nicolas", "Cage")));
    };
    public IMessageCallback onUserRequest = data -> {
        UserRequest msg = (UserRequest) data.getMessage();
        data.setResponse(new UserResponse(new Student(1, "Jack", "pot", 1)));
    };

    ///////////////// Gestion des messages ///////////////
    public IMessageCallback onStudentClassRequest = data -> {
        StudentClassRequest msg = (StudentClassRequest) data.getMessage();
        data.setResponse(new StudentClassResponse(new Class(1, 2015, 1)));
    };
    public IMessageCallback onProfessorClassRequest = data -> {
        ProfessorClassRequest msg = (ProfessorClassRequest) data.getMessage();
        data.setResponse(new ProfessorClassResponse(new ArrayList<>(Arrays.asList(
                new Class(1, 2015, 1),
                new Class(2, 2016, 1)
        ))));
    };
    private SClient socket;

    public Client(Socket socket) {
        try {
            this.registerCallback();
            this.socket = new SClient(socket, clientEvent);
        } catch (IOException e) {
            System.err.println("[Serveur] Erreur création du client.");
            e.printStackTrace();
        }
    }

    private void registerCallback() {
        messagesCallback.put(PingRequest.class, onPingRequest);
        messagesCallback.put(LoginRequest.class, onLoginRequest);
        messagesCallback.put(UserRequest.class, onUserRequest);
        messagesCallback.put(StudentClassRequest.class, onStudentClassRequest);
        messagesCallback.put(ProfessorClassRequest.class, onProfessorClassRequest);
    }

    public interface IMessageCallback {
        void run(DataArrivalEvent e);
    }

}
