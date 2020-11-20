package helseapp.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.time.LocalDate;

class DagerTest {

  private Dager dager;

  @BeforeEach
  void setUp() {
    dager = new Dager();
  }


  @Test
  void testAddDag() {
    LocalDate now = LocalDate.now();
    final Dag dag = new Dag(1, 2, 3, 4, 5, 6, now);
    dager.addDag(dag);
    assertTrue(dager.iterator().hasNext());
    final Dag addedDag = dager.iterator().next();
    assertEquals(dag.getVekt(), addedDag.getVekt());
    assertEquals(dag.getFett(), addedDag.getFett());
    assertEquals(dag.getKarbo(), addedDag.getKarbo());
    assertEquals(dag.getSkritt(), addedDag.getSkritt());
    assertEquals(dag.getProtein(), addedDag.getProtein());
    assertEquals(dag.getTreningstid(), addedDag.getTreningstid());
    assertEquals(dag.getDate(), addedDag.getDate());
    assertEquals(dag.getKalorier(), addedDag.getKalorier());
    }


  private void checkIterator(final Iterator<Dag> it, final Dag... dager) {
    int i = 0;
    while (it.hasNext()) {
      assertTrue(i < dager.length);
      assertSame(dager[i], it.next());
      i++;
    }
    assertEquals(i, dager.length);
  }


  @Test
  void testIterator_addingAndRemovingDag() {
    checkIterator(dager.iterator());
    LocalDate now = LocalDate.now();
    final Dag dag1 = new Dag(1, 2, 3, 4, 5, 6, now);
    final Dag dag2 = new Dag(10, 20, 30, 40, 50, 60, now.minusDays(1));
    final Dag dag3 = new Dag(6, 5, 4, 3, 2, 1, now.minusDays(2));
    dager.addDag(dag1);
    checkIterator(dager.iterator(), dag1);
    dager.addDag(dag2);
    checkIterator(dager.iterator(), dag1, dag2);
    dager.addDag(dag3);
    checkIterator(dager.iterator(), dag1, dag2, dag3);
    final int pos = dager.getDagCount();
    assertEquals(3, pos);
    dager.removeDag(pos - 1);
    checkIterator(dager.iterator(), dag1, dag2);
    dager.removeDag(pos - 3);
    checkIterator(dager.iterator(), dag2);
    dager.removeDag(pos - 3);
    checkIterator(dager.iterator());
  }


  @Test
  void getSetDagTest() {
    LocalDate now = LocalDate.now();
    final Dag dag1 = new Dag(1, 2, 3, 4, 5, 6, now);
    final Dag dag2 = new Dag(10, 20, 30, 40, 50, 60, now.minusDays(1));
    dager.addDag(dag2);
    dager.setDag(0, dag1);
    assertEquals(dag1, dager.getDag(0));
  }

  @Test
  void testEquals() {
    Dag dag1 = new Dag(1,2,3,4,5,6, LocalDate.of(1970, 1, 1));
    Dag dag2 = new Dag(2,3,4,5,6,7, LocalDate.of(1970, 1, 1));
    Dag dag3 = new Dag(1, 2, 3, 4, 5, 6, LocalDate.of(1970, 1, 2));
    assertEquals(dag1, dag1);
    assertEquals(dag1, dag2);
    assertNotEquals(dag1, 1);
    assertNotEquals(dag1, null);
    assertNotEquals(dag1, dag3);
  }

  @Test
  void testHashCode() {
    Dag dag1 = new Dag(1, 2, 3, 4, 5, 6, LocalDate.of(1970, 1, 1));
    Dag dag2 = new Dag(2, 3, 4, 5, 6, 7, LocalDate.of(1970, 1, 1));
    assertEquals(dag1.hashCode(), dag2.hashCode());
    Dag dag3 = new Dag(1, 2, 3, 4, 5, 6, LocalDate.of(1970, 1, 2));
    assertNotEquals(dag1.hashCode(), dag3.hashCode());
  }
}