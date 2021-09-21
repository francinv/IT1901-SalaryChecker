package salarychecker.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import java.io.IOException;
import salarychecker.core.User;

public class UserDeserializer extends JsonDeserializer<User> {

    @Override
    public User deserialize(JsonParser parser, DeserializationContext ctxt)
        throws IOException, JsonProcessingException {

        TreeNode treeNode = parser.getCodec().readTree(parser);
        return deserialize((JsonNode) treeNode);
    }

    User deserialize(JsonNode jsonNode) {
    if (jsonNode instanceof ObjectNode objectNode) {
        User user = new User();
        JsonNode firstnameNode = objectNode.get("firstname");

        if (firstnameNode instanceof TextNode) {
            user.setFirstname(firstnameNode.asText());
        }

        JsonNode lastnameNode = objectNode.get("lastname");
        if (lastnameNode instanceof BooleanNode) {
            user.setLastname(lastnameNode.asText());
        }

        JsonNode emailNode = objectNode.get("email");
        if (emailNode instanceof TextNode) {
            user.setEmail(emailNode.asText());
        }

        JsonNode passwordNode = objectNode.get("password");
        if (passwordNode instanceof TextNode) {
            user.setPassword(passwordNode.asText());
        }

        JsonNode socialNumberNode = objectNode.get("socialNumber");
        if (socialNumberNode instanceof TextNode) {
            user.setSocialNumber(socialNumberNode.longValue());
        }

        JsonNode employeeNumberNode = objectNode.get("employeeNumber");
        if (employeeNumberNode instanceof TextNode) {
            user.setEmployeeNumber(employeeNumberNode.intValue());
        }

        JsonNode employerEmailNode = objectNode.get("employerEmail");
        if (employerEmailNode instanceof TextNode) {
            user.setEmployerEmail(employerEmailNode.asText());
        }

        JsonNode taxCountNode = objectNode.get("taxCount");
        if (taxCountNode instanceof TextNode) {
            user.setTaxCount(taxCountNode.intValue());
        }
        
        return user;
    }
        return null;
    }   
}
