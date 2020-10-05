package helseapp.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

import helseapp.core.Dag;

public class DagSerializer extends JsonSerializer<Dag>{


    public static final String VEKT_FELT_NAVN = "Vekt";
    public static final String HOYDE_FELT_NAVN = "Hoyde";

    @Override
    public void serialize(final Dag dag, final JsonGenerator jsonGenerator, final SerializerProvider prov) throws IOException{
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField(VEKT_FELT_NAVN, dag.getVekt());
        jsonGenerator.writeNumberField(HOYDE_FELT_NAVN, dag.getHoyde());
        jsonGenerator.writeEndObject();
    }

}
