package helseapp.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import helseapp.core.Dag;

public class DagDeserializer extends JsonDeserializer<Dag> {

    private static final int ARRAY_JSON_NODE_SIZE = 2;

    @Override
    public Dag deserialize(final JsonParser jsonParser,
                               final DeserializationContext deserContext) throws IOException, JsonProcessingException {
        final JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        return deserialize(jsonNode);
    }

    public Dag deserialize(final JsonNode jsonNode) throws JsonProcessingException {
        if (jsonNode instanceof ObjectNode) {
            final ObjectNode objectNode = (ObjectNode) jsonNode;
            final double vekt =
                    objectNode.get(DagSerializer.VEKT_FELT_NAVN ).asDouble();
            final double hoyde =
                    objectNode.get(DagSerializer.HOYDE_FELT_NAVN ).asDouble();
            return new Dag(vekt, hoyde);
        } else if (jsonNode instanceof ArrayNode) {
            final ArrayNode dagArray = (ArrayNode) jsonNode;
            if (dagArray.size() == ARRAY_JSON_NODE_SIZE) {
                final double vekt = dagArray.get(0).asDouble();
                final double hoyde = dagArray.get(1).asDouble();
                return new Dag(vekt, hoyde);
            }
        }
        return null;
    }
}

