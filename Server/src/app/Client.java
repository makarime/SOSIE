package app;

import Models.DataBase;
import Models.Student;
import Models.User;
import dao.UserRepository;
import messages.*;
import messages.models.*;
import utils.socket.DataArrivalEvent;
import utils.socket.SClient;
import utils.socket.SClientAdapter;
import utils.socket.SClientListener;
import utils.socket.message.ErrorMessage;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

public class Client {
    private final HashMap<java.lang.Class, IMessageCallback> messagesCallback = new HashMap<>();
    private SClient socket;

    public interface IMessageCallback {
        void run(DataArrivalEvent e);
    }

    public Client(Socket socket) {
        try {
            this.registerCallback();
            this.socket = new SClient(socket, clientEvent);
        } catch (IOException e) {
            System.err.println("[Serveur] Erreur création du client.");
            e.printStackTrace();
        }
    }

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

    ///////////////// Gestion des messages ///////////////

    private void registerCallback() {
        messagesCallback.put(ProxyRequest.class, onProxyRequest);
        messagesCallback.put(ProxyIdRequest.class, onProxyIdRequest);
        messagesCallback.put(ProxyReverseIdRequest.class, onProxyReverseIdRequest);
        messagesCallback.put(PingRequest.class, onPingRequest);
        messagesCallback.put(LoginRequest.class, onLoginRequest);
        messagesCallback.put(ChangeUserEmailRequest.class, onChangeUserEmailRequest);
        messagesCallback.put(ChangeUserPasswordRequest.class, onChangeUserPasswordRequest);
        messagesCallback.put(ChangeUserProfileImageRequest.class, onChangeUserProfileImageRequest);
    }

    public IMessageCallback onProxyRequest = data -> {
        data.setResponse(new ProxyResponse(DataBase.currentProxy.load(((ProxyRequest) data.getMessage()).getMsg())));
    };

    public IMessageCallback onProxyIdRequest = data -> {
        ProxyIdRequest msg = (ProxyIdRequest) data.getMessage();
        data.setResponse(new ProxyIdResponse(DataBase.currentProxy.loadObjectById(msg.getClazz(), msg.getId())));
    };

    public IMessageCallback onProxyReverseIdRequest = data -> {
        ProxyReverseIdRequest msg = (ProxyReverseIdRequest) data.getMessage();
        //TODO: A Voir pour eviter le unchecked
        data.setResponse(new ProxyReverseIdResponse(DataBase.currentProxy.loadObjectByReverseId(msg.getTarget(), msg.getSource(), msg.getId())));
    };

    public IMessageCallback onPingRequest = data -> {
        data.setResponse(new PingResponse());
    };
    public IMessageCallback onLoginRequest = data -> {
        LoginRequest msg = (LoginRequest) data.getMessage();
        System.out.println(String.format("[Serveur] LoginRequest {Login: '%s'; Password: '%s'}", msg.getLogin(), msg.getPasswordHash()));
        User user = UserRepository.getByCredential(msg.getLogin(), msg.getPasswordHash());
        //data.setResponse(new LoginResponse(user != null, user));
        data.setResponse(new LoginResponse(true, new Student(10, "Piere", "Petit")));
    };

    public IMessageCallback onChangeUserEmailRequest = data -> {
        ChangeUserEmailRequest msg = (ChangeUserEmailRequest) data.getMessage();
        data.setResponse(new ChangeUserEmailResponse(true));
    };

    public IMessageCallback onChangeUserPasswordRequest = data -> {
        ChangeUserPasswordRequest msg = (ChangeUserPasswordRequest) data.getMessage();
        data.setResponse(new ChangeUserPasswordResponse(true));
    };

    public IMessageCallback onChangeUserProfileImageRequest = data -> {
        ChangeUserProfileImageRequest msg = (ChangeUserProfileImageRequest) data.getMessage();
        data.setResponse(new ChangeUserProfileImageResponse(true));
    };
}
