package salarychecker.ui;

import org.junit.jupiter.api.BeforeAll;

import org.testfx.framework.junit5.ApplicationTest;

public class SalaryCheckerAppIT extends ApplicationTest {
  
    @BeforeAll
    public static void setupHeadless() {
        SalaryCheckerApp.supportHeadless();
    }


}