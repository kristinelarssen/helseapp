package helseapp.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import helseapp.core.Dag;
import helseapp.core.Dager;

public class DagerSerializer {

    @Override
    public void serialize(final Dager dager, final JsonGenerator jsonGen,
                          final SerializerProvider provider) throws IOException {
        jsonGen.writeStartArray(Dager.getLatLongCount());
        for (final Dag dag : Dager) {
            jsonGen.writeObject(dag);
        }
        jsonGen.writeEndArray();
    }

}
