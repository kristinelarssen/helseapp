package helseapp.ui;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.time.LocalDate;
import java.util.Locale;

import helseapp.core.Dag;
import helseapp.core.Dager;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

class Hjelpemetoder {
  /**
   * Metode for å lage en graf og plotte inn data i den, med navn på grafen.
   *
   * @param  data Data som hentes fra en todimensjonal liste med x og y verdier som brukes i charten
   * @param chart Kobler charten mot fxml chart som skal brukes for den spesifikke charten
   * @param chartName Navnet på grafen
   */
  static void settInnGrafdata(double[][] data, LineChart<String, Number> chart,
                              String chartName) {
    chart.getData().clear();
    XYChart.Series<String, Number> series = new XYChart.Series<>();
    series.setName(chartName);
    StringBuilder sb = new StringBuilder();
    for (double[] datum : data) {
      sb.setLength(0);
      sb.append(Math.round(datum[1])).append(".").append(Math.round(datum[2]));
      series.getData().add(new XYChart.Data<>(sb.toString(), datum[0]));
    }
    chart.getData().add(series);
  }

  /**
   * Hjelpemetode som registrerer dato i data-arrayen.
   *
   * @param date Datoen som skal registreres
   * @param data Arrayen som datoen skal registreres i
   */
  static void insertDateInGraphs(LocalDate date, double[] data) {
    data[1] = (double) date.getDayOfMonth();
    data[2] = (double) date.getMonthValue();
  }

  static void makeNumberField(TextField field) {
    NumberFormat numFormat = NumberFormat.getNumberInstance(Locale.US);
    DecimalFormat format = (DecimalFormat) numFormat;
    format.applyPattern("#.0");
    field.setTextFormatter(new TextFormatter<>(c -> {
      if (c.getControlNewText().isEmpty()) {
        return c;
      }

      ParsePosition parsePosition = new ParsePosition(0);
      Object object = format.parse(c.getControlNewText(), parsePosition);

      if (object == null || parsePosition.getIndex() < c.getControlNewText().length()) {
        return null;
      } else {
        return c;
      }
    }));
  }

  /**
   * Henter data lagret på spesifisert dato og legger disse inn i TextField-ene.
   * Hvis ingen data er lagret på datoen blir TextField-ene satt til tomme
   *
   * @param date Dagen det skal hentes ut data fra
   */
  static Dag henteDag(LocalDate date){
    Persistance persistance = new Persistance();
    Dager dager = (Dager) persistance.load("http://localhost:8080/dager");
    Dag dag = null;
    for (int i = 0; i < dager.getDagCount(); i++) {
      if (dager.getDag(i).getDate().equals(date)) {
        return dager.getDag(i);
      }
    }
    return null;
  }
}
