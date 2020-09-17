package helseapp.ui;

import helseapp.core.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class GUIController implements Initializable {
    private static double vekt;
    private static double hoyde;

    @FXML
    TextField vektField;

    @FXML
    TextField hoydeField;

    @FXML
    Button registrerButton;

    @FXML
    Button lagreButton;

    @FXML
    Button henteButton;

    @FXML
    void registrerAction() {
        vekt = Double.parseDouble(vektField.getText());
        hoyde = Double.parseDouble(hoydeField.getText());
        JOptionPane.showMessageDialog(null, "Din BMI er " + Utregning.regnUtBMI(vekt, hoyde));
    }

    @FXML
    void lagreData() {}

    @FXML
    void henteData() {}


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        registrerButton.setMaxWidth(Double.MAX_VALUE);
        Platform.runLater(() -> registrerButton.requestFocus());
    }
}
