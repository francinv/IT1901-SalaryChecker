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
    

    private boolean checkValidRegex(Pattern regex, String string) {
        return regex.matcher(string).matches();
    }

    public boolean isValidFirstname(String firstname) {
        return checkValidRegex(FIRSTNAME_REGEX, firstname);
    }

    public boolean isValidLastname(String lastname) {
        return checkValidRegex(LASTNAME_REGEX, lastname);
    }

    public boolean isValidPassword(String password) {
        return checkValidRegex(PASSWORD_REGEX, password);
    }

    public boolean isValidSocialNumber(Long socialNumber) {
        return checkValidRegex(SOCIAL_NUMBER_REGEX, String.valueOf(socialNumber));
    }

    public boolean isValidEmployeeNumber(int employeeNumber) {
        return checkValidRegex(EMPLOYEE_NUMBER_REGEX, String.valueOf(employeeNumber));
    }

    public boolean isValidEmail(String email) {
        return checkValidRegex(EMAIL_REGEX, email);
    }
    
    public boolean isValidTaxCount(double taxCount) {
        return checkValidRegex(TAX_COUNT_REGEX, String.valueOf(taxCount));
    }

    public boolean isValidUser(String firstname, String lastname, String email, String password,
        long socialNumber, int employeeNumber, String employerEmail, double taxCount) {
            return isValidFirstname(firstname) && isValidLastname(lastname) &&
                   isValidEmail(email) && isValidPassword(password) && isValidSocialNumber(socialNumber) &&
                   isValidEmployeeNumber(employeeNumber) && isValidEmail(employerEmail) && 
                   isValidTaxCount(taxCount); 
    }

    public boolean isExistingUser(String email, String password) {
        return false;
    }
}
