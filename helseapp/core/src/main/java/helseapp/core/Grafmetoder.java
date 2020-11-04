package helseapp.core;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class Grafmetoder {

    /**
	* metode for å lage en graf og plotte inn data i den, med navn på grafen
	* @param  data - data som hentes fra en todimensjonal liste med x og y verdier som brukes i charten
	* @param chart - kobler charten mot fxml chart som skal brukes for den spesifikke charten
	* @param chartName - navnet på grafen
    */
    
    public static void leggDataIGraf(Double[][] data, LineChart<String, Number> chart, String chartName) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(chartName);
        for(int i=0; i < 7; i++) {
            series.getData().add(new XYChart.Data<>(data[i][0] + "", data[i][1]));
        }
        chart.getData().add(series);
    }
}
