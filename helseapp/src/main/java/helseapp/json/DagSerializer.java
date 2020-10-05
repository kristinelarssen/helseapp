package helseapp.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

import helseapp.core.Dag;

public class DagSerializer extends JsonSerializer<Dag>{


    public static final String VEKT_FELT_NAVN = "Vekt";
    public static final String SKRITT_FELT_NAVN = "Skritt";
    public static final String TRENINGSTID_FELT_NAVN = "Treningstid";
    public static final String PROTEIN_FELT_NAVN = "Protein";
    public static final String KARBO_FELT_NAVN = "Karbohydrater";
    public static final String FETT_FELT_NAVN = "Fett";

    @Override
    public void serialize(final Dag dag, final JsonGenerator jsonGenerator, final SerializerProvider prov) throws IOException{
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField(VEKT_FELT_NAVN, dag.getVekt());
        jsonGenerator.writeNumberField(SKRITT_FELT_NAVN, dag.getSkritt());
        jsonGenerator.writeNumberField(TRENINGSTID_FELT_NAVN, dag.getTreningstid());
        jsonGenerator.writeNumberField(PROTEIN_FELT_NAVN, dag.getProtein());
        jsonGenerator.writeNumberField(KARBO_FELT_NAVN, dag.getKarbo());
        jsonGenerator.writeNumberField(FETT_FELT_NAVN, dag.getFett());
        jsonGenerator.writeEndObject();
    }

}
