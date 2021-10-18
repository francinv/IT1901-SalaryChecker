package salarychecker.core;


import java.util.regex.Pattern;

public class UserValidation {
    
    private final Pattern FIRSTNAME_REGEX = Pattern.compile("^([a-åA-Å]{2,})$");
    private final Pattern LASTNAME_REGEX = Pattern.compile("^([a-åA-Å]{2,})$");
    private final Pattern PASSWORD_REGEX = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$");
    private final Pattern SOCIAL_NUMBER_REGEX = Pattern.compile("^(0[1-9]|[1-2][0-9]|31(?!(?:0[2469]|11))|30(?!02))(0[1-9]|1[0-2])\\d{7}$");
    private final Pattern EMPLOYEE_NUMBER_REGEX = Pattern.compile("^([0-9]{5})$"); 
    private final Pattern EMAIL_REGEX = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
    private final Pattern TAX_COUNT_REGEX = Pattern.compile("(^100(\\.0{1,2})?$)|(^([1-9]([0-9])?|0)(\\.[0-9]{1,2})?$)");
    

    private boolean isValidRegex(Pattern regex, String string) {
        return regex.matcher(string).matches();
    }

    public void checkValidFirstname(String firstname) {
        if (!isValidRegex(FIRSTNAME_REGEX, firstname)) {
            throw new IllegalArgumentException(Errors.INVALID_NAME.getMessage());
        }
    }

    public void checkValidLastname(String lastname) {
        if (!isValidRegex(LASTNAME_REGEX, lastname)) {
            throw new IllegalArgumentException(Errors.INVALID_NAME.getMessage());
        }
    }

    public void checkValidPassword(String password) {
        if (!isValidRegex(PASSWORD_REGEX, password)) {
            throw new IllegalArgumentException(Errors.INVALID_PWD.getMessage());
        }
    }

    public void checkValidSocialNumber(String socialNumber) {
        if (!isValidRegex(SOCIAL_NUMBER_REGEX, socialNumber)) {
            throw new IllegalArgumentException(Errors.INVALID_SOCIAL_NUMBER.getMessage());
        }
    }

    public void checkValidEmployeeNumber(int employeeNumber) {
        if (!isValidRegex(EMPLOYEE_NUMBER_REGEX, String.valueOf(employeeNumber))) {
            throw new IllegalArgumentException(Errors.INVALID_EMPLOYEE_NUMBER.getMessage());
        }
    }

    public void checkValidEmail(String email) {
        if (!isValidRegex(EMAIL_REGEX, email)) {
            throw new IllegalArgumentException(Errors.INVALID_EMAIL.getMessage());
        }
    }
    
    public void checkValidTaxCount(double taxCount) throws IllegalArgumentException {
        if (!isValidRegex(TAX_COUNT_REGEX, String.valueOf(taxCount))) {
            throw new IllegalArgumentException(Errors.INVALID_TAX_COUNT.getMessage());
        }
    }

    public void isExistingUser(String email, String password, Accounts accounts) {
        if(accounts.getUser(email, password)==null){
            throw new IllegalArgumentException(Errors.NOT_REGISTERED.getMessage());
        }
    }

    public void checkValidUser(String firstname, String lastname, String email, String password,
        String socialNumber, int employeeNumber, String employerEmail, double taxCount) {
            
        checkValidFirstname(firstname);
        checkValidLastname(lastname);
        checkValidEmail(email);
        checkValidPassword(password);
        checkValidSocialNumber(socialNumber);
        checkValidEmployeeNumber(employeeNumber);
        checkValidEmail(employerEmail);
        checkValidTaxCount(taxCount);
    }

    public void isValidLogIn(String email, String password, Accounts accounts) {
        if(!accounts.checkValidUserLogin(email, password)) {
            throw new IllegalArgumentException(Errors.INVALID_EMAIL_AND_OR_PWD.getMessage());
        }
    }

    public void isEqualPassword(String password1, String password2){
        if(!(password1.equals(password2))){
            throw new IllegalArgumentException(Errors.NOT_EQUAL_PASSWORD.getMessage());
        }
    }

    public void isEqualEmail(String email1, String email2){
        if(!(email1.equals(email2))){
            throw new IllegalArgumentException(Errors.NOT_EQUAL_EMAIL.getMessage());
        }
    }
}