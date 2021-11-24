package salarychecker.json.internal;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.NumericNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import java.io.IOException;
import salarychecker.core.Calculation;

/**
 * Class to Deserialize {@link Calculation}.
 */
public class CalculationDeserializer extends JsonDeserializer<Calculation> {

  @Override
  public Calculation deserialize(JsonParser parser, DeserializationContext dctxt)
      throws IOException {

    TreeNode treeNode = parser.getCodec().readTree(parser);
    return deserialize((JsonNode) treeNode);
  }

  Calculation deserialize(JsonNode jsonNode) {
    if (jsonNode instanceof ObjectNode objectNode) {
      Calculation calculation = new Calculation();
      JsonNode salesperiodNode = objectNode.get("salesperiod");

      if (salesperiodNode instanceof TextNode) {
        calculation.setSalesperiod(salesperiodNode.asText());
      }

      JsonNode hoursNode = objectNode.get("hours");
      if (hoursNode instanceof NumericNode) {
        calculation.setHours(hoursNode.asDouble());
      }

      JsonNode mobileNode = objectNode.get("mobileamount");
      if (mobileNode instanceof NumericNode) {
        calculation.setMobileamount(mobileNode.asInt());
      }

      JsonNode paidNode = objectNode.get("paid");
      if (paidNode instanceof NumericNode) {
        calculation.setPaid(paidNode.asDouble());
      }

      return calculation;
    }
    return null;
  }
}
