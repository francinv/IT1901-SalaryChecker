package salarychecker.core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;

public class SalaryCSVReader {

    public List<Sale> csvToBean(String url) throws FileNotFoundException {
        Map<String, String> mapping = new 
                      HashMap<String, String>();
        mapping.put("SalgsID", "salgsID");
        mapping.put("AnleggsStatus", "anleggStatus");
        mapping.put("Salgstype", "salgsType");
        mapping.put("Kampanje", "Campaign");
        mapping.put("Merkevare", "Brand");
        mapping.put("Trippelgaranti", "TX3");
        mapping.put("BRUDDGEBYR(KR)", "rebate");
        mapping.put("Norsk Vannkraft", "NVK");
        mapping.put("ProductHubNavn", "product");

        HeaderColumnNameTranslateMappingStrategy<Sale> strategy = new HeaderColumnNameTranslateMappingStrategy<Sale>();
        strategy.setType(Sale.class);
        strategy.setColumnMapping(mapping);

        Reader reader = new BufferedReader(new FileReader(url));

        CsvToBean<Sale> csvReader = new CsvToBeanBuilder(reader)
                .withType(Sale.class)
                .withSeparator(';')
                .withIgnoreLeadingWhiteSpace(true)
                .withIgnoreEmptyLine(true)
                .withMappingStrategy(strategy)
                .build();

        List<Sale> results = csvReader.parse();

        return results;
    }

    public static void main(String[] args) throws FileNotFoundException {
        SalaryCSVReader salaryCSVReader = new SalaryCSVReader();
        String url = "salary-checker/core/src/main/resources/salarychecker/core/SalesReport.csv";
        salaryCSVReader.csvToBean(url);
    }
}
