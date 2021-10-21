package salarychecker.core;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculationTest {
    
    private Calculation calculation;
    private User user;

    @BeforeEach
    void setUp() {
        user = new User("firstname", "lastname", "email@gmail.com", "Password!123", 
            "22030199234", 55555, "employerEmail@gmail.com", 23.2, 130);
        calculation = new Calculation(user);
    }

    @Test
    private void testUpdateMethods() {

    }

    @Test
    private void testRemoveUnwanted() {

    }

    @Test
    private void testDoCalculation() {
        String url = new File(getClass().getResource("SalesReport.csv").getFile()).getAbsolutePath();
        
        assertDoesNotThrow(() -> calculation.doCalculation(url, 50, 5));
        assertTrue(calculation.getSaleslist().size() > 0);
        

    }

    @Test
    private void testUpdateList() {

    }
}
