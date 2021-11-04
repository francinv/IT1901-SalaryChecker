package salarychecker.core;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class for calcualting a users salary based on SalesReport.
 */
public class Calculation {

  private List<Sale> saleslist = new ArrayList<>();

  private static final List<String> NYSALG = Arrays.asList("Nysalg FK", "Borettslag", "Nysalg TK");
  private static final List<String> WINBACK = Arrays.asList("WB FK", "WB Lokal", "WB TK");
  private static final List<String> COMEBACK = Arrays.asList("FK lista", "Lokal lista");
  private static final List<String> LEADS = Arrays.asList("Bring Flytt", "Bring Prospect",
      "Diverese leads fjordkraft", "Flytt FK", "Posten", "Småbaser", "Strøm.com",
      "Strømleverandøren", "Diverese leads FK", "CPH leads", "Eiendomsregisteret TK",
      "Leads KS", "Småbaser", "Tjenestetorget.no", "Coop/Trumf");

  private static final List<String> ORG = Arrays.asList("Agrol Spotpris",
      "Bergen Huseierforening - Topp 5 Garanti", "BOB Garanti", "BOB Spotpris",
      "Bobilforeningen Spotpris", "Farmaceutene Spotpris", "Industri Energi - Garanti",
      "Industri Energi - Spotpris", "Innkjøpspris Borettslag", "Jordmorforeningen Spotpris",
      "KNIF Spotpris", "KNIF 50 Sikret", "LOfavør Garanti", "LOfavør Spotpris",
      "Norges Eiendomsmeglerforbund Spotpris", "Norsk Narkotikapolitiforening Spotpris",
      "Samfunnsviterene Spotpris Fornybar", "Vestbo Spotpris", "Visma Spotpris",
      "LO Favør Spotpris", "LO Favør Topp 3 Garanti", "Ranheim Fotball", "Studentsamskipnaden",
      "TOBB Garanti", "TOBB Spotpris", "Garantipris", "Coop / Trumf");

  private static final List<String> VAR = Arrays.asList("Forutsigbar", "Garantistrøm",
      "Garantistrøm Oktober-kampanje", "Garantistrøm September-kampanje",
      "Garantistrøm Kampanje Høst 2021", "Garantistrøm Kampanje Vår 2021", "Garantistrøm Standard",
      "Garantistrøm Nord", "Garantipris", "Garantistrøm Kampanje Høst");

  private static final List<String> NSPOT = Arrays.asList("Strøm til Spotpris",
      "Strøm til Spotpris Kampanje", "Solstrøm", "Trønderspot", "Trønderspot Kampanje",
      "Elbil-avtalen");

  private static final List<String> RSPOT = Arrays.asList("Trønderspot Spesial",
      "Strøm til Spotpris Spesial");

  private static final List<String> BUN = Arrays.asList("EuroBonus-avtalen", "PowerSpot");

  private double calculated;
  private User user;
  private static final SalaryCSVReader SALARY_CSV_READER = new SalaryCSVReader(); 

  public Calculation(User user) {
    this.user = user;
  }

  public Calculation() {
    
  }

  /**
   * Access method for saleslist.
   *
   * @return copy of saleslist
   */
  public List<Sale> getSaleslist() {
    return new ArrayList<>(saleslist);
  }

  /**
   * Upadtes the list according to salesreport.
   *
   * @param url location of salesreport
   * @throws IOException Signals that an I/O exception of some sort has occurred.
   *                     This class is the general class of exceptions produced by
   *                     failed or interrupted I/O operations.
   */
  public void updateList(String url) throws IOException {
    saleslist = SALARY_CSV_READER.csvToBean(url);
  }

  /**
   * Removes all the unwanted sales from saleslist.
   */
  public void removeUnwanted() {
    saleslist = saleslist.stream()
      .filter(s -> s.getAnleggStatus().equals("23-Etablert"))
      .collect(Collectors.toList());
  }

