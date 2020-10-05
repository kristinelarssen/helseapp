package helseapp.ui;

import helseapp.core.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.io.Writer;
import java.nio.file.Paths;
import java.nio.file.Path;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import helseapp.json.DagPersistance;

public class GUIController implements Initializable {
    private static double vekt;
    private static double hoyde;
    Double[][] vektData = new Double[7][2];
    Double[][] skrittData = new Double[7][2];

    @FXML
    TextField vektField;

    @FXML
    TextField hoydeField;

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
    Button bmiButton;

    @FXML
    Button lagreButton;

    @FXML
    Button henteButton;

    @FXML
    DatePicker datoPicker;

    @FXML
    LineChart<String, Number> vektChart, skrittChart;

    @FXML
    private void populateWeightGraph() {
        Grafmetoder.leggDataIGraf(vektData, vektChart, "Vekt");
    }

    @FXML
    private void populateStepGraph() {
        Grafmetoder.leggDataIGraf(skrittData, skrittChart, "Skritt");
    }

    private String savePath = "helseapp/src/main/java/helseapp/json/dager.json";

    private DagPersistance dagPersistance = new DagPersistance();

    private Dager dager = new Dager();

    @FXML
    void registrerAction() {
        double vekt = Double.parseDouble(vektField.getText());
        double hoyde = Double.parseDouble(hoydeField.getText());
        dager.addDag(new Dag(vekt, hoyde));
        JOptionPane.showMessageDialog(null, "Din BMI er " + Utregning.regnUtBMI(vekt, hoyde));

        // Lagring:
        if(savePath != null){
            Path path = Paths.get(savePath);
            try(Writer writer = new FileWriter(path.toFile(), StandardCharsets.UTF_8)){
                dagPersistance.writeDager(dager, writer);
            } catch (IOException e){
                System.err.println("Fikk ikke skrevet til dager.json på hjemme området");
            }
        }
    }

    @FXML
    void lagreData() {
        StringBuilder data = new StringBuilder();
        data.append(datoPicker.getValue());
        data.append("\n").append(vektField.getText());
        data.append("\n").append(hoydeField.getText());
        data.append("\n").append(skrittField.getText());
        data.append("\n").append(treningField.getText());
        data.append("\n").append(proteinField.getText());
        data.append("\n").append(karboField.getText());
        data.append("\n").append(fettField.getText());
        try {
            WriteFile.writeToFile(data.toString(), "helseapp/src/main/java/helseapp/json/midlertidig/data.txt", false);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            JOptionPane.showMessageDialog(null, "Dine data har blitt lagret");
        }
    }

    @FXML
    void henteData() {
        String[] data = new String[8];
        try {
            data = ReadFile.OpenFile("helseapp/src/main/java/helseapp/json/midlertidig/data.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        datoPicker.setValue(LocalDate.parse(data[0]));
        vektField.setText(data[1]);
        hoydeField.setText(data[2]);
        skrittField.setText(data[3]);
        treningField.setText(data[4]);
        proteinField.setText(data[5]);
        karboField.setText(data[6]);
        fettField.setText(data[7]);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            bmiButton.requestFocus();
            //Testdata for grafene
            for (int i = 0; i < 7; i++) {
                vektData[i][0] = Double.parseDouble(((27+i)%31) + "");
                vektData[i][1] = Double.parseDouble((70+i) + "");
                skrittData[i][0] = Double.parseDouble(((27+i)%31) + "");
                skrittData[i][1] = Double.parseDouble((5000) + Math.round(Math.random()*20000) + "");
            }
            populateWeightGraph();
            populateStepGraph();
        });
    }
}
