package salarychecker.core;

public class Sale {

    private String salgsID, anleggStatus, salgsType, campaign, brand, TX3, rebate, NVK, product;

    private int provisjon;

    public int getProvisjon() {
        return this.provisjon;
    }
    public void setProvisjon(int provisjon) {
        this.provisjon = provisjon;
    }

    public void updateProvisjon(int number) {
        this.provisjon += number;
    }
    public String getSalgsID() {
        return this.salgsID;
    }

    public void setSalgsID(String salgsID) {
        this.salgsID = salgsID;
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

    public String getTX3() {
        return this.TX3;
    }

    public void setTX3(String TX3) {
        this.TX3 = TX3;
    }

    public String getRebate() {
        return this.rebate;
    }

    public void setRebate(String rebate) {
        this.rebate = rebate;
    }

    public String getNVK() {
        return this.NVK;
    }

    public void setNVK(String NVK) {
        this.NVK = NVK;
    }

    public String getProduct() {
        return this.product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "{" +
            " Salgs-ID='" + getSalgsID() + "'" +
            ", Anlegg Status='" + getAnleggStatus() + "'" +
            ", Salgs Type='" + getSalgsType() + "'" +
            ", Kampanje='" + getCampaign() + "'" +
            ", Merke='" + getBrand() + "'" +
            ", Trippelgaranti='" + getTX3() + "'" +
            ", Rabatt='" + getRebate() + "'" +
            ", Norsk VannKraft='" + getNVK() + "'" +
            ", Produktnavn='" + getProduct() + "'" +
            ", Provisjon='" + getProvisjon() + "'" +
            "}";
    }
    
}
