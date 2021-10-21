package salarychecker.core;

public class UserSale {

    String salesperiod;
    Double expected;
    Double paid;
    Double difference;

    public UserSale(String salesperiod, double expected, double paid) {
        this.salesperiod = salesperiod;
        this.expected = expected;
        this.paid = paid;
    }

    public UserSale() {
    }


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
        return "{" +
            " salesperiod='" + getSalesperiod() + "'" +
            ", expected='" + getExpected() + "'" +
            ", paid='" + getPaid() + "'" +
            ", difference='" + getDifference() + "'" +
            "}";
    }
}