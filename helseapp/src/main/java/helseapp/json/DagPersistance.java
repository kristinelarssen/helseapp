package helseapp.json;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import helseapp.core.Dager;

public class DagPersistance {

    private ObjectMapper mapper;

    public DagPersistance(){
        mapper = new ObjectMapper();
        mapper.registerModule(new DagerModule());
    }

    public Dager readDager(Reader reader) throws IOException {
        return mapper.readValue(reader, Dager.class);
    }

    public void writeDager(Dager dager, Writer writer) throws IOException{
        mapper.writerWithDefaultPrettyPrinter().writeValue(writer, dager);
    }
}
