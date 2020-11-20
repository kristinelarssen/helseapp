package helseapp.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Gui extends Application {

  /**
   * Åpner et vindu, og henter designet til appen fra FXML-dokumentet.
   * Setter tittel på vinduet og viser vinduet.
   *
   * @param primaryStage Vinduet appen vises i
   * @throws Exception Exception
   */
  @Override
  public void start(final Stage primaryStage) throws Exception {
    final Parent inputParent = FXMLLoader.load(getClass().getResource("Gui.fxml"));
    Scene inputScene = new Scene(inputParent);
    primaryStage.setTitle("Helseapp");
    primaryStage.setScene(inputScene);
    primaryStage.show();
  }

  /**
   * Main-funksjon.
   *
   * @param args Argumenter
   */
  public static void main(final String[] args) {
    launch(args);
  }
}