package helseapp.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import helseapp.core.Dag;
import helseapp.core.Dager;

public class DagerSerializer extends JsonSerializer<Dager> {

    @Override
    public void serialize(final Dager dager, final JsonGenerator jsonGen,
                          final SerializerProvider provider) throws IOException {
        jsonGen.writeStartArray(dager.getDagCount());
        for (final Dag dag : dager) {
            jsonGen.writeObject(dag);
        }
        jsonGen.writeEndArray();
    }

}
