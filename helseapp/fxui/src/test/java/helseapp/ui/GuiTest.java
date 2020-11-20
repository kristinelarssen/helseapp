package helseapp.ui;

import helseapp.core.Dag;
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

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

public class GuiTest extends ApplicationTest {
  private GuiController controller;
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
    final FXMLLoader loader = new FXMLLoader(getClass().getResource("Gui.fxml"));
    final Parent root = loader.load();
    this.controller = loader.getController();
    stage.setScene(new Scene(root));
    stage.show();
  }

  @BeforeEach
  void setupItems() throws Exception {
    ApplicationTest.launch(Gui.class);
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
    datoPicker.setValue(LocalDate.of(1970, Month.JANUARY, 2));
    vektField.setText("16");
    skrittField.setText("15");
    treningField.setText("14");
    proteinField.setText("13");
    karboField.setText("12");
    fettField.setText("11");
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
    controller.datoPicker.setValue(LocalDate.of(1970, 1, 1));
    controller.henteData();
    assertEquals(vektField.getText(), "1.0");
    assertEquals(skrittField.getText(), "2");
    assertEquals(treningField.getText(), "3.0");
    assertEquals(proteinField.getText(), "4.0");
    assertEquals(karboField.getText(), "5.0");
    assertEquals(fettField.getText(), "6.0");
    controller.datoPicker.setValue(LocalDate.of(1971, 1, 1));
    controller.henteData();
    assertEquals(vektField.getText(), "");
    assertEquals(skrittField.getText(), "");
    assertEquals(treningField.getText(), "");
    assertEquals(proteinField.getText(), "");
    assertEquals(karboField.getText(), "");
    assertEquals(fettField.getText(), "");
    assertFalse(nextButton.isDisabled());
    assertFalse(prevButton.isDisabled());
    controller.datoPicker.setValue(LocalDate.now());
    controller.henteData();
    assertTrue(nextButton.isDisabled());
    assertFalse(prevButton.isDisabled());
    controller.datoPicker.setValue(LocalDate.of(2030, 1, 1));
    controller.henteData();
    assertTrue(nextButton.isDisabled());
    assertTrue(prevButton.isDisabled());
  }

  @Test
  void testNesteDag() {
    controller.datoPicker.setValue(LocalDate.of(1969, 12, 31));
    controller.nesteDag();
    assertEquals(vektField.getText(), "1.0");
    assertEquals(skrittField.getText(), "2");
    assertEquals(treningField.getText(), "3.0");
    assertEquals(proteinField.getText(), "4.0");
    assertEquals(karboField.getText(), "5.0");
    assertEquals(fettField.getText(), "6.0");
    assertFalse(nextButton.isDisabled());
    LocalDate date = LocalDate.now();
    LocalDate changeDate = date.minusDays(1);
    controller.datoPicker.setValue(changeDate);
    controller.nesteDag();
    assertTrue(nextButton.isDisabled());
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
    controller.lagreData();
    controller.dateChangeAction();
    controller.henteData();
    assertEquals(LocalDate.of(1970, 1, 2), datoPicker.getValue());
    assertEquals(vektField.getText(), "16.0");
    assertEquals(skrittField.getText(), "15");
    assertEquals(treningField.getText(), "14.0");
    assertEquals(proteinField.getText(), "13.0");
    assertEquals(karboField.getText(), "12.0");
    assertEquals(fettField.getText(), "11.0");
    controller.dateChangeAction();
    controller.lagreData();
  }

  @Test
  void testPopulateGraphs() {
  controller.populateGraphs(6, LocalDate.of(1970,2,1));
  for (int i = 0; i < 5; i++) {
    assertEquals(getxvalue(controller.vektChart, i), (i+1) + ".2");
    assertEquals(getxvalue(controller.skrittChart, i), (i+1) + ".2");
    assertEquals(getxvalue(controller.treningsChart, i), (i+1) + ".2");
    assertEquals(getxvalue(controller.kaloriChart, i), (i+1) + ".2");
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
    controller.fraDato.setValue(LocalDate.of(1970, 2, 1));
    controller.tilDato.setValue(LocalDate.of(1970, 2, 6));
    controller.visGraf();
    assertEquals(getxvalue(controller.vektChart, 0), "1.2");
    assertEquals(getxvalue(controller.vektChart, 5), "6.2");
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