package helseapp.core;

import java.time.LocalDate;
import java.util.Objects;

public class Dag {

  // Definerer alle variablene dag objektet trenger

  private double vekt;
  private double skritt;
  private double treningstid;
  private double protein;
  private double karbo;
  private double fett;
  private LocalDate date;

  public Dag() {}

  /**
   * klasse konstruktør.
   *
   * @param vekt        - vekten til brukeren
   * @param skritt      - antall skritt
   * @param treningstid - tid brukt til trening
   * @param protein     - mengde protein inntatt
   * @param karbo       - mengde karbohydrater inntatt
   * @param fett        - mengde fett inntatt
   * @param date        - dato for dagen
   */

  public Dag(double vekt, double skritt, double treningstid, double protein, double karbo,
      double fett, LocalDate date) {
    this.vekt = vekt;
    this.skritt = skritt;
    this.treningstid = treningstid;
    this.protein = protein;
    this.karbo = karbo;
    this.fett = fett;
    this.date = date;
  }

  /**
   * henter vekten til brukeren.
   *
   * @return vekt - brukers vekt
   */

  public double getVekt() {
    return vekt;
  }

  /**
   * henter treningstiden til brukeren.
   *
   * @return treningstid - brukers treningstid
   */

  public double getTreningstid() {
    return treningstid;
  }

  /**
   * henter proteininntaket til brukeren.
   *
   * @return protein - mengde protein inntatt
   */

  public double getProtein() {
    return protein;
  }

  /**
   * henter karbohydratinntaket til brukeren.
   *
   * @return karbo - mengde karbohydrater inntatt
   */

  public double getKarbo() {
    return karbo;
  }

  /**
   * henter fettinntaket til brukeren.
   *
   * @return fett - mengde fett inntatt
   */

  public double getFett() {
    return fett;
  }

  /**
   * henter antall skritt til brukeren.
   *
   * @return skritt - antall skritt
   */

  public double getSkritt() {
    return skritt;
  }

  /**
   * Regner ut kaloriantallet fra fett, karbohydrater og proteinter.
   * 9 = kal per fettgram, 4 = kal per gram protein og karbohydrater
   *
   * @return kalorier - antall kalorier basert på inntak
   */

  public double getKalorier() {
    return (9 * getFett() + 4 * (getKarbo() + getProtein()));
  }

  /**
   * Henter datoen.
   *
   * @return date - datoen for dagen
   */

  public LocalDate getDate() {
    return date;
  }

  /**
   * Definerer at to dager er like hvis de har den samme datoen.
   *
   * @param o Objekt som sammenlignes
   * @return Returnerer true hvis dagene er like
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null) {
      return false;
    }
    if (o.getClass() != getClass()) {
      return false;
    }
    Dag dag = (Dag) o;
    return this.getDate().equals(dag.getDate());
  }

  /**
   * Baserer hash-funksjonen på datoen til dagen, så den stemmer med equals-metoden.
   *
   * @return Returnerer en hash
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.getDate());
  }
}