package salarychecker.json;

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

        jsonGen.writeEndObject();
    }
}