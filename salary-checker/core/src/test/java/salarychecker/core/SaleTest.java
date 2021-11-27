package salarychecker.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SaleTest {

    private Sale testSale;

    @BeforeEach
    public void setUp(){
        testSale = new Sale("17. Mai, 2021" , "anleggStatusString", "salgsTypeString", "campaignString", "brandString", "tx3String", "rebateString", "nvkString", "productString", 50.0);
    }

    @Test
    public void testConstructor() {
        Sale emptyConstructor = new Sale();
        Assertions.assertNull(emptyConstructor.getAnleggStatus());
    }

    @Test
    public void testConstructor() {
        Sale emptyConstructor = new Sale();
        Assertions.assertNull(emptyConstructor.getAnleggStatus());
    }

    @Test
    public void getMethodTest(){
        Assertions.assertEquals("17. Mai, 2021", testSale.getSalesID());
        Assertions.assertEquals("anleggStatusString", testSale.getAnleggStatus());
        Assertions.assertEquals("salgsTypeString", testSale.getSalgsType());
        Assertions.assertEquals("campaignString", testSale.getCampaign());
        Assertions.assertEquals("brandString", testSale.getBrand());
        Assertions.assertEquals("tx3String", testSale.getTx3());
        Assertions.assertEquals("rebateString", testSale.getRebate());
        Assertions.assertEquals("nvkString", testSale.getNvk());
        Assertions.assertEquals("productString", testSale.getProduct());
        Assertions.assertEquals(50.0, testSale.getCommission());
    }

    @Test
    public void setMethodTest(){
        testSale.setSalesID("21. September 2021");
        Assertions.assertEquals("21. September 2021", testSale.getSalesID());

        testSale.setAnleggStatus("anleggStatusString2");
        Assertions.assertEquals("anleggStatusString2", testSale.getAnleggStatus());

        testSale.setSalgsType("salgsTypeString2");
        Assertions.assertEquals("salgsTypeString2", testSale.getSalgsType());

        testSale.setCampaign("campaignString2");
        Assertions.assertEquals("campaignString2", testSale.getCampaign());

        testSale.setBrand("brandString2");
        Assertions.assertEquals("brandString2", testSale.getBrand());

        testSale.setTx3("tx3String2");
        Assertions.assertEquals("tx3String2", testSale.getTx3());

        testSale.setRebate("rebateString2");
        Assertions.assertEquals("rebateString2", testSale.getRebate());

        testSale.setNvk("nvkString2");;
        Assertions.assertEquals("nvkString2", testSale.getNvk());

        testSale.setProduct("productString2");
        Assertions.assertEquals("productString2", testSale.getProduct());

        testSale.setCommission(60.0);
        Assertions.assertEquals(60.0, testSale.getCommission());
    }

    @Test
    public void testUpdateProvisjon() {
        testSale.updateProvisjon(200);
        assertEquals(250, testSale.getProvisjon());
    }

    @Test
    public void testUpdateProvisjon() {
        testSale.updateProvisjon(200);
        assertEquals(250, testSale.getProvisjon());
    }
    
}
