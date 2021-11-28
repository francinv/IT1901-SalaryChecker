package salarychecker.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class SalaryCsvReaderTest {
    private List<Sale> someRead = new ArrayList<Sale>();

    private SalaryCsvReader testReader;

    @BeforeEach
    public void setUp() throws IOException {
        testReader = new SalaryCsvReader();
    }

    @Test
    public void testCSVReader() throws IOException {
        FileInputStream correctFilePath = new FileInputStream(new File(getClass().getResource("SalesReport.csv").getFile()));
        someRead = testReader.csvToSale(correctFilePath);
        assertEquals(72, someRead.size());
        assertThrows(FileNotFoundException.class, () -> new FileInputStream(new File("/Users/francinvincent/dev/gr2111/salary-checker/core/src/test/resources/salarychecker/core/Test.csv")));
        FileInputStream emptyFilePath = new FileInputStream(new File(getClass().getResource("Empty.csv").getFile()));
        someRead = testReader.csvToSale(emptyFilePath);
        assertEquals(1, someRead.size());
        assertNotEquals(72, someRead.size());

    }
}
