package helseapp.ui;

import helseapp.core.Dag;
import helseapp.json.DagPersistance;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.beans.Transient;
import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

public class GUITest extends ApplicationTest {
  private GUIController controller;
  private TextField vektField;
  private TextField skrittField;
  private TextField treningField;
  private TextField proteinField;
  private TextField karboField;
  private TextField fettField;
  private DatePicker datoPicker;
  private TextField[] textFields = new TextField[6];
  private Button prevButton;
  private Button nextButton;

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
    controller.savePath = "../core/src/test/java/helseapp/json/dager.json";
    ApplicationTest.launch(GUI.class);
    vektField = controller.vektField; // lookup("#vektField").query();
    skrittField = controller.skrittField; // lookup("#skrittField").query();
    treningField = controller.treningField; // = lookup("#treningField").query();
    proteinField = controller.proteinField; // lookup("#proteinField").query();
    karboField = controller.karboField; // lookup("#karboField").query();
    fettField = controller.fettField; // lookup("#karboField").query();
    datoPicker = controller.datoPicker; // lookup("#datoPicker").query();
    prevButton = controller.prevButton; // lookup("#prevButton").query();
    nextButton = controller.nextButton; // lookup("#nextButton").query();
    textFields[0] = vektField;
    textFields[1] = skrittField;
    textFields[2] = treningField;
    textFields[3] = proteinField;
    textFields[4] = karboField;
    textFields[5] = fettField;
    datoPicker.setValue(LocalDate.of(2020, Month.DECEMBER, 24));
    vektField.setText("80");
    skrittField.setText("15000");
    treningField.setText("30");
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
    assertEquals(treningField.getText(), "45.0");
    assertEquals(proteinField.getText(), "200.0");
    assertEquals(karboField.getText(), "200.0");
    assertEquals(fettField.getText(), "100.0");
  }

  @Test
  void testHenteData() {
    controller.datoPicker.setValue(LocalDate.of(2020, 12, 1));
    controller.henteData();
    assertEquals(vektField.getText(), "50.0");
    assertEquals(skrittField.getText(), "5000");
    assertEquals(treningField.getText(), "10.0");
    assertEquals(proteinField.getText(), "200.0");
    assertEquals(karboField.getText(), "300.0");
    assertEquals(fettField.getText(), "50.0");
    controller.datoPicker.setValue(LocalDate.of(2020, 11, 30));
    controller.henteData();
    assertEquals(vektField.getText(), "");
    assertEquals(skrittField.getText(), "");
    assertEquals(treningField.getText(), "");
    assertEquals(proteinField.getText(), "");
    assertEquals(karboField.getText(), "");
    assertEquals(fettField.getText(), "");
    assertTrue(nextButton.isEnabled());
    assertTrue(prevButton.isEnabled());
    controller.datoPicker.setValue(LocalDate.now());
    controller.henteData();
    assertTrue(nextButton.isEnabled());
    assestFalse(prevButton.isEnabled());
    controller.datoPicker.setValue(LocalDate.of(2020, 11, 1));
    controller.henteData();
    assertFalse(nextButton.isEnabled());
    assertFalse(prevButton.isEnabled());
  }

  @Test
  void testNesteDag() {
    controller.datoPicker.setValue(LocalDate.of(2020, 11, 30));
    controller.nesteDag();
    assertEquals(vektField.getText(), "50.0");
    assertEquals(skrittField.getText(), "5000");
    assertEquals(treningField.getText(), "10.0");
    assertEquals(proteinField.getText(), "200.0");
    assertEquals(karboField.getText(), "300.0");
    assertEquals(fettField.getText(), "50.0");
    assertTrue(nextButton.isEnabled());
    LocalDate date = LocalDate.now();
    LocalDate changeDate = date.minusDays(1);
    controller.datoPicker.setValue(changeDate);
    controller.nesteDag();
    assertFalse(nextButton.isEnabled());
  }

  @Test
  void testForrigeDag() {
    controller.datoPicker.setValue(LocalDate.of(2020, 12, 1));
    controller.forrigeDag();
    assertEquals(vektField.getText(), "");
    assertEquals(skrittField.getText(), "");
    assertEquals(treningField.getText(), "");
    assertEquals(proteinField.getText(), "");
    assertEquals(karboField.getText(), "");
    assertEquals(fettField.getText(), "");
  }

  @Test
  void testReturnerTilDag() {
    controller.datoPicker.setValue(LocalDate.now());
    controller.vektField.setText("80");
    controller.skrittField.setText("8000");
    controller.treningField.setText("30");
    controller.proteinField.setText("150");
    controller.karboField.setText("400");
    controller.fettField.setText("100");
    controller.lagreData();
    controller.datoPicker.setValue(LocalDate.now());
    controller.returnerTilDag();
    assertEquals(vektField.getText(), "80.0");
    assertEquals(skrittField.getText(), "8000");
    assertEquals(treningField.getText(), "30.0");
    assertEquals(proteinField.getText(), "150.0");
    assertEquals(karboField.getText(), "400.0");
    assertEquals(fettField.getText(), "100.0");
  }

  @Test
  void testLagreData() {
    vektField.setText("");
    controller.lagreData();
    helseapp.core.Dager dager = controller.dagPersistance.read(controller.savePath);
    Dag dag = dager.getDag(dager.getDagCount() - 1);
    assertEquals(dag.getDate(), datoPicker.getValue());
    assertEquals(dag.getVekt(), 0.0);
    assertEquals(dag.getSkritt(), 15000.0);
    assertEquals(dag.getTreningstid(), 30.0);
    assertEquals(dag.getProtein(), 150.0);
    assertEquals(dag.getKarbo(), 400.0);
    assertEquals(dag.getFett(), 100.0);
    dager.removeDag(dager.getDagCount() - 1);
    controller.dagPersistance.save(controller.savePath, dager);
  }

  @Test
  void testPopulateGraphs() {
  controller.populateGraphs(6, LocalDate.of(2020, 12, 1));
  for (int i = 0; i < 5; i++) {
    assertEquals(getxvalue(controller.vektChart, i), (i+1) + ".12");
    assertEquals(getxvalue(controller.skrittChart, i), (i+1) + ".12");
    assertEquals(getxvalue(controller.treningsChart, i), (i+1) + ".12");
    assertEquals(getxvalue(controller.kaloriChart, i), (i+1) + ".12");
  }
  for (int i = 0; i < 5; i++) {
    assertEquals(getyvalue(controller.vektChart, i), 50.0 + i*10);
    assertEquals(getyvalue(controller.skrittChart, i), 5000.0 + i*1000);
    assertEquals(getyvalue(controller.treningsChart, i), 10.0 + i*5);
    assertEquals(getyvalue(controller.kaloriChart, i), 2450.0 - i*81);
  }
    assertEquals(getyvalue(controller.vektChart, 5), 0.0);
    assertEquals(getyvalue(controller.skrittChart, 5), 0.0);
    assertEquals(getyvalue(controller.treningsChart, 5), 0.0);
    assertEquals(getyvalue(controller.kaloriChart, 5), 0.0);
  }

  @Test
  void testVisGraf() {
    controller.fraDato.setValue(LocalDate.of(2020, 12, 1));
    controller.tilDato.setValue(LocalDate.of(2020, 12, 6));
    controller.visGraf();
    assertEquals(getxvalue(controller.vektChart, 0), "1.12");
    assertEquals(getxvalue(controller.vektChart, 5), "6.12");
    assertEquals(controller.vektChart.getData().get(0).getData().size(), 6);
  }

  @Test
  void testMakeNumberField() {
    Hjelpemetoder hjelpemetoder = new Hjelpemetoder();
    vektField.setText("");
    vektField.setText("1");
    assertEquals(vektField.getText(), "1");
    vektField.setText("");
    vektField.setText("a");
    System.out.println(vektField.getText());
    assertEquals(vektField.getText(), "");
  }

  String getxvalue(javafx.scene.chart.LineChart<String, Number> chart, int day) {
    return chart.getData().get(0).getData().get(day).getXValue();
  }

  double getyvalue(javafx.scene.chart.LineChart<String, Number> chart, int day) {
    return chart.getData().get(0).getData().get(day).getYValue().doubleValue();
  }
}