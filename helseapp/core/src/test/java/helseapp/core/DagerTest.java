package helseapp.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    /* 
    Tester addDager() i Dager 
    Sjekker om et Dag-objekt blir lagt til i Dager sin dager-liste og om feltene er de samme
    */
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

    /* Hjelpemetode til  */
    private void checkIterator(final Iterator<Dag> it, final Dag... dager) {
        int i = 0;
        while (it.hasNext()) {
            assertTrue(i < dager.length);
            assertSame(dager[i], it.next());
            i++;
        }
        assertEquals(i, dager.length);
    }

    /* 
    Terster iterator() i Dager
    Sjekker om dager blir lagt til og fjernet fra et Dager-objekt sin dager liste 
    */
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
        dager.removeDag(pos-1);
        checkIterator(dager.iterator(), dag1, dag2);
        dager.removeDag(pos-3);
        checkIterator(dager.iterator(), dag2);
        dager.removeDag(pos-3);
        checkIterator(dager.iterator());
  }

  /*
    Tester getDag() og setDag()
    Sjekker om setDag() erstatter et dag-objekt i dager-lista på plass 0
    */
    @Test
    void getSetDagTest() {
        LocalDate now = LocalDate.now();
        final Dag dag1 = new Dag(1, 2, 3, 4, 5, 6, now);
        final Dag dag2 = new Dag(10, 20, 30, 40, 50, 60, now.minusDays(1));
        dager.addDag(dag2);
        dager.setDag(0, dag1);
        assertEquals(dag1, dager.getDag(0));
    }

}