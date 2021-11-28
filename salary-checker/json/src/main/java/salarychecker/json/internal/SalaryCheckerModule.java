package salarychecker.json.internal;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import salarychecker.core.*;

/**
 * A Jackson module for configuring JSON serialization of User instances.
 */
public class SalaryCheckerModule extends SimpleModule {

  private static final String NAME = "UserModule";

  /**
   * Initializes this SalaryCheckerModule with appropriate serializers and deserializers.
   */
  public SalaryCheckerModule() {
    super(NAME, Version.unknownVersion());
    addSerializer(User.class, new UserSerializer());
    addSerializer(AdminUser.class, new AdminUserSerializer());
    addSerializer(UserSale.class, new UserSaleSerializer());
    addSerializer(Accounts.class, new AccountsSerializer());
    addSerializer(Calculation.class, new CalculationSerializer());
    addDeserializer(User.class, new UserDeserializer());
    addDeserializer(AdminUser.class, new AdminUserDeserializer());
    addDeserializer(UserSale.class, new UserSaleDeserializer());
    addDeserializer(Accounts.class, new AccountsDeserializer());
    addDeserializer(Calculation.class, new CalculationDeserializer());
  }
}
