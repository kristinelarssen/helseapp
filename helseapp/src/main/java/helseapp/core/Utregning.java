package helseapp.core;

public class Utregning {
    public static double regnUtBMI(double vekt, double hoyde) {
        return vekt/Math.pow(hoyde/100, 2);
    }
}
