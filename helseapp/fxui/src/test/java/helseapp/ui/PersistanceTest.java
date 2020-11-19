package helseapp.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import helseapp.core.Dag;
import helseapp.core.Dager;
import org.json.JSONException;
import org.junit.jupiter.api.Test;

public class PersistanceTest {

  @Test
  void testObjectMapper_initial() {
    assertNotNull(Persistance.getDefaultObjectMapper());
  }

  @Test
  void testCreateJsonDagString() {
    Dag dag = new Dag(1, 2, 3, 4, 5, 6, LocalDate.of(1970, 1, 1));
    String dagString = Persistance.createJsonDagString(dag);
    assertEquals("{\"fett\":6,\"date\":\"1970-01-01\",\"protein\":4,\"karbo\":5,\"skritt\":2,\"treningstid\":3,\"vekt\":1}", dagString);
  }

  @Test
  void testParseJsonToDager() {
    Dager dager = new Dager();
    try {
      dager = Persistance.parseJsonToDager("[{\"fett\":6,\"date\":\"1970-01-01\",\"protein\":4,\"karbo\":5,\"skritt\":2,\"treningstid\":3,\"vekt\":1}]");
    } catch (JSONException e) {
    } finally {
      Dag dag = dager.getDag(0);
      assertEquals(1.0, dag.getVekt());
      assertEquals(2, dag.getSkritt());
      assertEquals(3.0, dag.getTreningstid());
      assertEquals(4.0, dag.getProtein());
      assertEquals(5.0, dag.getKarbo());
      assertEquals(6.0, dag.getFett());
      assertEquals(LocalDate.of(1970, 1, 1), dag.getDate());
    }
  }

  @Test
  void testParseJsonToDag() {
    Dag dag = new Dag();
    try {
      dag = Persistance.parseJsonToDag("{\"fett\":6,\"date\":\"1970-01-01\",\"protein\":4,\"karbo\":5,\"skritt\":2,\"treningstid\":3,\"vekt\":1}");
    } catch (JsonProcessingException e) {
    } finally {
      assertEquals(1.0, dag.getVekt());
      assertEquals(2, dag.getSkritt());
      assertEquals(3.0, dag.getTreningstid());
      assertEquals(4.0, dag.getProtein());
      assertEquals(5.0, dag.getKarbo());
      assertEquals(6.0, dag.getFett());
      assertEquals(LocalDate.of(1970, 1, 1), dag.getDate());
    }
  }

  @Test
  void testLoad1() {
    Dag dataDag = new Dag(1,2,3,4,5,6,LocalDate.of(1970,1,1));
    Persistance persistance = new Persistance();
    Dager dager = (Dager) persistance.load("http://localhost:8080/dager");
    List dagerList = dager.getDager();
    for (Object testDagObj : dagerList) {
      if (dataDag.equals(testDagObj)) {
        Dag testDag = (Dag) testDagObj;
        assertEquals(1.0, testDag.getVekt());
        assertEquals(2, testDag.getSkritt());
        assertEquals(3.0, testDag.getTreningstid());
        assertEquals(4.0, testDag.getProtein());
        assertEquals(5.0, testDag.getKarbo());
        assertEquals(6.0, testDag.getFett());
      }
    }
  }

  @Test
  void testLoad2() {
    Dag dataDag = new Dag(1,2,3,4,5,6,LocalDate.of(1970,1,1));
    Persistance persistance = new Persistance();
    Dag testDag = (Dag) persistance.load("http://localhost:8080/dager/1970-01-01");
    assertEquals(1.0, testDag.getVekt());
    assertEquals(2, testDag.getSkritt());
    assertEquals(3.0, testDag.getTreningstid());
    assertEquals(4.0, testDag.getProtein());
    assertEquals(5.0, testDag.getKarbo());
    assertEquals(6.0, testDag.getFett());
  }
}