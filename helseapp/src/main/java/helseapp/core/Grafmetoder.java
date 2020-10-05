package helseapp.core;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class Grafmetoder {
    public static void leggDataIGraf(Double[][] data, LineChart<String, Number> chart, String chartName) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(chartName);
        for(int i=0; i < 7; i++) {
            series.getData().add(new XYChart.Data<>(data[i][0] + "", data[i][1]));
        }
        chart.getData().add(series);
    }
}
