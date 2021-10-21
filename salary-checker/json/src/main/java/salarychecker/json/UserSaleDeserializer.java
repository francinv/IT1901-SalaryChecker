package salarychecker.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.DoubleNode;
import com.fasterxml.jackson.databind.node.NumericNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import java.io.IOException;
import salarychecker.core.UserSale;

public class UserSaleDeserializer extends JsonDeserializer<UserSale> {

    @Override
    public UserSale deserialize(JsonParser parser, DeserializationContext ctxt)
        throws IOException, JsonProcessingException {

        TreeNode treeNode = parser.getCodec().readTree(parser);
        return deserialize((JsonNode) treeNode);
    }

    UserSale deserialize(JsonNode jsonNode) {
    if (jsonNode instanceof ObjectNode objectNode) {
        UserSale userSale = new UserSale();
        
        JsonNode salesperiodNode = objectNode.get("salesperiod");
        if (salesperiodNode instanceof TextNode) {
            userSale.setSalesperiod(salesperiodNode.asText());
        }

        JsonNode expectedNode = objectNode.get("expected");
        if (expectedNode instanceof NumericNode) {
            userSale.setExpected(expectedNode.asDouble());
        }

        JsonNode paidNode = objectNode.get("paid");
        if (paidNode instanceof NumericNode) {
            userSale.setPaid(paidNode.asDouble());
        }

        JsonNode diffenceNode = objectNode.get("difference");
        if (diffenceNode instanceof NumericNode) {
            userSale.setDifference();
        }
        
        return userSale;
    }
        return null;
    }   
}