  /**
   * Updates electricity comissions.
   */
  public void updateElectricityCommission() {
    for (Sale s : saleslist) {

      if (s.getTX3().equals("Ja") && s.getNVK().equals("Nei")) {
        s.setProvisjon(75);
      }
      if (s.getNVK().equals("Ja") && s.getTX3().equals("Nei")) {
        s.setProvisjon(50);
      }
      if (s.getTX3().equals("Ja") && s.getNVK().equals("Ja")) {
        s.setProvisjon(125);
      }

      if (s.getSalgsType().equals("Produktbytte") && WINBACK.contains(s.getCampaign())
          || s.getSalgsType().equals("Produktbytte") && LEADS.contains(s.getCampaign())) {
        s.updateProvisjon(50);
      }

      if (BUN.contains(s.getProduct()) && WINBACK.contains(s.getCampaign())
          || BUN.contains(s.getProduct()) && LEADS.contains(s.getCampaign())) {
        s.updateProvisjon(50);
      }

      if (NYSALG.contains(s.getCampaign())) {

        if (ORG.contains(s.getProduct()) || VAR.contains(s.getProduct())) {
          s.updateProvisjon(225);
          if (s.getRebate().equals("500")) {
            s.updateProvisjon(-25);
          }
          if (s.getRebate().equals("750") || s.getRebate().equals("1000")) {
            s.updateProvisjon(-50);
          }
        }
        if (NSPOT.contains(s.getProduct())) {
          s.updateProvisjon(200);
          if (s.getRebate().equals("300") || s.getRebate().equals("500")
              || s.getRebate().equals("750") || s.getRebate().equals("1000")) {
            s.updateProvisjon(-25);
          }
        }
        if (RSPOT.contains(s.getProduct())) {
          s.updateProvisjon(175);
          if (s.getRebate().equals("500")) {
            s.updateProvisjon(-25);
          }
        }

      }

      if (WINBACK.contains(s.getCampaign())) {
        if (ORG.contains(s.getProduct()) || VAR.contains(s.getProduct())) {
          s.updateProvisjon(110);
          if (s.getRebate().equals("500")) {
            s.updateProvisjon(-30);
          }
          if (s.getRebate().equals("750") || s.getRebate().equals("1000")) {
            s.updateProvisjon(-60);
          }
        }
        if (NSPOT.contains(s.getProduct())) {
          s.updateProvisjon(80);
          if (s.getRebate().equals("300") || s.getRebate().equals("500")
              || s.getRebate().equals("750") || s.getRebate().equals("1000")) {
            s.updateProvisjon(-30);
          }
        }
        if (RSPOT.contains(s.getProduct())) {
          s.updateProvisjon(50);
          if (s.getRebate().equals("500")) {
            s.updateProvisjon(-25);
          }
        }

      }

      if (COMEBACK.contains(s.getCampaign())) {

        if (ORG.contains(s.getProduct()) || VAR.contains(s.getProduct())) {
          s.updateProvisjon(175);
          if (s.getRebate().equals("500")) {
            s.updateProvisjon(-25);
          }
          if (s.getRebate().equals("750") || s.getRebate().equals("1000")) {
            s.updateProvisjon(-50);
          }
        }
        if (NSPOT.contains(s.getProduct())) {
          s.updateProvisjon(150);
          if (s.getRebate().equals("300") || s.getRebate().equals("500")
              || s.getRebate().equals("750") || s.getRebate().equals("1000")) {
            s.updateProvisjon(-25);
          }
        }
        if (RSPOT.contains(s.getProduct())) {
          s.updateProvisjon(125);
          if (s.getRebate().equals("500")) {
            s.updateProvisjon(-25);
          }
        }
      }

      if (LEADS.contains(s.getCampaign())) {
        if (ORG.contains(s.getProduct()) || VAR.contains(s.getProduct())) {
          s.updateProvisjon(150);
          if (s.getRebate().equals("500")) {
            s.updateProvisjon(-35);
          }
          if (s.getRebate().equals("750") || s.getRebate().equals("1000")) {
            s.updateProvisjon(-70);
          }
        }
        if (NSPOT.contains(s.getProduct())) {
          s.updateProvisjon(115);
          if (s.getRebate().equals("300") || s.getRebate().equals("500")
              || s.getRebate().equals("750") || s.getRebate().equals("1000")) {
            s.updateProvisjon(-15);
          }
        }
        if (RSPOT.contains(s.getProduct())) {
          s.updateProvisjon(100);
          if (s.getRebate().equals("500")) {
            s.updateProvisjon(-50);
          }
        }
      }
    }
  }

  /**
   * Calculates electricity comission.
   */
  public void calculateElectricityCommission() {
    for (Sale s : saleslist) {
      calculated += s.getProvisjon();
    }
  }

  /**
   * Method to add mobile.
   *
   * @param amount ampount to add
   */
  public void addMobile(int amount) {
    int per = 200;
    int mobcommission = per * amount;
    calculated += mobcommission;
  }

  /**
   * Calculates just the hour salary.
   *
   * @param hours number of worked hours
   */
  public void hourSalary(double hours) {
    double hoursal = user.getHourRate() * hours;
    calculated += hoursal;
  }

  public void hourSalary(double hours, double hourwage) {
    double hoursal = hourwage * hours;
    calculated += hoursal;
  }

  /**
   * Access method for calculated.
   *
   * @return the calculated
   */
  public double getCalculated() {
    return calculated;
  }

  /**
   * Removes tax from calculated.
   */
  public void taxDeduction() {
    calculated = (calculated * ((100 - user.getTaxCount()) / 100));
  }

  public void taxDeduction(double taxCount){
    calculated = (calculated * ((100 - taxCount) / 100));
  }

  /**
   * Do the full calculation.
   *
   * @param url to the file
   * @param hours total hours of working
   * @param mobileamount amount of mobile
   * @throws FileNotFoundException Signals that an attempt to open the file
   *                               denoted by a specified pathname has failed.
   */
  public UserSale doCalculation(String url, double hours, int mobileamount, String salesperiod, double paid) throws IOException {
    updateList(url);
    removeUnwanted();
    updateElectricityCommission();
    calculateElectricityCommission();
    addMobile(mobileamount);
    hourSalary(hours);
    taxDeduction();
    double expectedCalc = Math.round(getCalculated() * 10 )/ 10.0;
    UserSale userSale = new UserSale(salesperiod, expectedCalc, paid);
    return userSale;
  }

  public UserSale doCalculation(String url, double hours, double hourwage, double taxcount, int mobileamount, String salesperiod, double paid) throws IOException {
    updateList(url);
    removeUnwanted();
    updateElectricityCommission();
    calculateElectricityCommission();
    addMobile(mobileamount);
    hourSalary(hours, hourwage);
    taxDeduction(taxcount);
    double expectedCalc = Math.round(getCalculated() * 10) / 10.0;
    UserSale userSale = new UserSale(salesperiod, expectedCalc, paid);
    return userSale;
  }

  
}
