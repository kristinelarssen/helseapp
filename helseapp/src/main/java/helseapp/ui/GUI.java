package helseapp.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUI extends Application {

    private Scene dataScene;

    @Override
    public void start(final Stage primaryStage) throws Exception {
        Stage window = primaryStage;
        final Parent inputParent = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        Scene inputScene = new Scene(inputParent);
        window.setTitle("Helseapp");
        //window.setWidth(50*9);
        //window.setHeight(50*16);
        window.setScene(inputScene);
        window.show();

    }

    public static void main(final String[] args) {
        launch(args);
    }
}