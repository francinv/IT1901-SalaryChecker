package salarychecker.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.CsvToBean;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class SalaryCSVReaderTest {
    private List<Sale> someRead = new ArrayList<Sale>();

    private SalaryCSVReader testReader;

    @BeforeEach
    public void setUp(){
        testReader = new SalaryCSVReader();
        try {
            someRead = testReader.csvToBean(new File(getClass().getResource("SalesReport.csv").getFile()).getAbsolutePath());
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
    }

    @Test
    public void testCsvToBean(){
        Assertions.assertNotNull(someRead);
    }
}
