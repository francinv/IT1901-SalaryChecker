package salarychecker.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import salarychecker.core.Accounts;
import salarychecker.core.User;

import java.io.IOException;

public class AccountsSerializer extends JsonSerializer<Accounts> {

  /*
   * format: { "name": "...", "items": [ ... ] }
   */

  @Override
  public void serialize(Accounts accounts, JsonGenerator jsonGen,
      SerializerProvider serializerProvider) throws IOException {
    jsonGen.writeStartObject();

    if (accounts.getAccounts() != null) {
      jsonGen.writeArrayFieldStart("Accounts");
      for (User user : accounts.getAccounts()) {
        jsonGen.writeObject(user);
      }
      jsonGen.writeEndArray();
    }
    jsonGen.writeEndObject();
  }
}

