package salarychecker.core;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculationTest {
    
    private Calculation calculation;
    private User user;
    private final Path path = Path.of(getClass().getResource("SalesReport.csv").getFile());

    @BeforeEach
    void setUp() {
        user = new User("firstname", "lastname", "email@gmail.com", "Password!123", 
            "22030199234", 55555, "employerEmail@gmail.com", 23.2, 130);
        calculation = new Calculation();
    }

    @Test
    public void testConstructors() {
        assertNull(calculation.getSalesperiod());
        Calculation calculation = new Calculation("Januar 2021", 100.0, 5, 10000.0);
        assertEquals("Januar 2021", calculation.getSalesperiod());
        assertEquals(100.0, calculation.getHours());
        assertEquals(5, calculation.getMobileamount());
        assertEquals(10000.0, calculation.getPaid());
    }

    @Test
    public void testGettersAndSetters() {
        calculation.setSalesperiod("Januar 2021");
        assertEquals("Januar 2021", calculation.getSalesperiod());
        calculation.setHours(100.0);
        assertEquals(100.0, calculation.getHours());
        calculation.setMobileamount(5);
        assertEquals(5, calculation.getMobileamount());
        calculation.setPaid(10000.0);
        assertEquals(10000.0, calculation.getPaid());
    }

    /*@Test
    public void testMethods() throws IOException {
        calculation.updateList(path);
        assertEquals(72, calculation.getSaleslist().size());
        calculation.removeUnwanted();
        assertEquals(57, calculation.getSaleslist().size());
        for (Sale s : calculation.getSaleslist()) {
            assertEquals("23-Etablert", s.getAnleggStatus());
        }
        calculation.updateElectricityCommission();
        assertEquals(57, calculation.getSaleslist().size());
        assertEquals(80, calculation.getSaleslist().get(14).getCommission());
        assertEquals(150, calculation.getSaleslist().get(27).getCommission());
        calculation.calculateElectricityCommission();
        assertEquals(7670, calculation.getCalculated());
        calculation.addMobile(5);
        assertEquals(8670, calculation.getCalculated());
        calculation.hourSalary(100.0, user);
        assertEquals(21670, calculation.getCalculated());
        calculation.taxDeduction(user);
        assertEquals(16642.6, calculation.getCalculated());
    }*/


    /*@Test
    public void testDoCalculation() throws IOException {
        Calculation fullCalc = new Calculation("Januar 2021", 100.0, 5, 10000.0);
        fullCalc.doCalculation(path, user);
        List<UserSale> tempSales = new ArrayList<>();
        UserSale userSale = new UserSale("Januar 2021", 16642.6, 10000.0);
        tempSales.add(userSale);
        assertEquals(tempSales.get(0).getSalesperiod(), user.getUserSaleList().get(0).getSalesperiod());
        assertEquals(tempSales.get(0).getDifference(), user.getUserSaleList().get(0).getDifference());
        assertEquals(tempSales.get(0).getExpected(), user.getUserSaleList().get(0).getExpected());
        assertEquals(tempSales.get(0).getPaid(), user.getUserSaleList().get(0).getPaid());
        assertEquals(userSale.getExpected(), user.getUserSale("Januar 2021").getExpected());
    }*/

}
