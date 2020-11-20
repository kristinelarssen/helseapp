package helseapp.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class Dager implements Iterable<Dag> {

  // liste med dag objekter
  private final List<Dag> dager = new ArrayList<>();

  public Dager() {}

  /**
  * metode for å legge til dager.
  *
  * @param dager - dager objekt
  */

  public Dager(final Dag... dager) {
    addDager(dager);
  }

  /**
  * metode for å legge til dager.
  *
  * @param dager - dager objekt
  */

  public Dager(final Collection<Dag> dager) {
    addDager(dager);
  }

  /**
  * iterator for dager.
  *
  * @return dager - iterator for elementene i dager
  */

  @NotNull
  @Override
  public Iterator<Dag> iterator() {
    return dager.iterator();
  }

  /**
  * henter størrelsen på dager listen.
  *
  * @return dager.size() - størrelsen på dager listen
  */

  public int getDagCount() {
    return dager.size();
  }

  /**
  * henter dagen med indeks num.
  *
  * @param num - indeks tall for hvilken dag som skal hentes ut
  * @return dag - dag med indeksen num som hentes ut
  */

  public Dag getDag(final int num) {
    return dager.get(num);
  }

  /**
  * metode for å sette et nummer for et dagobjekt.
  *
  * @param num - nummer som skal settes for dagobjekt
  * @param dag - dag som skal få satt et nummer til seg
  */

  void setDag(final int num, final Dag dag) {
    dager.set(num, dag);
  }

  /**
  * metode for å legge til dag i listen av dagerobjekt.
  *
  * @param dag - dagen som skal legges til
  * @return pos - posisjonen til dag objektet i dager listen
  */

  public int addDag(final Dag dag) {
    final int pos = dager.size();
    dager.add(dag);
    return pos;
  }

  /**
  * metode for å legge til en collection av dager på slutten av listen av.
  * dagerobjekt
  *
  * @param dager - collection av dagerobjekt som skal legges til
  * @return pos - posisjon til første av dagene som legges til
  */

  private int addDager(final Collection<Dag> dager) {
    final int pos = this.dager.size();
    this.dager.addAll(dager);
    return pos;
  }

  /**
  * metode for å gjøre en liste med dagerobjekter til en collection og legge dem.
  * til liste av eksisterende dagerobjekt
  *
  * @param dager - liste av dagerobjekt som skal legges til
  * @return addDager - kaller metoden over og returnerer pos, posisjonen til
  *         første av dagene som legges til
  */

  private int addDager(final Dag... dager) {
    return addDager(List.of(dager));
  }

  /**
  * Fjerner dag objekt i listen dager med indeks num.
  *
  * @param num - indeks for hvilken dag som skal bli fjernet
  * @return dager - returnerer listen med dager uten dag objektet som ble fjernet
  */

  public Dag removeDag(final int num) {
    return dager.remove(num);
  }

  /**
   * Returnerer alle dagene i Dager-objektet.
   *
   * @return dager - et List-object som inneholder alle dagene
   */
  public List<Dag> getDager() {
    return this.dager;
  }

}
