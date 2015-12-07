import Controllers.UserConnectionController;
import Models.AppUser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        if ((!AppUser.TestConnectivity()) || (!AppUser.testServerAccess()))
            System.exit(1);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/UserConnectionView.fxml"));
        loader.setController(new UserConnectionController(primaryStage));
        primaryStage.setTitle("Connexion");
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(action -> AppUser.sClient.close());
        primaryStage.show();
    }
}
