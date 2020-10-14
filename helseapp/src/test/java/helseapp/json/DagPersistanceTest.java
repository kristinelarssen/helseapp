package helseapp.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.time.LocalDate;
import java.util.Iterator;
import org.junit.jupiter.api.Test;
import helseapp.core.Dag;
import helseapp.core.Dager;
import java.time.LocalDate;

public class DagPersistanceTest {

    private DagPersistance dagPersistance = new DagPersistance();

    /* 
    Terster serializer og deserializer
    Lager Dag objekter som legges til i et Dager-objekt. Skriver Dager, og leser av til nytt Dager2-objekt.
    Itererer gjennom Dager2-objektet og sjekker om det har samme dato som Dager-objektet.  
    */

    @Test
    public void testSerializersDeserializers() {
        LocalDate now = LocalDate.now();
        final Dag dag1 = new Dag(1, 2, 3, 4, 5, 6, now);
        final Dag dag2 = new Dag(10, 20, 30, 40, 50, 60, now.minusDays(1));
        final Dag dag3 = new Dag(6, 5, 4, 3, 2, 1, now.minusDays(2));
        Dager dager = new Dager(dag1, dag2, dag3);
        try {
            StringWriter writer = new StringWriter();
            dagPersistance.writeDager(dager, writer);
            String json = writer.toString();
            Dager dager2 = dagPersistance.readDager(new StringReader(json));
            Iterator<Dag> it = dager2.iterator();
            assertEquals(dag1.getDate(), it.next().getDate());
            assertTrue(it.hasNext());
            assertEquals(dag2.getDate(), it.next().getDate());
            assertTrue(it.hasNext());
            assertEquals(dag3.getDate(), it.next().getDate());
            assertFalse(it.hasNext());
        } catch (IOException e) {
            fail();
     }
  }


}