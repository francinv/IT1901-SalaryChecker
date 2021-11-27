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
  private double provisjon;

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
   * @param provisjon commission
   */
  public Sale(String salesId, String anleggStatus, String salgsType, String campaign,
              String brand, String tx3, String rebate, String nvk, String product,
              Double provisjon) {

    this.salesId = salesId;
    this.anleggStatus = anleggStatus;
    this.salgsType = salgsType;
    this.campaign = campaign;
    this.brand = brand;
    this.tx3 = tx3;
    this.rebate = rebate;
    this.nvk = nvk;
    this.product = product;
    this.provisjon = provisjon;
  }

  /**
   * Empty constructor needed for test.
   */
  public Sale() {
  }

  /**
   * Set and get methods.
   */
  public double getProvisjon() {
    return this.provisjon;
  }

  public void setProvisjon(double provisjon) {
    this.provisjon = provisjon;
  }

  public void updateProvisjon(int number) {
    this.provisjon += number;
  }

  public String getSalesID() {
    return this.salesID;
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

}
