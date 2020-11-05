package helseapp.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import helseapp.core.Dager;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import helseapp.core.Dag;

public class DagerDeserializer extends JsonDeserializer<Dager>{

    private final DagDeserializer dagDeserializer = new DagDeserializer();

    /**
     * Deserilize dager object from json
     * @param jsonParser - JsonParser object
     * @param deserializationContext - DeserializationContext object
     * @throws IOException
     * @throws JsonProcessingException
     * @return Dager object
     */
    @Override
    public Dager deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException,
            JsonProcessingException {
        final JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        if(jsonNode instanceof ArrayNode){
            final ArrayNode dagerArray = (ArrayNode) jsonNode;
            final Collection<Dag> dager = new ArrayList<>(dagerArray.size());
            for(final JsonNode dagNode : dagerArray){
                final Dag dag = dagDeserializer.deserialize(dagNode);
                dager.add(dag);
            }
            return new Dager(dager);
        }
        return null;
    }
}
