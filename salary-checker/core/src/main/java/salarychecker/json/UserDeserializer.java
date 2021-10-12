package salarychecker.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.DoubleNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import salarychecker.core.EncryptDecrypt;
import salarychecker.core.User;

public class UserDeserializer extends JsonDeserializer<User> {

    private EncryptDecrypt encryptDecrypt = new EncryptDecrypt();

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
                String decryptedPassword = encryptDecrypt.decrypt(passwordNode.asText(), firstnameNode.asText());
                user.setPassword(decryptedPassword);
            } catch (InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException
                    | InvalidAlgorithmParameterException | BadPaddingException | IllegalBlockSizeException e) {
                // TODO Auto-generated catch block
                //e.printStackTrace();
            }
    
        }

        JsonNode socialNumberNode = objectNode.get("socialNumber");
        if (socialNumberNode instanceof TextNode) {

            try {
                String decryptedSocialNumber = encryptDecrypt.decrypt(socialNumberNode.asText(), firstnameNode.asText());
                user.setSocialNumber(decryptedSocialNumber);
            } catch (InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException
                    | InvalidAlgorithmParameterException | BadPaddingException | IllegalBlockSizeException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }

        JsonNode employeeNumberNode = objectNode.get("employeeNumber");
        if (employeeNumberNode instanceof IntNode) {
            user.setEmployeeNumber(employeeNumberNode.intValue());
        }

        JsonNode employerEmailNode = objectNode.get("employerEmail");
        if (employerEmailNode instanceof TextNode) {
            user.setEmployerEmail(employerEmailNode.asText());
        }

        JsonNode taxCountNode = objectNode.get("taxCount");
        if (taxCountNode instanceof DoubleNode) {
            user.setTaxCount(taxCountNode.doubleValue());
        }
        
        return user;
    }
        return null;
    }   
}
