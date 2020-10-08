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
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.nio.file.Path;
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

    @FXML
    TextField vektField, skrittField, treningField, proteinField,karboField, fettField;

    @FXML
    Button lagreButton, henteButton;

    @FXML
    DatePicker datoPicker;

    @FXML
    LineChart<String, Number> vektChart, skrittChart;

    @FXML
    void lagreData() {
        LocalDate date = LocalDate.now();
        double vekt = Double.parseDouble(vektField.getText());
        int skritt = Integer.parseInt(skrittField.getText());
        double treningstid = Double.parseDouble(skrittField.getText());
        double proteiner = Double.parseDouble(proteinField.getText());
        double karbohydrater = Double.parseDouble(karboField.getText());
        double fett = Double.parseDouble(fettField.getText());

        // Lagring:
        fileData.saveDag(new Dag(vekt, skritt, treningstid, proteiner, karbohydrater, fett, date), savePath);

        // Om alt fungerer, slett det under:
        // Dager dager = new Dager();
        // dager.addDag(new Dag(vekt, skritt, treningstid, proteiner, karbohydrater, fett));

        /*
        if(savePath != null){
            Path path = Paths.get(savePath);
            try(Writer writer = new FileWriter(path.toFile(), StandardCharsets.UTF_8)){
                dagPersistance.writeDager(dager, writer);
            } catch (IOException e){
                System.err.println("Fikk ikke skrevet til dager.json på hjemme området");
            }
        }
        */
    }

    @FXML
    void henteData() {
        Dager dager = fileData.read(savePath);

        // Om alt fungerer, slett alt som er kommentert ut under.
        /*
        Reader reader = null;
        Dager dager = null;
        if(savePath != null) {
            try {
                reader = new FileReader(Paths.get(savePath).toFile(), StandardCharsets.UTF_8);
            } catch (IOException e) {
                System.err.println("Feil!");
            }
        }
        try {
            dager = dagPersistence.readDager(reader);
        } catch (IOException e) {
            System.err.println("Feil_2");
        }
        */

        vektField.setText(String.valueOf(dager.getDag(dager.getDagCount()-1).getVekt()));
        skrittField.setText(String.valueOf(Math.round(dager.getDag(dager.getDagCount()-1).getSkritt())));
        treningField.setText(String.valueOf(dager.getDag(dager.getDagCount()-1).getTreningstid()));
        proteinField.setText(String.valueOf(dager.getDag(dager.getDagCount()-1).getProtein()));
        karboField.setText(String.valueOf(dager.getDag(dager.getDagCount()-1).getKarbo()));
        fettField.setText(String.valueOf(dager.getDag(dager.getDagCount()-1).getFett()));
    }

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
            Grafmetoder.leggDataIGraf(vektData, vektChart, "Vekt");
            Grafmetoder.leggDataIGraf(skrittData, skrittChart, "Skritt");
        });
    }
}
