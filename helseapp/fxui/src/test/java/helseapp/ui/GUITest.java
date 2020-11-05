package helseapp.ui;

import helseapp.core.Dag;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

public class GUITest extends ApplicationTest {
  private GUIController controller;
  private TextField vektField;
  private TextField skrittField;
  private TextField treningsField;
  private TextField proteinField;
  private TextField karboField;
  private TextField fettField;
  private DatePicker datoPicker;
  private TextField[] textFields = new TextField[6];

  @Override
  public void start(final Stage stage) throws Exception {
    final FXMLLoader loader = new FXMLLoader(getClass().getResource("GUI.fxml"));
    final Parent root = loader.load();
    this.controller = loader.getController();
    stage.setScene(new Scene(root));
    stage.show();
  }

  @BeforeEach
  void setupItems() throws Exception {
    controller.savePath = "../core/src/main/java/helseapp/json/dager.json";
    ApplicationTest.launch(GUI.class);
    vektField = controller.vektField; // lookup("#vektField").query();
    skrittField = controller.skrittField;
    treningsField = controller.treningField; // = lookup("#treningsField").query();
    proteinField = controller.proteinField; // lookup("#proteinField").query();
    karboField = controller.karboField; // lookup("#karboField").query();
    fettField = controller.fettField; // lookup("#karboField").query();
    datoPicker = controller.datoPicker; // lookup("#datoPicker").query();
    textFields[0] = vektField;
    textFields[1] = skrittField;
    textFields[2] = treningsField;
    textFields[3] = proteinField;
    textFields[4] = karboField;
    textFields[5] = fettField;
    datoPicker.setValue(LocalDate.of(2020, Month.DECEMBER, 24));
    vektField.setText("80");
    skrittField.setText("15000");
    treningsField.setText("30");
    proteinField.setText("150");
    karboField.setText("400");
    fettField.setText("100");
  }

  @Test
  void testController_initial() {
    assertNotNull(this.controller);
  }

  @Test
  void testDateChangeAction() {
    datoPicker.setValue(LocalDate.of(2020, Month.DECEMBER, 25));
    for (TextField field : textFields) {
      assertEquals(field.getText(), "");
    }
  }

  @Test
  void testSetDataFields() {
    Dag dag = new Dag(70, 20000, 45, 200, 200, 100, LocalDate.now());
    controller.setDataFields(dag);
    assertEquals(vektField.getText(), "70.0");
    assertEquals(skrittField.getText(), "20000");
    assertEquals(treningsField.getText(), "45.0");
    assertEquals(proteinField.getText(), "200.0");
    assertEquals(karboField.getText(), "200.0");
    assertEquals(fettField.getText(), "100.0");
  }

  @Test
  void testHenteData() {
    controller.datoPicker.setValue(LocalDate.of(2020, Month.DECEMBER, 1));
    controller.henteData();
    assertEquals(vektField.getText(), "50.0");
    assertEquals(skrittField.getText(), "5000");
    assertEquals(treningsField.getText(), "10.0");
    assertEquals(proteinField.getText(), "200.0");
    assertEquals(karboField.getText(), "300.0");
    assertEquals(fettField.getText(), "50.0");
  }
}