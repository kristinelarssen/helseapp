package helseapp.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class DagerTest {

    private Dager dager;

    @BeforeEach
    public void setUp() {
        dager = new Dager();
    }

    @Test
    public void testAddDag() {
        Dag dag = new Dag(1,2,3,4,5,6);
        dager.addDag(dag);
        assertTrue(dager.iterator().hasNext());
        Dag addedDag = dager.iterator().next();
        assertEquals(dag.getVekt(), addedDag.getVekt());
        assertEquals(dag.getFett(), addedDag.getFett());
        assertEquals(dag.getKarbo(), addedDag.getKarbo());
        assertEquals(dag.getSkritt(), addedDag.getSkritt());
        assertEquals(dag.getProtein(), addedDag.getProtein());
        assertEquals(dag.getTreningstid(), addedDag.getTreningstid());
    }

}