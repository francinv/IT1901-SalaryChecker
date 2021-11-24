package salarychecker.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SaleTest {

    private Sale testSale;

    @BeforeEach
    public void setUp(){
        testSale = new Sale("17. Mai, 2021" , "anleggStatusString", "salgsTypeString", "campaignString", "brandString", "TX3String", "rebateString", "NVKString", "productString", 50.0);
    }

    @Test
    public void getMethodTest(){
        Assertions.assertEquals("17. Mai, 2021", testSale.getSalesDate());
        Assertions.assertEquals("anleggStatusString", testSale.getAnleggStatus());
        Assertions.assertEquals("salgsTypeString", testSale.getSalgsType());
        Assertions.assertEquals("campaignString", testSale.getCampaign());
        Assertions.assertEquals("brandString", testSale.getBrand());
        Assertions.assertEquals("TX3String", testSale.getTX3());
        Assertions.assertEquals("rebateString", testSale.getRebate());
        Assertions.assertEquals("NVKString", testSale.getNVK());
        Assertions.assertEquals("productString", testSale.getProduct());
        Assertions.assertEquals(50.0, testSale.getProvisjon());
    }

    @Test
    public void setMethodTest(){
        testSale.setSalesID("21. September 2021");
        Assertions.assertEquals("21. September 2021", testSale.getSalesDate());

        testSale.setAnleggStatus("anleggStatusString2");
        Assertions.assertEquals("anleggStatusString2", testSale.getAnleggStatus());

        testSale.setSalgsType("salgsTypeString2");
        Assertions.assertEquals("salgsTypeString2", testSale.getSalgsType());

        testSale.setCampaign("campaignString2");
        Assertions.assertEquals("campaignString2", testSale.getCampaign());

        testSale.setBrand("brandString2");
        Assertions.assertEquals("brandString2", testSale.getBrand());

        testSale.setTX3("TX3String2");
        Assertions.assertEquals("TX3String2", testSale.getTX3());

        testSale.setRebate("rebateString2");
        Assertions.assertEquals("rebateString2", testSale.getRebate());

        testSale.setNVK("NVKString2");;
        Assertions.assertEquals("NVKString2", testSale.getNVK());

        testSale.setProduct("productString2");
        Assertions.assertEquals("productString2", testSale.getProduct());

        testSale.setProvisjon(60.0);
        Assertions.assertEquals(60.0, testSale.getProvisjon());
    }
    
}
