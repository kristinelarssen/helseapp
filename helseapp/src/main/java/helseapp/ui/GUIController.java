package helseapp.ui;

import helseapp.core.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import helseapp.json.midlertidig.*;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

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
    DatePicker datoPicker;

    @FXML
    void bmiAction() {
        vekt = Double.parseDouble(vektField.getText());
        hoyde = Double.parseDouble(hoydeField.getText());
        JOptionPane.showMessageDialog(null, "Din BMI er " + Utregning.regnUtBMI(vekt, hoyde));
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
        Platform.runLater(() -> bmiButton.requestFocus());
    }
}
