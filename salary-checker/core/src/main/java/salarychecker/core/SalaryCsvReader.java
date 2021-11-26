package salarychecker.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/** 
 * Class to read from CSV file.
 */
public class SalaryCsvReader {

  /** 
   * Translates the information from the CSV file to a list.
   *
   * @param url location of CSV file
   * @return a list with bean
   * @throws IOException Signals that an I/O exception of some sort has occurred.
   *                     This class is the general class of exceptions produced by
   *                     failed or interrupted I/O operations.
   */


  /**
   * Converts data from CSV file to a list of sales object.
   *
   * @param url file path
   * @return saleslist
   * @throws IOException when not found
   */

  public List<Sale> csvToSale(String url) throws IOException {
    
    List<List<String>> records = new ArrayList<>();
    List<Integer> indexOfRemove = new ArrayList<>();

    try (Scanner scanner = new Scanner(new File(url));) {
      while (scanner.hasNextLine()) {
        records.add(getRecordFromLine(scanner.nextLine()));
      }
    }
    for (List<String> e : records) {
      List<String> temp;
      if (e.size() == 17) {
        int index = records.indexOf(e);
        temp = Stream.concat(e.stream(), 
          records.get(index + 1).stream()).collect(Collectors.toList());
        temp = Stream.concat(temp.stream(), 
          records.get(index + 2).stream()).collect(Collectors.toList());
        temp = Stream.concat(temp.stream(), 
          records.get(index + 3).stream()).collect(Collectors.toList());
        records.set(index, temp);
        indexOfRemove.addAll(Arrays.asList(index + 1, index + 2, index + 3));
      }
    }
    for (int i = indexOfRemove.size(); i-- > 0; ) {
      if (records.get(indexOfRemove.get(i)).size() == 1 
          || records.get(indexOfRemove.get(i)).size() == 22) {
        int index = indexOfRemove.get(i);
        records.remove(index);
      }
    }
    records.remove(0);
    List<Sale> saleslist = new ArrayList<>();
    for (List<String> e : records) {
      Sale sale = new Sale();
      if (e.size() == 41) {
        sale.setSalesId(e.get(0));
        sale.setAnleggStatus(e.get(14));
        sale.setSalgsType(e.get(15));
        sale.setCampaign(e.get(28));
        sale.setBrand(e.get(30));
        sale.setTx3(e.get(31));
        sale.setRebate(e.get(37));
        sale.setNvk(e.get(38));
        sale.setProduct(e.get(40));
        saleslist.add(sale);
      } else {
        sale.setSalesId(e.get(0));
        sale.setAnleggStatus(e.get(14));
        sale.setSalgsType(e.get(15));
        sale.setCampaign(e.get(25));
        sale.setBrand(e.get(27));
        sale.setTx3(e.get(28));
        sale.setRebate(e.get(34));
        sale.setNvk(e.get(35));
        sale.setProduct(e.get(37));
        saleslist.add(sale);
      }
    }
    return saleslist;
  }

  private List<String> getRecordFromLine(String nextLine) {
    List<String> values = new ArrayList<String>();
    try (Scanner rowScanner = new Scanner(nextLine)) {
      rowScanner.useDelimiter(";");
      while (rowScanner.hasNext()) {
        values.add(rowScanner.next());
      }
    }
    return values;
  }
}