package helseapp.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import helseapp.core.Dag;
import java.io.IOException;
import java.time.LocalDate;

public class DagDeserializer extends JsonDeserializer<Dag> {

  private static final int ARRAY_JSON_NODE_SIZE = 2;

  /**
   * Deserialize dag from json.
   *
   * @param jsonParser   - JsonParser object
   * @param deserContext - DeserializationContext
   * @throws IOException             - IOException
   * @throws JsonProcessingException -JsonProcessingException
   * @return dag - Dag object
   */
  @Override
  public Dag deserialize(final JsonParser jsonParser, final DeserializationContext deserContext)
      throws IOException, JsonProcessingException {
    final JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
    return deserialize(jsonNode);
  }

  /**
   * Deserialize dag from json.
   *
   * @param jsonNode - JsonNode object
   * @return dag - Dag object
   * @throws JsonProcessingException - JsonProcessingException
   */
  public Dag deserialize(final JsonNode jsonNode) throws JsonProcessingException {
    // If the json-node is a dag object
    if (jsonNode instanceof ObjectNode) {
      // Creates object from the json-node
      final ObjectNode objectNode = (ObjectNode) jsonNode;
      // Get all attributes of the object
      final double vekt = objectNode.get(DagSerializer.VEKT_FELT_NAVN).asDouble();
      final double skritt = objectNode.get(DagSerializer.SKRITT_FELT_NAVN).asDouble();
      final double treningstid = objectNode.get(DagSerializer.TRENINGSTID_FELT_NAVN).asDouble();
      final double protein = objectNode.get(DagSerializer.PROTEIN_FELT_NAVN).asDouble();
      final double karbo = objectNode.get(DagSerializer.KARBO_FELT_NAVN).asDouble();
      final double fett = objectNode.get(DagSerializer.FETT_FELT_NAVN).asDouble();
      final String date_string = objectNode.get(DagSerializer.DATO_FELT_NAVN).asText();
      final LocalDate date = LocalDate.parse(date_string);
      // Create the object
      return new Dag(vekt, skritt, treningstid, protein, karbo, fett, date);
      
      // If the json-node is an ArrayNode
    } else if (jsonNode instanceof ArrayNode) {
      // Creates ArrayNode from the json-node
      final ArrayNode dagArray = (ArrayNode) jsonNode;
      if (dagArray.size() == ARRAY_JSON_NODE_SIZE) {
        // Get all attributes of the object
        final double vekt = dagArray.get(0).asDouble();
        final double skritt = dagArray.get(1).asDouble();
        final double treningstid = dagArray.get(2).asDouble();
        final double protein = dagArray.get(3).asDouble();
        final double karbo = dagArray.get(4).asDouble();
        final double fett = dagArray.get(5).asDouble();
        final String date_string = dagArray.get(6).asText();
        final LocalDate date = LocalDate.parse(date_string);
        // Create the Dag object
        return new Dag(vekt, skritt, treningstid, protein, karbo, fett, date);
      }
    }
    return null;
  }
}
