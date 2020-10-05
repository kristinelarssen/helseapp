package helseapp.core;

public class Dag{

    private double vekt;
    private double skritt;
    private double treningstid;
    private double protein;
    private double karbo;
    private double fett;

    public Dag(double vekt, double skritt, double treningstid, double protein, double karbo, double fett) {
        this.vekt = vekt;
        this.skritt = skritt;
        this.treningstid = treningstid;
        this.protein = protein;
        this.karbo = karbo;
        this.fett = fett;
    }

    public double getVekt() {
        return vekt;
    }

    public double getTreningstid() {
        return treningstid;
    }

    public double getProtein() {
        return protein;
    }

    public double getKarbo() {
        return karbo;
    }

    public double getFett() {
        return fett;
    }

    public double getSkritt() {
        return skritt;
    }
}