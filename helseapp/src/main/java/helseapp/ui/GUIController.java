package helseapp.ui;

import helseapp.core.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.io.Writer;
import java.nio.file.Paths;
import java.nio.file.Path;
import javax.swing.*;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import helseapp.json.DagPersistance;

public class GUIController implements Initializable {
    //private static double vekt;
    //private static double hoyde;

    @FXML
    TextField vektField;

    @FXML
    TextField hoydeField;

    @FXML
    Button registrerButton;

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
