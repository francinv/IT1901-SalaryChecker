package salarychecker.json.internal;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import salarychecker.core.Calculation;

/**
 * Class to Deserialize {@link Calculation}.
 */
public class CalculationSerializer extends JsonSerializer<Calculation> {

  /*
  format:
  {
    "salesperiod": "...",
    "hours": "...",
    "mobileamount": "...",
    "paid": "...",
  }
  */

  @Override
  public void serialize(Calculation calculation, JsonGenerator jsonGen, 
      SerializerProvider serializerProvider)
      throws IOException {
    jsonGen.writeStartObject();

    jsonGen.writeStringField("salesperiod", calculation.getSalesperiod());
    jsonGen.writeNumberField("hours", calculation.getHours());
    jsonGen.writeNumberField("mobileamount", calculation.getMobileamount());
    jsonGen.writeNumberField("paid", calculation.getPaid());

    jsonGen.writeEndObject();
  }
}
