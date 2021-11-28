package salarychecker.core;


import java.util.regex.Pattern;

/**
 * Class to validate different object variables of users.
 */
public class UserValidation {

  private static final Pattern FIRSTNAME_REGEX = Pattern.compile("^([a-åA-Å]{2,})$");
  private static final Pattern LASTNAME_REGEX = Pattern.compile("^([a-åA-Å]{2,})$");
  private static final Pattern PASSWORD_REGEX = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])"
      + "(?=.*[a-zA-Z]).{8,}$");
  private static final Pattern SOCIAL_NUMBER_REGEX = Pattern.compile("^(0[1-9]|[1-2][0-9]|31"
      + "(?!(?:0[2469]|11))|30(?!02))(0[1-9]|1[0-2])\\d{7}$");
  private static final Pattern EMPLOYEE_NUMBER_REGEX = Pattern.compile("^([0-9]{5})$");
  private static final Pattern EMAIL_REGEX = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+"
      + "(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\""
      + "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\"
      + "[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)"
      + "+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}"
      + "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:"
      + "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\"
      + "[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
  private static final Pattern TAX_COUNT_REGEX = Pattern.compile("(^100(\\.0{1,2})?$)|"
      + "(^([1-9]([0-9])?|0)(\\.[0-9]{1,2})?$)");

  /**
   * Checks if string is valid according to REGEX pattern.
   *
   * @param regex to match with
   * @param string to match
   * @return boolean
   */
  private static boolean isValidRegex(Pattern regex, String string) {
    return regex.matcher(string).matches();
  }

  /**
   * Validates first name.
   *
   * @param firstname to check
   * @throws IllegalArgumentException if field is empty, or firstname does not match Regex pattern
   */
  public static void checkValidFirstname(String firstname) {
    if (firstname.equals("")) {
      throw new IllegalArgumentException(Errors.NAME_FIELD_EMPTY.getMessage());
    }
    if (!isValidRegex(FIRSTNAME_REGEX, firstname)) {
      throw new IllegalArgumentException(Errors.INVALID_NAME.getMessage());
    }
  }

  /**
   * Validates lastname.
   *
   * @param lastname to check
   * @throws IllegalArgumentException if field is empty, or lastname does not match Regex pattern
   */
  public static void checkValidLastname(String lastname) {
    if (lastname.equals("")) {
      throw new IllegalArgumentException(Errors.NAME_FIELD_EMPTY.getMessage());
    }
    if (!isValidRegex(LASTNAME_REGEX, lastname)) {
      throw new IllegalArgumentException(Errors.INVALID_NAME.getMessage());
    }
  }

  /**
   * Validates password.
   *
   * @param password to check
   * @throws IllegalArgumentException if field is empty, or password does not match Regex pattern
   */
  public static void checkValidPassword(String password) {
    if (password.equals("")) {
      throw new IllegalArgumentException(Errors.PWD_FIELD_EMPTY.getMessage());
    }
    if (!isValidRegex(PASSWORD_REGEX, password)) {
      throw new IllegalArgumentException(Errors.INVALID_PWD.getMessage());
    }
  }

  /**
   * Validates socialNumber.
   *
   * @param socialNumber to check
   * @throws IllegalArgumentException if field is empty, or socialNumber does not
   *                                  match Regex pattern.
   */
  public static void checkValidSocialNumber(String socialNumber) {
    if (socialNumber.equals("")) {
      throw new IllegalArgumentException(Errors.SOCIAL_NUMBER_EMPTY.getMessage());
    }
    if (!isValidRegex(SOCIAL_NUMBER_REGEX, socialNumber)) {
      throw new IllegalArgumentException(Errors.INVALID_SOCIAL_NUMBER.getMessage());
    }
  }

  /**
   * Validates employeeNumber.
   *
   * @param employeeNumber to check
   * @throws IllegalArgumentException if field is empty, or employeeNumber
   *                                  does not match Regex pattern
   */
  public static void checkValidEmployeeNumber(int employeeNumber) {
    if (employeeNumber == 0) {
      throw new IllegalArgumentException(Errors.EMPLOYEE_NUMBER_EMPTY.getMessage());
    }
    if (!isValidRegex(EMPLOYEE_NUMBER_REGEX, String.valueOf(employeeNumber))) {
      throw new IllegalArgumentException(Errors.INVALID_EMPLOYEE_NUMBER.getMessage());
    }
  }

  /**
   * Validates email.
   *
   * @param email to check
   * @throws IllegalArgumentException if field is empty, or email does not match Regex pattern
   */
  public static void checkValidEmail(String email) {
    if (email.equals("")) {
      throw new IllegalArgumentException(Errors.EMAIL_FIELD_EMPTY.getMessage());
    }
    if (!isValidRegex(EMAIL_REGEX, email)) {
      throw new IllegalArgumentException(Errors.INVALID_EMAIL.getMessage());
    }
  }

  /**
   * Validates taxCount.
   *
   * @param taxCount to check
   * @throws IllegalArgumentException if field is empty, or taxCount does not match Regex pattern
   */
  public static void checkValidTaxCount(double taxCount) throws IllegalArgumentException {
    if (taxCount == 0.0) {
      throw new IllegalArgumentException(Errors.TAX_COUNT_EMPTY.getMessage());
    }
    if (!isValidRegex(TAX_COUNT_REGEX, String.valueOf(taxCount))) {
      throw new IllegalArgumentException(Errors.INVALID_TAX_COUNT.getMessage());
    }
  }

  /**
   * Validates hourRate.
   *
   * @param hourRate to check
   * @throws IllegalArgumentException if field is empty, or hourRate does not match Regex pattern
   */
  public static void checkValidHourRate(double hourRate) throws IllegalArgumentException {
    if (hourRate == 0.0) {
      throw new IllegalArgumentException(Errors.HOUR_RATE_EMPTY.getMessage());
    }
  }

  /**
   * Checks if user exists.
   *
   * @param email to check
   * @param password to check
   * @param accounts check if user exists in this accounts
   * @throws IllegalArgumentException if the user is not in accounts
   */
  public static void isNotExistingUser(String email, String password, Accounts accounts) {
    if (accounts.getUser(email, password) == null) {
      throw new IllegalArgumentException(Errors.NOT_REGISTERED.getMessage());
    }
  }

  /**
   * Checks if an attempt to create new user is valid.
   * Inspects all parameters and checks if they match teir individual regex-pattern.
   *
   *  @param firstname users firstname
   *  @param lastname users lastname
   *  @param email users email
   *  @param password users password
   *  @param socialNumber users socialNumber
   *  @param employeeNumber users employeeNumber
   *  @param employerEmail users employers email
   *  @param taxCount users tax count
   *  @param hourRate users hour rate
   */
  public static void checkValidUser(String firstname, String lastname, String email, String password,
                             String socialNumber, int employeeNumber, String employerEmail,
                             double taxCount, double hourRate) {
    allFieldsEmpty(firstname, lastname, email, password, socialNumber,
        employeeNumber, taxCount, hourRate);
    checkValidFirstname(firstname);
    checkValidLastname(lastname);
    checkValidEmail(email);
    checkValidPassword(password);
    checkValidSocialNumber(socialNumber);
    checkValidEmployeeNumber(employeeNumber);
    checkValidEmail(employerEmail);
    checkValidTaxCount(taxCount);
    checkValidHourRate(hourRate);
  }

  /**
   * Checks if all fields are empty.
   *
   *  @param firstname users firstname
   *  @param lastname users lastname
   *  @param email users email
   *  @param password users password
   *  @param socialNumber users socialNumber
   *  @param employeeNumber users employeeNumber
   *  @param taxCount users tax count
   *  @param hourRate users hour rate
   *  @throws IllegalArgumentException if all fields are empty
   */
  public static void allFieldsEmpty(String firstname, String lastname, String email, String password,
                             String socialNumber, int employeeNumber, double taxCount,
                             double hourRate) {
    if (firstname.equals("") && lastname.equals("") && email.equals("")
        && password.equals("") && socialNumber.equals("") && employeeNumber == 0 && taxCount == 0.0
        && hourRate == 0.0) {
      throw new IllegalArgumentException(Errors.EVERYTHING_EMPTY.getMessage());
    }
  }

  /**
   * Checks if user exists.
   *
   * @param email to check
   * @param password to check
   * @param accounts check if user exists in this accounts
   * @throws IllegalArgumentException if the user is not in accounts
   */
  public static void isValidLogIn(String email, String password, Accounts accounts) {
    if (!accounts.checkValidUserLogin(email, password)) {
      throw new IllegalArgumentException(Errors.INVALID_EMAIL_AND_OR_PWD.getMessage());
    }
  }

  /**
   * Checks if passwords are equal before changing in settings.
   *
   * @param password1 password 1
   * @param password2 password 2
   * @throws IllegalArgumentException if passwords is not equal
   */
  public static void isEqualPassword(String password1, String password2) {
    if (!(password1.equals(password2))) {
      throw new IllegalArgumentException(Errors.NOT_EQUAL_PASSWORD.getMessage());
    }
  }

  /**
   * Checks if E-mails are equal before changing in settings.
   *
   * @param email1 email1
   * @param email2 email2
   * @throws IllegalArgumentException if emails is not equal
   */
  public static void isEqualEmail(String email1, String email2) {
    if (!(email1.equals(email2))) {
      throw new IllegalArgumentException(Errors.NOT_EQUAL_EMAIL.getMessage());
    }
  }
}