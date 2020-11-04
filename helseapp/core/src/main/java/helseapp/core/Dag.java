package helseapp.core;

import java.time.LocalDate;

public class Dag{

    //Definerer alle variablene dag objektet trenger

    private double vekt;
    private double skritt;
    private double treningstid;
    private double protein;
    private double karbo;
    private double fett;
    private LocalDate date;

    /**
	* klasse konstruktør
	* @param vekt - vekten til brukeren
	* @param skritt - antall skritt
	* @param treningstid - tid brukt til trening
	* @param protein - mengde protein inntatt
	* @param karbo - mengde karbohydrater inntatt
	* @param fett - mengde fett inntatt
	* @param date - dato for dagen
	*/

    public Dag(double vekt, double skritt, double treningstid, double protein, double karbo, double fett, LocalDate date) {
        this.vekt = vekt;
        this.skritt = skritt;
        this.treningstid = treningstid;
        this.protein = protein;
        this.karbo = karbo;
        this.fett = fett;
        this.date = date;
    }
    
	/**
	* henter vekten til brukeren
	* @return vekt - brukers vekt
	*/

    public double getVekt() {
        return vekt;
    }

    /**
	* henter treningstiden til brukeren
	* @return treningstid - brukers treningstid
	*/

    public double getTreningstid() {
        return treningstid;
    }

	/**
	* henter proteininntaket til brukeren
	* @return protein - mengde protein inntatt
	*/

    public double getProtein() {
        return protein;
    }

	/**
	* henter karbohydratinntaket til brukeren
	* @return karbo - mengde karbohydrater inntatt
	*/

    public double getKarbo() {
        return karbo;
    }

	/**
	* henter fettinntaket til brukeren
	* @return fett - mengde fett inntatt
	*/

    public double getFett() {
        return fett;
    }

	/**
	* henter antall skritt til brukeren
	* @return skritt - antall skritt
	*/

    public double getSkritt() {
        return skritt;
    }

	/**
	* henter datoen
	* @return date - datoen for dagen
	*/

    public LocalDate getDate() { return date; }
}