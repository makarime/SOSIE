package app;

import Models.*;
import dao.CourseRepository;
import dao.UserRepository;
import messages.*;
import messages.models.*;
import utils.socket.*;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class Client {
    private SClient socket;
    private User user;

    public Client(Socket socket) {
        try {
            this.socket = new SClient(socket, clientEvent);
            this.socket.addMessageArrival(PingRequest.class, onPingRequest);
            this.socket.addMessageArrival(LoginRequest.class, onLoginRequest);
        } catch (IOException e) {
            System.err.println("[Serveur] Erreur création du client.");
            e.printStackTrace();
        }
    }

    public SClientAdapter clientEvent = new SClientAdapter() {
        @Override
        public void onClosed(SClient sender) {
            System.out.println("[Serveur] Deconnexion du client");
        }
    };

    ///////////////// Gestion des messages ///////////////

    public void loginMessageRegister(User user) {
        socket.removeMessageArrival(LoginRequest.class, onLoginRequest);
        socket.addMessageArrival(ProxyRequest.class, onProxyRequest);
        socket.addMessageArrival(ProxyIdRequest.class, onProxyIdRequest);
        socket.addMessageArrival(ProxyReverseIdRequest.class, onProxyReverseIdRequest);
        socket.addMessageArrival(ChangeUserEmailRequest.class, onChangeUserEmailRequest);
        socket.addMessageArrival(ChangeUserPasswordRequest.class, onChangeUserPasswordRequest);
        socket.addMessageArrival(ChangeUserProfileImageRequest.class, onChangeUserProfileImageRequest);
        socket.addMessageArrival(ChangeUserProfileImageRequest.class, onChangeUserProfileImageRequest);
        socket.addMessageArrival(UserCoursesRequest.class, onUserCoursesRequest);
        socket.addMessageArrival(LogoutRequest.class, onLogoutRequest);
    }

    public void logoutRegister() {
        socket.addMessageArrival(LoginRequest.class, onLoginRequest);
        socket.removeMessageArrival(ProxyRequest.class, onProxyRequest);
        socket.removeMessageArrival(ProxyIdRequest.class, onProxyIdRequest);
        socket.removeMessageArrival(ProxyReverseIdRequest.class, onProxyReverseIdRequest);
        socket.removeMessageArrival(ChangeUserEmailRequest.class, onChangeUserEmailRequest);
        socket.removeMessageArrival(ChangeUserPasswordRequest.class, onChangeUserPasswordRequest);
        socket.removeMessageArrival(ChangeUserProfileImageRequest.class, onChangeUserProfileImageRequest);
        socket.removeMessageArrival(ChangeUserProfileImageRequest.class, onChangeUserProfileImageRequest);
        socket.removeMessageArrival(UserCoursesRequest.class, onUserCoursesRequest);
        socket.removeMessageArrival(LogoutRequest.class, onLogoutRequest);
    }

    public IMessageArrival<LoginRequest> onLoginRequest = (sender, event) -> {
        System.out.println(String.format("[Serveur] LoginRequest {Login: '%s'; Password: '%s'}", event.getMessage().getLogin(), event.getMessage().getPasswordHash()));
        User user = UserRepository.getByCredential(event.getMessage().getLogin(), event.getMessage().getPasswordHash());
        event.setResponse(new LoginResponse(user != null, user));
        if(user != null) {
            loginMessageRegister(user);
            this.user = user;
        }
    };

    public IMessageArrival<LogoutRequest> onLogoutRequest = (sender, event) -> {
        logoutRegister();
        user = null;
    };

    public IMessageArrival<ProxyRequest> onProxyRequest = (sender, event) -> {
        event.setResponse(new ProxyResponse(DataBaseEnv.currentProxy.load(event.getMessage().getMsg())));
    };

    public IMessageArrival<ProxyIdRequest> onProxyIdRequest = (sender, event) -> {
        event.setResponse(new ProxyIdResponse(DataBaseEnv.currentProxy.loadObjectById(event.getMessage().getClazz(), event.getMessage().getId())));
    };

    public IMessageArrival<ProxyReverseIdRequest> onProxyReverseIdRequest = (sender, event) -> {
        //TODO: A Voir pour eviter le unchecked
        event.setResponse(new ProxyReverseIdResponse(DataBaseEnv.currentProxy.loadObjectByReverseId(event.getMessage().getTarget(), event.getMessage().getSource(), event.getMessage().getId())));
    };

    public IMessageArrival<PingRequest> onPingRequest = (sender, event) -> {
        event.setResponse(new PingResponse());
    };

    public IMessageArrival<ChangeUserEmailRequest> onChangeUserEmailRequest = (sender, event) -> {
        final boolean result = UserRepository.update(user.getUserId(), UserRepository.Columns.EMAIL, event.getMessage().getEmail());
        event.setResponse(new ChangeUserEmailResponse(result));
    };

    public IMessageArrival<ChangeUserPasswordRequest> onChangeUserPasswordRequest = (sender, event) -> {
        final boolean result = UserRepository.changePassword(user.getUserId(), event.getMessage().getOldUserPassword(), event.getMessage().getUserPassword());
        event.setResponse(new ChangeUserPasswordResponse(result));
    };

    public IMessageArrival<ChangeUserProfileImageRequest> onChangeUserProfileImageRequest = (sender, event) -> {
        event.setResponse(new ChangeUserProfileImageResponse(true));
    };

    public IMessageArrival<UserCoursesRequest> onUserCoursesRequest = (sender, event) -> {
        //TODO: A Optimiser
        List<Course> result = user.isStudent() ?
                CourseRepository.getCoursesOnDateByCP(((Student)user).getCurrentClassBatch().getClassBatchId(), event.getMessage().getDate()) :
                CourseRepository.getCoursesOnDateByProfessor(user.getUserId(), event.getMessage().getDate());
        event.setResponse(new UserCoursesResponse(result));
    };
}
