package helseapp.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.beans.Transient;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.time.LocalDate;
import java.util.Iterator;
import org.junit.jupiter.api.Test;
import helseapp.core.Dag;
import helseapp.core.Dager;
import java.time.LocalDate;

class DagPersistanceTest {

  private DagPersistance dagPersistance = new DagPersistance();
  String path = "../core/src/test/java/helseapp/json/dager2.json";

  /*
   * Terster serializer og deserializer Lager Dag objekter som legges til i et
   * Dager-objekt. Skriver Dager, og leser av til nytt Dager2-objekt. Itererer
   * gjennom Dager2-objektet og sjekker om det har samme dato som Dager-objektet.
   */

  @Test
  void testSerializersDeserializers() {
    LocalDate now = LocalDate.now();
    final Dag dag1 = new Dag(1, 2, 3, 4, 5, 6, now);
    final Dag dag2 = new Dag(10, 20, 30, 40, 50, 60, now.minusDays(1));
    final Dag dag3 = new Dag(6, 5, 4, 3, 2, 1, now.minusDays(2));
    Dager dager = new Dager(dag1, dag2, dag3);
    dagPersistance.save(path, dager);
    Dager dager2 = dagPersistance.read(path);
    Iterator<Dag> it = dager2.iterator();
    assertTrue(it.hasNext());
    checkDag(it.next(), dag1);
    assertTrue(it.hasNext());
    checkDag(it.next(), dag2);
    assertTrue(it.hasNext());
    checkDag(it.next(), dag3);
    assertFalse(it.hasNext());
  }

  @Test
  void testSaveDag() {
      LocalDate now = LocalDate.now();
      Dag dag1 = new Dag(11, 22, 33, 44, 55, 66, now);
      dagPersistance.saveDag(dag1, path);
      Dager dager = dagPersistance.read(path);
      Iterator<Dag> it = dager.iterator();
      while(it.hasNext()) {
        Dag nextDay = it.next();
        if (nextDay.getDate() == dag1.getDate()) {
            checkDag(nextDay, dag1);
        }
      }
  }


  static void checkDag(Dag dag, double vekt, double skritt, double treningstid, double protein, double karbo,
      double fett, LocalDate date) {
    assertEquals(vekt, dag.getVekt());
    assertEquals(skritt, dag.getSkritt());
    assertEquals(treningstid, dag.getTreningstid());
    assertEquals(protein, dag.getProtein());
    assertEquals(karbo, dag.getKarbo());
    assertEquals(fett, dag.getFett());
    assertEquals(date, dag.getDate());
  }

  static void checkDag(Dag dag1, Dag dag2) {
    checkDag(dag1, dag2.getVekt(), dag2.getSkritt(), dag2.getTreningstid(), dag2.getProtein(), dag2.getKarbo(),
        dag2.getFett(), dag2.getDate());
  }

}