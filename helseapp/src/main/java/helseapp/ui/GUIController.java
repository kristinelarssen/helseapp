package helseapp.ui;

import helseapp.core.*;
import helseapp.json.FileData;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import helseapp.json.DagPersistance;

public class GUIController implements Initializable {
    private Double[][] vektData = new Double[7][2];
    private Double[][] skrittData = new Double[7][2];
    private String savePath = "helseapp/src/main/java/helseapp/json/dager.json";
    private DagPersistance dagPersistance = new DagPersistance();
    private FileData fileData = new FileData(dagPersistance);

    //Definerer alle FXML-elementene

    @FXML
    TextField vektField, skrittField, treningField, proteinField, karboField, fettField;

    @FXML
    Button lagreButton, henteButton;

    @FXML
    DatePicker datoPicker;

    @FXML
    LineChart<String, Number> vektChart, skrittChart;

    //Metode for å lagre datafelt på spesifisert dato
    // Hvis feltet ikke er fylt inn lagres verdien 0
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
        for(int i = 0; i < 6; i++) {
            try {
                tallData[i] = Double.parseDouble(tekstData[i]);
            } catch (NumberFormatException e) {
                tallData[i] = 0.0;
            }
        }
        // Lagring:
        fileData.saveDag(new Dag(tallData[0], tallData[1], tallData[2], tallData[3], tallData[4], tallData[5], date), savePath);
    }

    //Kalles når datofeltet endres
    @FXML
    void dateChangeAction() {
        setDataFields("", "", "", "", "", "");
    }

    //Metode for å hente data lagret på spesifisert dato
    @FXML
    void henteData() {
        LocalDate date = LocalDate.parse(datoPicker.getValue().toString());
        Dager dager = fileData.read(savePath);
        Dag dag = null;
        for(int i = 0; i < dager.getDagCount(); i++) {
            if(dager.getDag(i).getDate().toString().equals(date.toString())) {
                dag = dager.getDag(i);
            }
        }
        if (dag != null) {
            setDataFields(dag);
        }
    }

    //Hjelpemetoder
    private void setDataFields(Dag dag) {
        setDataFields(Double.toString(dag.getVekt()), Long.toString(Math.round(dag.getSkritt())), Double.toString(dag.getTreningstid()), Double.toString(dag.getProtein()), Double.toString(dag.getKarbo()), Double.toString(dag.getFett()));
    }

    private void setDataFields(String vekt, String skritt, String treningstid, String protein, String karbohydrater, String fett) {
        vektField.setText(vekt);
        skrittField.setText(skritt);
        treningField.setText(treningstid);
        proteinField.setText(protein);
        karboField.setText(karbohydrater);
        fettField.setText(fett);
    }

    //Kjøres ved start av appen
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            //Testdata for grafene
            for (int i = 0; i < 7; i++) {
                vektData[i][0] = Double.parseDouble(((27+i)%31) + "");
                vektData[i][1] = Double.parseDouble((70+i) + "");
                skrittData[i][0] = Double.parseDouble(((27+i)%31) + "");
                skrittData[i][1] = Double.parseDouble((5000) + Math.round(Math.random()*20000) + "");
            }
            //Viser grafene med data
            Grafmetoder.leggDataIGraf(vektData, vektChart, "Vekt");
            Grafmetoder.leggDataIGraf(skrittData, skrittChart, "Skritt");
            datoPicker.setValue(LocalDate.now());
        });
    }
}
