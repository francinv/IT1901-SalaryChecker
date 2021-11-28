package salarychecker.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserSaleTest {
    private UserSale testUserSale;

    @BeforeEach
    public void setUp(){
        testUserSale = new UserSale("August 2021", 3500.0, 3000.0);
    }
    @Test
    public void constructorTest(){
        UserSale emptyConstructor = new UserSale();
        Assertions.assertNull(emptyConstructor.getSalesperiod());
    }
    @Test
    public void testGetMethods(){
        Assertions.assertEquals("August 2021", testUserSale.getSalesperiod());
        Assertions.assertEquals(3500.0, testUserSale.getExpected());
        Assertions.assertEquals(3000.0, testUserSale.getPaid());
        testUserSale.setDifference();
        Assertions.assertEquals(500.0, testUserSale.getDifference());
    }
    @Test
    public void testSetMethods(){
        testUserSale.setSalesperiod("September 2021");
        Assertions.assertEquals("September 2021", testUserSale.getSalesperiod());
        testUserSale.setExpected(4000.0);
        Assertions.assertEquals(4000.0, testUserSale.getExpected());
        testUserSale.setPaid(2500.0);
        Assertions.assertEquals(2500.0, testUserSale.getPaid());
    }
}
