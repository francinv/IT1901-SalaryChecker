package salarychecker.core;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class to read from CSV file.
 */
public class SalaryCSVReader {

  /**
   * Translates the information from the CSV file to a list.
   *
   * @param url location of CSV file
   * @return a list with bean
   * @throws IOException Signals that an I/O exception of some sort has occurred.
   *                     This class is the general class of exceptions produced by
   *                     failed or interrupted I/O operations.
   */
  public List<Sale> csvToBean(String url) throws IOException {
    Map<String, String> mapping = new HashMap<>();
    mapping.put("SalgsID", "salgsID");
    mapping.put("AnleggsStatus", "anleggStatus");
    mapping.put("Salgstype", "salgsType");
    mapping.put("Kampanje", "Campaign");
    mapping.put("Merkevare", "Brand");
    mapping.put("Trippelgaranti", "TX3");
    mapping.put("BRUDDGEBYR(KR)", "rebate");
    mapping.put("Norsk Vannkraft", "NVK");
    mapping.put("ProductHubNavn", "product");

    HeaderColumnNameTranslateMappingStrategy<Sale> strategy =
        new HeaderColumnNameTranslateMappingStrategy<>();
    strategy.setType(Sale.class);
    strategy.setColumnMapping(mapping);

    Reader reader = new BufferedReader(new FileReader(url, StandardCharsets.UTF_8));

    CsvToBean<Sale> csvReader = new CsvToBeanBuilder<Sale>(reader).withType(Sale.class)
                                                                  .withSeparator(';')
                                                                  .withIgnoreLeadingWhiteSpace(true)
                                                                  .withIgnoreEmptyLine(true)
                                                                  .withMappingStrategy(strategy)
                                                                  .build();

    return csvReader.parse();
  }
}
