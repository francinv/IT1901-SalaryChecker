package salarychecker.json.internal;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import salarychecker.core.EncryptDecrypt;
import salarychecker.core.User;
import salarychecker.core.UserSale;

/**
 * Class to Deserialize {@link User}.
 */
public class UserSerializer extends JsonSerializer<User> {

  private final EncryptDecrypt encryptDecrypt = new EncryptDecrypt();
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
    jsonGen.writeStringField("password",
        encryptDecrypt.encrypt(user.getPassword(), user.getFirstname() + user.getLastname()));
    jsonGen.writeStringField("socialNumber", encryptDecrypt.encrypt(user.getSocialNumber(),
        user.getLastname() + user.getFirstname()));
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