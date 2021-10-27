package salarychecker.json.internal;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import salarychecker.core.AdminUser;
import salarychecker.core.EncryptDecrypt;

/**
 * Class to Deserialize {@link AdminUser}.
 */
public class AdminUserDeserializer extends JsonDeserializer<AdminUser> {

  private final EncryptDecrypt encryptDecrypt = new EncryptDecrypt();

  @Override
  public AdminUser deserialize(JsonParser parser, DeserializationContext ctxt)
      throws IOException {

    TreeNode treeNode = parser.getCodec().readTree(parser);
    return deserialize((JsonNode) treeNode);
  }

  private AdminUser deserialize(JsonNode jsonNode) {
    if (jsonNode instanceof ObjectNode objectNode) {
      AdminUser user = new AdminUser();
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

        try {
          String decryptedPassword = encryptDecrypt.decrypt(passwordNode.asText(),
              firstnameNode.asText() + lastnameNode.asText());
          user.setPassword(decryptedPassword);
        } catch (InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException
                | InvalidAlgorithmParameterException | BadPaddingException
                | IllegalBlockSizeException e) {
          e.printStackTrace();
        }
      }
      return user;
    }
    return null;
  }
}