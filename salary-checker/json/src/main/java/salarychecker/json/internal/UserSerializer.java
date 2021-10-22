package salarychecker.json.internal;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import salarychecker.core.EncryptDecrypt;
import salarychecker.core.User;
import salarychecker.core.UserSale;

public class UserSerializer extends JsonSerializer<User> {

/*
format: 
{ 
    "firstname": "...", 
    "lastname": "...", 
    "email": "...",
    "password": "...",
    "socialNumber": "...",
    "employeeNyumber": "...",
    "employerEmail": "...",
    "taxCount": "...",
    "userSale": [...]
}
*/
    EncryptDecrypt encryptDecrypt = new EncryptDecrypt();

    @Override
    public void serialize(User user, JsonGenerator jsonGen, SerializerProvider serializerProvider)
        throws IOException {
            
        jsonGen.writeStartObject();

        jsonGen.writeStringField("firstname", user.getFirstname());
        jsonGen.writeStringField("lastname", user.getLastname());
        jsonGen.writeStringField("email", user.getEmail());
        try {
            jsonGen.writeStringField("password", encryptDecrypt.encrypt(user.getPassword(), user.getFirstname()+user.getLastname()));
        } catch (InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException
                | InvalidAlgorithmParameterException | BadPaddingException | IllegalBlockSizeException e1) {
            e1.printStackTrace();
        }
        try {
            jsonGen.writeStringField("socialNumber", encryptDecrypt.encrypt(user.getSocialNumber(), user.getLastname()+user.getFirstname()));
        } catch (InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException
                | InvalidAlgorithmParameterException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        jsonGen.writeNumberField("employeeNumber", user.getEmployeeNumber());
        jsonGen.writeStringField("employerEmail", user.getEmployerEmail());
        jsonGen.writeNumberField("taxCount", user.getTaxCount());
        jsonGen.writeNumberField("hourRate", user.getHourRate());
        
        if (user.getUserSaleList() != null) {
            jsonGen.writeArrayFieldStart("userSale");
                for (UserSale userSale : user.getUserSaleList()) {
                    jsonGen.writeObject(userSale);
                }
            jsonGen.writeEndArray();
        }

        jsonGen.writeEndObject();
    }
}