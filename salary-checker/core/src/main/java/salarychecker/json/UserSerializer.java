package salarychecker.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

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

    @Override
    public void serialize(User user, JsonGenerator jsonGen, SerializerProvider serializerProvider)
        throws IOException {
        jsonGen.writeStartObject();

        jsonGen.writeStringField("firstname", user.getFirstname());
        jsonGen.writeStringField("lastname", user.getLastname());
        jsonGen.writeStringField("email", user.getEmail());
        jsonGen.writeStringField("password", user.getPassword());
        jsonGen.writeStringField("socialNumber", user.getSocialNumber());
        jsonGen.writeNumberField("employeeNumber", user.getEmployeeNumber());
        jsonGen.writeStringField("employerEmail", user.getEmployerEmail());
        jsonGen.writeNumberField("taxCount", user.getTaxCount());
        
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