package salarychecker.core;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

/*
 * Class to read from CSV file.
*/
public class SalaryCSVReader {

  /*
   * Translates the information from the CSV file to a list.
   *
   * @param url location of CSV file
   * @return a list with bean
   * @throws IOException Signals that an I/O exception of some sort has occurred.
   *                     This class is the general class of exceptions produced by
   *                     failed or interrupted I/O operations.
   */


  public List<Sale> CSVtoSale(String url) throws IOException {
    List<Sale> temp = new ArrayList<>();
    Reader reader = new FileReader(url);
    Iterable<CSVRecord> records = CSVFormat.EXCEL.withDelimiter(';').parse(reader);
    for (CSVRecord csvRecord : records) {
      Sale sale = new Sale();
      sale.setSalesID(csvRecord.get(0));
      sale.setAnleggStatus(csvRecord.get(14));
      sale.setSalgsType(csvRecord.get(15));
      sale.setCampaign(csvRecord.get(25));
      sale.setBrand(csvRecord.get(27));
      sale.setTX3(csvRecord.get(28));
      sale.setRebate(csvRecord.get(34));
      sale.setNVK(csvRecord.get(35));
      sale.setProduct(csvRecord.get(37));
      temp.add(sale);
    }
    temp.remove(0);
    return temp;
  }
}
