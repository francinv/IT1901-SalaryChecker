package salarychecker.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import salarychecker.core.Accounts;
import salarychecker.core.AdminUser;
import salarychecker.core.User;

public class AccountsDeserializer extends JsonDeserializer<Accounts> {

    private UserDeserializer userDeserializer = new UserDeserializer();
    private AdminUserDeserializer adminUserDeserializer = new AdminUserDeserializer();
    /*
    * format: { "Accounts": [ ... ] }
    */

    @Override
    public Accounts deserialize(JsonParser parser, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        TreeNode treeNode = parser.getCodec().readTree(parser);
        return deserialize((JsonNode) treeNode);
    }

    Accounts deserialize(JsonNode treeNode) {
    if (treeNode instanceof ObjectNode objectNode) {
        JsonNode accountsNode = objectNode.get("Accounts");
        if (! (accountsNode instanceof ArrayNode)) {
            return null;
        }
        Accounts accounts = new Accounts();
        
        for (JsonNode elementNode : ((ArrayNode) accountsNode)) {
            if (elementNode.size() == 4) {
                AdminUser adminUser = adminUserDeserializer.deserialize(elementNode);
                if (adminUser != null) {
                    accounts.addUser(adminUser);
                }
            }
            else {
                User user = userDeserializer.deserialize(elementNode);
                if (user != null) {
                    accounts.addUser(user);
                }
            }
        }
        return accounts;
    }
    return null;
    }   
}