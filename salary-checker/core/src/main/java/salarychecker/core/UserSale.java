package salarychecker.core;

/**
 * Class to specify a sale for a user. This class translates
 * the SalesReport to java class, with help of {@link SalaryCSVReader}
 * and {@link Sale}.
 */
public class UserSale {

  String salesperiod;
  Double expected;
  Double paid;
  Double difference;

  /**
   * Constructor for UserSale, a salesperiod, ie: October 2021.
   *
   * @param salesperiod the period
   * @param expected expected salary
   * @param paid actuall salary
   */
  public UserSale(String salesperiod, double expected, double paid) {
    this.salesperiod = salesperiod;
    this.expected = expected;
    this.paid = paid;
  }

  /**
   * Empty constructor for serializing UserSale.
   */
  public UserSale() {
  }

  /**
   * Get and set methods for all parameters in UserSale.
   * get methods @return parameter
   */
  public String getSalesperiod() {
    return this.salesperiod;
  }

  public void setSalesperiod(String salesperiod) {
    this.salesperiod = salesperiod;
  }

  public Double getExpected() {
    return this.expected;
  }

  public void setExpected(Double expected) {
    this.expected = expected;
  }

  public Double getPaid() {
    return this.paid;
  }

  public void setPaid(Double paid) {
    this.paid = paid;
  }

  public Double getDifference() {
    return this.difference;
  }

  public void setDifference() {
    this.difference = getExpected() - getPaid();
  }

  @Override
  public String toString() {
    return "{"
      + " salesperiod='" + getSalesperiod() + "'"
      + ", expected='" + getExpected() + "'"
      + ", paid='" + getPaid() + "'"
      + ", difference='" + getDifference() + "'"
      + "}";
  }
}