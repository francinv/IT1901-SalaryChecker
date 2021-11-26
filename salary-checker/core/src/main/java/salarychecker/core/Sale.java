package salarychecker.core;

/**
 * Util class for a sale.
 */
public class Sale {

  private String salesId;
  private String anleggStatus;
  private String salgsType;
  private String campaign;
  private String brand;
  private String tx3;
  private String rebate;
  private String nvk;
  private String product;
  private double commission;

  /**
   * Creates Sale with all relevant parameters.
   *
   * @param salesId date of sale
   * @param anleggStatus status
   * @param salgsType type of sale
   * @param campaign campaign
   * @param brand brand
   * @param tx3 tx3
   * @param rebate discount on sale
   * @param nvk nvk
   * @param product product of sale
   * @param commission commission
   */
  public Sale(String salesId, String anleggStatus, String salgsType, String campaign,
              String brand, String tx3, String rebate, String nvk, String product,
              double commission) {

    this.salesId = salesId;
    this.anleggStatus = anleggStatus;
    this.salgsType = salgsType;
    this.campaign = campaign;
    this.brand = brand;
    this.tx3 = tx3;
    this.rebate = rebate;
    this.nvk = nvk;
    this.product = product;
    this.commission = commission;
  }

  /**
   * Empty constructor needed for test.
   */
  public Sale() {
  }

  /**
   * Set and get methods.
   */
  public double getCommission() {
    return this.commission;
  }

  public void setCommission(double commission) {
    this.commission = commission;
  }

  public void updateCommission(int number) {
    this.commission += number;
  }

  public String getSalesDate() {
    return this.salesId;
  }

  public void setSalesId(String salesId) {
    this.salesId = salesId;
  }

  public String getAnleggStatus() {
    return this.anleggStatus;
  }

  public void setAnleggStatus(String anleggStatus) {
    this.anleggStatus = anleggStatus;
  }

  public String getSalgsType() {
    return this.salgsType;
  }

  public void setSalgsType(String salgsType) {
    this.salgsType = salgsType;
  }

  public String getCampaign() {
    return this.campaign;
  }

  public void setCampaign(String campaign) {
    this.campaign = campaign;
  }

  public String getBrand() {
    return this.brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getTx3() {
    return this.tx3;
  }

  public void setTx3(String tx3) {
    this.tx3 = tx3;
  }

  public String getRebate() {
    return this.rebate;
  }

  public void setRebate(String rebate) {
    this.rebate = rebate;
  }

  public String getNvk() {
    return this.nvk;
  }

  public void setNvk(String nvk) {
    this.nvk = nvk;
  }

  public String getProduct() {
    return this.product;
  }

  public void setProduct(String product) {
    this.product = product;
  }

  @Override
  public String toString() {
    return "{"
      + " Salgs-ID='" + getSalesDate() + "'"
      + ", Anlegg Status='" + getAnleggStatus() + "'"
      + ", Salgs Type='" + getSalgsType() + "'"
      + ", Kampanje='" + getCampaign() + "'"
      + ", Merke='" + getBrand() + "'"
      + ", Trippelgaranti='" + getTx3() + "'"
      + ", Rabatt='" + getRebate() + "'"
      + ", Norsk VannKraft='" + getNvk() + "'"
      + ", Produktnavn='" + getProduct() + "'"
      + ", Provisjon='" + getCommission() + "'"
      + "}";
  }
}
