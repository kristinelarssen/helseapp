package helseapp.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import helseapp.core.Dager;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

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
}
