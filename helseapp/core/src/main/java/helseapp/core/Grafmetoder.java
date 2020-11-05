package helseapp.core;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class Grafmetoder {

  /**
   * metode for å lage en graf og plotte inn data i den, med navn på grafen.
   *
   * @param data      - data som hentes fra en todimensjonal liste med x og y
   *                  verdier som brukes i charten
   * @param chart     - kobler charten mot fxml chart som skal brukes for den
   *                  spesifikke charten
   * @param chartName - navnet på grafen
   */
  public static void leggDataIGraf(Double[][] data, LineChart<String, Number> chart, 
      String chartName) {
    chart.getData().clear();
    XYChart.Series<String, Number> series = new XYChart.Series<>();
    series.setName(chartName);
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < data.length; i++) {
      sb.setLength(0);
      sb.append(Math.round(data[i][1])).append(".").append(Math.round(data[i][2]));
      series.getData().add(new XYChart.Data<>(sb.toString(), data[i][0]));
    }
    chart.getData().add(series);
  }
}
