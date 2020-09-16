package helseapp.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUI extends Application {

    @Override
    public void start(final Stage primaryStage) throws Exception {
        final Parent parent = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        primaryStage.setTitle("Helseapp");
        primaryStage.setWidth(50*9);
        primaryStage.setHeight(50*16);
        primaryStage.setScene(new Scene(parent));
        primaryStage.show();
    }

    public static void main(final String[] args) {
        launch(args);
    }
}