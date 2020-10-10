package helseapp.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import helseapp.core.Dag;
import helseapp.core.Dager;

public class DagerSerializer extends JsonSerializer<Dager> {

    /**
     * Serialize Dager object to Json
     * @param dager - dager object
     * @param jsonGen - JsonGenerator
     * @param provider - SerializerProvider
     * @throws IOException
     */
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
