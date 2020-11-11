package helseapp.ui;

import helseapp.core.Dag;
import helseapp.core.Dager;
import helseapp.json.DagPersistance;
import java.net.URL;
import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class GUIController implements Initializable {
  String savePath = "../core/src/main/java/helseapp/json/dager.json";
  private DagPersistance dagPersistance = new DagPersistance();

  // Definerer alle FXML-elementene

  @FXML
  TextField vektField;

  @FXML
  TextField skrittField;

  @FXML
  TextField treningField;

  @FXML
  TextField proteinField;

  @FXML
  TextField karboField;

  @FXML
  TextField fettField;

  @FXML
  Button lagreButton;

  @FXML
  Button henteButton;

  @FXML
  Button visGraf;

  @FXML
  DatePicker datoPicker;

  @FXML
  DatePicker fraDato;

  @FXML
  DatePicker tilDato;

  @FXML
  LineChart<String, Number> vektChart;

  @FXML
  LineChart<String, Number> skrittChart;

  @FXML
  LineChart<String, Number> kaloriChart;

  @FXML
  LineChart<String, Number> treningsChart;

  /**
   * Lagrer data fra TextField-feltene i appen.
   * Data lagres basert på dato, med ett datasett per dato
   * Hvis feltene ikke er fylt inn lagres verdien 0
   */
  @FXML
  void lagreData() {
    LocalDate date = LocalDate.parse(datoPicker.getValue().toString());
    Double[] tallData = new Double[6];
    String[] tekstData = new String[6];
    tekstData[0] = vektField.getText();
    tekstData[1] = skrittField.getText();
    tekstData[2] = treningField.getText();
    tekstData[3] = proteinField.getText();
    tekstData[4] = karboField.getText();
    tekstData[5] = fettField.getText();
    for (int i = 0; i < 6; i++) {
      try {
        tallData[i] = Double.parseDouble(tekstData[i]);
      } catch (NumberFormatException e) {
        tallData[i] = 0.0;
      }
    }
    // Lagring:
    dagPersistance.saveDag(new Dag(tallData[0], tallData[1], tallData[2], tallData[3], tallData[4], tallData[5], date), savePath);
  }

  /**
   * Setter innhold i alle TextField-feltene når dato endres.
   */
  @FXML
  void dateChangeAction() {
    setDataFields("", "", "", "", "", "");
  }

  /**
   * Henter data lagret på spesifisert dato og legger disse inn i TextField-ene.
   * Hvis ingen data er lagret på datoen forblir TextField-ene uendret
   */
  @FXML
  void henteData() {
    LocalDate date = LocalDate.parse(datoPicker.getValue().toString());
    Dager dager = dagPersistance.read(savePath);
    Dag dag = null;
    for (int i = 0; i < dager.getDagCount(); i++) {
      if (dager.getDag(i).getDate().equals(date)) {
        dag = dager.getDag(i);
      }
    }
    if (dag != null) {
      setDataFields(dag);
    }
  }

  /**
   * Kalles når visGraf-knappen trykkes Viser graf med lagret data for intervallet.
   * mellom startDato og sluttDato
   */
  @FXML
  void visGraf() {
    LocalDate startDato = fraDato.getValue();
    LocalDate sluttDato = tilDato.getValue();
    populateGraphs((int) DAYS.between(startDato, sluttDato) + 1, startDato);
  }

  /**
   * Setter TextFields-feltene i appen med info lagret i Dag-objetket.
   *
   * @param dag Dag-objekt som inneholder dataene som skal vises i TextField-feltene
   */
  void setDataFields(Dag dag) {
    setDataFields(Double.toString(dag.getVekt()), Long.toString(Math.round(dag.getSkritt())),
      Double.toString(dag.getTreningstid()), Double.toString(dag.getProtein()), Double.toString(dag.getKarbo()),
      Double.toString(dag.getFett()));
  }

  /**
   * Setter TextFields-feltene i appen med info fra parameterne.
   *
   * @param vekt          Legges inn i vekt-feltet
   * @param skritt        Legges inn i skritt-feltet
   * @param treningstid   Legges inn i treningstid-feltet
   * @param protein       Legges inn i protein-feltet
   * @param karbohydrater Legges inn i karbohydrater-feltet
   * @param fett          Legges inn i fett-feltet
   */
  void setDataFields(String vekt, String skritt, String treningstid, String protein, String karbohydrater,
      String fett) {
    vektField.setText(vekt);
    skrittField.setText(skritt);
    treningField.setText(treningstid);
    proteinField.setText(protein);
    karboField.setText(karbohydrater);
    fettField.setText(fett);
  }

  /**
   * Henter data som er lagret og legger det inn i grafene. Viser deretter grafene.
   *
   * @param antallDager Antall dager som skal vises i grafen
   * @param startDate Den første datoen som skal vises i grafen
   */
  void populateGraphs(int antallDager, LocalDate startDate) {
    double[][][] grafData = new double[4][antallDager][3];
    Dager dager = dagPersistance.read(savePath);
    Dag dag = null;
    for(int i = 0; i < antallDager; i++) {
      for(int j = 0; j < dager.getDagCount(); j++) {
        if(dager.getDag(j).getDate().equals(startDate.plusDays(i))) {
          dag = dager.getDag(j);
        }
      }
      for (int j = 0; j < 4; j++) {
        Hjelpemetoder.insertDateInGraphs(startDate.plusDays(i), grafData[j][i]);
      }
      grafData[0][i][0] = dag == null ? 0.0 : dag.getVekt();
      grafData[1][i][0] = dag == null ? 0.0 : dag.getSkritt();
      grafData[2][i][0] = dag == null ? 0.0 : dag.getKalorier();
      grafData[3][i][0] = dag == null ? 0.0 : dag.getTreningstid();
    }
    Hjelpemetoder.leggDataIGraf(grafData[0], vektChart, "Vekt");
    Hjelpemetoder.leggDataIGraf(grafData[1], skrittChart, "Skritt");
    Hjelpemetoder.leggDataIGraf(grafData[2], kaloriChart, "Kalorier");
    Hjelpemetoder.leggDataIGraf(grafData[3], treningsChart, "Treningstid");
  }

  /**
   * Initialiserer appen.
   * Legger inn testdata i grafene Viser grafene i appen.
   * Setter datoen i DatePicker til dagens dato
   *
   * @param location
   * @param resources
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    Platform.runLater(() -> datoPicker.setValue(LocalDate.now()));
  }
}
