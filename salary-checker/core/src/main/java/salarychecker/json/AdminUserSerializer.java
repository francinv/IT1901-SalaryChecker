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

import salarychecker.core.AdminUser;
import salarychecker.core.EncryptDecrypt;

public class AdminUserSerializer extends JsonSerializer<AdminUser> {

/*
format: 
{ 
    "firstname": "...", 
    "lastname": "...", 
    "email": "...",
    "password": "...",

}
*/

EncryptDecrypt encryptDecrypt = new EncryptDecrypt();

    @Override
    public void serialize(AdminUser user, JsonGenerator jsonGen, SerializerProvider serializerProvider)
        throws IOException {
            
        jsonGen.writeStartObject();

        jsonGen.writeStringField("firstname", user.getFirstname());
        jsonGen.writeStringField("lastname", user.getLastname());
        jsonGen.writeStringField("email", user.getEmail());
        try {
            jsonGen.writeStringField("password", encryptDecrypt.encrypt(user.getPassword(), user.getFirstname()+user.getLastname()) );
        } catch (InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException
                | InvalidAlgorithmParameterException | BadPaddingException | IllegalBlockSizeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        jsonGen.writeEndObject();
    }
}