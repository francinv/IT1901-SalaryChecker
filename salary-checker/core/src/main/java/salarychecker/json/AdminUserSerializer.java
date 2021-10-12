package salarychecker.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import salarychecker.core.AdminUser;

public class AdminUserSerializer extends JsonSerializer<AdminUser> {

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

    @Override
    public void serialize(AdminUser user, JsonGenerator jsonGen, SerializerProvider serializerProvider)
        throws IOException {
            
        jsonGen.writeStartObject();

        jsonGen.writeStringField("firstname", user.getFirstname());
        jsonGen.writeStringField("lastname", user.getLastname());
        jsonGen.writeStringField("email", user.getEmail());
        jsonGen.writeStringField("password", user.getPassword());

        jsonGen.writeEndObject();
    }
}