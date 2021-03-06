package salarychecker.json.internal;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.NumericNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import java.io.IOException;
import salarychecker.core.EncryptDecrypt;
import salarychecker.core.User;
import salarychecker.core.UserSale;

/**
 * Class to Deserialize {@link User}.
 */
public class UserDeserializer extends JsonDeserializer<User> {

  private final EncryptDecrypt encryptDecrypt = new EncryptDecrypt();
  private final UserSaleDeserializer userSaleDeserializer = new UserSaleDeserializer();

  @Override
  public User deserialize(JsonParser parser, DeserializationContext dctxt)
      throws IOException {

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
      if (lastnameNode instanceof TextNode) {
        user.setLastname(lastnameNode.asText());
      }

      JsonNode emailNode = objectNode.get("email");
      if (emailNode instanceof TextNode) {
        user.setEmail(emailNode.asText());
      }

      JsonNode passwordNode = objectNode.get("password");
      if (passwordNode instanceof TextNode) {
        String decryptedPassword = encryptDecrypt.decrypt(passwordNode.asText(),
              firstnameNode.asText() + lastnameNode.asText());
        user.setPassword(decryptedPassword);
      }

      JsonNode socialNumberNode = objectNode.get("socialNumber");
      if (socialNumberNode instanceof TextNode) {
        String decryptedSocialNumber = encryptDecrypt.decrypt(socialNumberNode.asText(),
              lastnameNode.asText() + firstnameNode.asText());
        user.setSocialNumber(decryptedSocialNumber);
      }

      JsonNode employeeNumberNode = objectNode.get("employeeNumber");
      if (employeeNumberNode instanceof NumericNode) {
        user.setEmployeeNumber(employeeNumberNode.asInt());
      }

      JsonNode employerEmailNode = objectNode.get("employerEmail");
      if (employerEmailNode instanceof TextNode) {
        user.setEmployerEmail(employerEmailNode.asText());
      }

      JsonNode taxCountNode = objectNode.get("taxCount");
      if (taxCountNode instanceof NumericNode) {
        user.setTaxCount(taxCountNode.asDouble());
      }

      JsonNode hourSalNode = objectNode.get("hourRate");
      if (hourSalNode instanceof NumericNode) {
        user.setHourRate(hourSalNode.asDouble());
      }

      JsonNode userSaleNode = objectNode.get("userSale");
      if (userSaleNode instanceof ArrayNode) {
        for (JsonNode elementNode : ((ArrayNode) userSaleNode)) {
          UserSale userSale = userSaleDeserializer.deserialize(elementNode);
          if (userSale != null) {
            user.addUserSale(userSale);
          }
        }
      }
      return user;
    }
    return null;
  }
}
