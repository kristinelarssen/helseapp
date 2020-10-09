package helseapp.core;

import java.time.LocalDate;

public class Dag{

    private double vekt;
    private double skritt;
    private double treningstid;
    private double protein;
    private double karbo;
    private double fett;
    private LocalDate date;

    public Dag(double vekt, double skritt, double treningstid, double protein, double karbo, double fett, LocalDate date) {
        this.vekt = vekt;
        this.skritt = skritt;
        this.treningstid = treningstid;
        this.protein = protein;
        this.karbo = karbo;
        this.fett = fett;
        this.date = date;
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

    public LocalDate getDate() { return date; }
}