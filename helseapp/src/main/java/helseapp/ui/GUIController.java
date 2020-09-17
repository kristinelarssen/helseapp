package helseapp.ui;

import helseapp.core.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import helseapp.json.midlertidig.*;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class GUIController implements Initializable {
    private static double vekt;
    private static double hoyde;

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
    void bmiAction() {
        vekt = Double.parseDouble(vektField.getText());
        hoyde = Double.parseDouble(hoydeField.getText());
        JOptionPane.showMessageDialog(null, "Din BMI er " + Utregning.regnUtBMI(vekt, hoyde));
    }

    @FXML
    void lagreData() {
        StringBuilder data = new StringBuilder();
        data.append(vektField.getText());
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
        String[] data = new String[7];
        try {
            data = ReadFile.OpenFile("helseapp/src/main/java/helseapp/json/midlertidig/data.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        vektField.setText(data[0]);
        hoydeField.setText(data[1]);
        skrittField.setText(data[2]);
        treningField.setText(data[3]);
        proteinField.setText(data[4]);
        karboField.setText(data[5]);
        fettField.setText(data[6]);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> bmiButton.requestFocus());
    }
}
