package salarychecker.json.internal;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
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
 * Class to serialize {@link AdminUser}.
 */
public class AdminUserSerializer extends JsonSerializer<AdminUser> {

  /*
  format:
  {
    "firstname": "...", 
    "lastname": "...", 
    "email": "...",
    "password": "..."
  }
  */

  private final EncryptDecrypt encryptDecrypt = new EncryptDecrypt();

  @Override
  public void serialize(AdminUser user, JsonGenerator jsonGen,
                        SerializerProvider serializerProvider)
      throws IOException {

    jsonGen.writeStartObject();

    jsonGen.writeStringField("firstname", user.getFirstname());
    jsonGen.writeStringField("lastname", user.getLastname());
    jsonGen.writeStringField("email", user.getEmail());

    try {
      jsonGen.writeStringField("password", encryptDecrypt.encrypt(user.getPassword(),
          user.getFirstname() + user.getLastname()));
    } catch (InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException
            | InvalidAlgorithmParameterException | BadPaddingException
            | IllegalBlockSizeException e) {
      e.printStackTrace();
    }

    jsonGen.writeEndObject();
  }
}