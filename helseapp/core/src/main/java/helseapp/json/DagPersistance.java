package helseapp.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import helseapp.core.Dag;
import helseapp.core.Dager;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DagPersistance {

  private ObjectMapper mapper;

  public DagPersistance() {
    mapper = new ObjectMapper();
    mapper.registerModule(new DagerModule());
  }

  /**
   * read Dager object from json using ObjectMapper.
   *
   * @param reader - Reader object
   * @return dager - Dager object
   */

  public Dager readDager(Reader reader) throws IOException {
    Dager dager = mapper.readValue(reader, Dager.class);
    return dager;
  }



  /**
   * Write Dager object to json using ObjectMapper, such that the data is layed.
   * out vertically
   *
   * @param dager  - Dager object
   * @param writer - Writer object
   * @throws IOException - IOException
   */

  public void writeDager(Dager dager, Writer writer) throws IOException {
    mapper.writerWithDefaultPrettyPrinter().writeValue(writer, dager);
  }



  /**
   * Save Dager objects to a json-file.
   *
   * @param savePath - String path to json-file
   * @param dager    - Dager oject
   */

  void save(String savePath, Dager dager) {
    if (savePath != null) {
      Path path = Paths.get(savePath);
      try (Writer writer = new FileWriter(path.toFile(), StandardCharsets.UTF_8)) {
        this.writeDager(dager, writer);
      } catch (IOException e) {
        System.err.println("Fikk ikke skrevet til dager.json på hjemme området " + e);
      }
    }
  }



  /**
   * Read json-file and create Dager objects.
   *
   * @param loadPath - String path to json-file
   * @return dager - Dager object
   */

  public Dager read(String loadPath) {
    Reader reader = null;
    Dager dager = null;
    if (loadPath != null) {
      try {
        reader = new FileReader(Paths.get(loadPath).toFile(), StandardCharsets.UTF_8);
      } catch (IOException e) {
        System.err.println("Feil! " + e);
      }
    }
    try {
      dager = this.readDager(reader);
    } catch (IOException e) {
      System.err.println("Feil_2" + e);
    } finally {
      if (reader != null) {
        try {
          reader.close();
        } catch (IOException e) {
          System.out.println("Feil_3");
        }
      }
    }
    return dager;
  }



  /**
   * Handles saving of a single Dag object, makes sure that the same day is not.
   * saved twice, and that the last registration is the one that is saved
   *
   * @param filePath - String path to json-file
   * @param dag      - Dag object
   */

  public void saveDag(Dag dag, String filePath) {
    Dager dager = read(filePath);
    for (int i = 0; i < dager.getDagCount(); i++) {
      if (dager.getDag(i).getDate().equals(dag.getDate())) {
        dager.removeDag(i);
      }
    }
    dager.addDag(dag);
    save(filePath, dager);
  }
}
