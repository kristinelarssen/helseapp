package helseapp.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUI extends Application {

    /**
     * Åpner et vindu, henter designet til appen fra FXML-dokumentet,
     * setter tittel på vinduet og viser vinduet
     * @param primaryStage Vinduet appen vises i
     * @throws Exception
     */
    @Override
    public void start(final Stage primaryStage) throws Exception {
        Stage window = primaryStage;
        final Parent inputParent = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        Scene inputScene = new Scene(inputParent);
        window.setTitle("Helseapp");
        window.setScene(inputScene);
        window.show();

    }

    /**
     * Main-funksjon
     * @param args
     */
    public static void main(final String[] args) {
        launch(args);
    }
}