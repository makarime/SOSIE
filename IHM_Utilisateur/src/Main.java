import Controllers.UserConnectionWindowController;
import Models.DataBaseModels.DataBase;
import Models.Internet;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Internet.TestConnectivity();
        if (!Internet.isConnected)
            System.exit(1);

        //DataBase.ConnectToDataBase();
        //if (!DataBase.isConnected)
        //    System.exit(1);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/UserConnectionWindowView.fxml"));
        Parent root = loader.load();
        UserConnectionWindowController userConnectionWindowController = loader.getController();
        userConnectionWindowController.stage = primaryStage;
        primaryStage.setTitle("Connexion");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(action -> DataBase.CloseDataBaseConnection());
        primaryStage.show();
    }
}
