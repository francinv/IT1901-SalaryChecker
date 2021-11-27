package salarychecker.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class for calcualting a users salary based on SalesReport. In the 
 * future this app will do the calualtion of more SalesReporsts.
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
  private static final SalaryCSVReader SALARY_CSV_READER = new SalaryCSVReader();
  private String salesperiod;
  private double hours;
  private int mobileamount;
  private double paid;

  /**
   * Constructor for initializing a calculation object.
   *
   * @param salesperiod sales period
   * @param hours working hours
   * @param mobileamount mobile amount
   * @param paid paid salary
   */

  public Calculation(String salesperiod, double hours, int mobileamount, double paid) {
    this.salesperiod = salesperiod;
    this.hours = hours;
    this.mobileamount = mobileamount;
    this.paid = paid;
  }

  public Calculation() {
  }

  /**
   * Getter for salesperiod. Eg: "Januar 2021".
   * 
   * @return salesperiod of this instance.
   */
  public String getSalesperiod() {
    return salesperiod;
  }

  /**
   * Setter for salesperiod. Eg: "Januar 2021".
   * 
   * @param salesperiod that we are going to set.
   */
  public void setSalesperiod(String salesperiod) {
    this.salesperiod = salesperiod;
  }


  /**
   * Get hours that the user has worked.
   * 
   * @return hours of work.
   */
  public double getHours() {
    return hours;
  }

  /**
   * Set the hours that the user has worked.
   * 
   * @param hours for this salesperiod.
   */
  public void setHours(double hours) {
    this.hours = hours;
  }

  /**
   * Get the amount of mobile-plan sales.
   * 
   * @return mobileamount for this instance.
   */
  public int getMobileamount() {
    return mobileamount;
  }

  /**
   * Set the amount of mobile-plan sales.
   * 
   * @param mobileamount for this instance.
   */
  public void setMobileamount(int mobileamount) {
    this.mobileamount = mobileamount;
  }

  /**
   * Get the amount that is paid by employeer.
   * 
   * @return amount that is paid.
   */
  public double getPaid() {
    return paid;
  }

  /**
   * Set the amount that is paid by employeer.
   * 
   * @param paid by employeer.
   */
  public void setPaid(double paid) {
    this.paid = paid;
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
  public void updateList(String pathToFile) throws IOException {
    FileInputStream pathToReadFile = new FileInputStream(new File(pathToFile));
    saleslist = SALARY_CSV_READER.CSVtoSale(pathToReadFile);
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

      if (s.getTx3().equals("Ja") && s.getNvk().equals("Nei")) {
        s.setCommission(75);
      }
      if (s.getNvk().equals("Ja") && s.getTx3().equals("Nei")) {
        s.setCommission(50);
      }
      if (s.getTx3().equals("Ja") && s.getNvk().equals("Ja")) {
        s.setCommission(125);
      }

      if (s.getSalgsType().equals("Produktbytte") && WINBACK.contains(s.getCampaign())
          || s.getSalgsType().equals("Produktbytte") && LEADS.contains(s.getCampaign())) {
        s.updateCommission(50);
      }

      checkIfBunWinBackLeadsContainsCampaign(s);
      checkIfNySalgContainsCampaign(s);
      checkIfComebackContainsCampaign(s);
    }
  }

  /**
   * Checks if comeback contains campagin, an do the needed 
   * update of commission.
   */
  public void checkIfComebackContainsCampaign(Sale s) {
    if (COMEBACK.contains(s.getCampaign())) {
      if (ORG.contains(s.getProduct()) || VAR.contains(s.getProduct())) {
        s.updateCommission(175);
        if (s.getRebate().equals("500")) {
          s.updateCommission(-25);
        }
        if (s.getRebate().equals("750") || s.getRebate().equals("1000")) {
          s.updateCommission(-50);
        }
      }
      if (NSPOT.contains(s.getProduct())) {
        s.updateCommission(150);
        if (s.getRebate().equals("300") || s.getRebate().equals("500")
            || s.getRebate().equals("750") || s.getRebate().equals("1000")) {
          s.updateCommission(-25);
        }
      }
      if (RSPOT.contains(s.getProduct())) {
        s.updateCommission(125);
        if (s.getRebate().equals("500")) {
          s.updateCommission(-25);
        }
      }
    }
  }

  /**
   * Checks if bun and winback lists or bun and leads list, 
   * contains campagin. If so, do the needed update of commission.
   */
  public void checkIfBunWinBackLeadsContainsCampaign(Sale s) {
    if (BUN.contains(s.getProduct()) && WINBACK.contains(s.getCampaign())
          || BUN.contains(s.getProduct()) && LEADS.contains(s.getCampaign())) {
        s.updateCommission(50);
      }
  }

  /**
   * Checks if nysalg contains campagin, If so, do the needed 
   * update of commission.
   */
  public void checkIfNySalgContainsCampaign(Sale s) {
    if (NYSALG.contains(s.getCampaign())) {
      if (ORG.contains(s.getProduct()) || VAR.contains(s.getProduct())) {
        s.updateCommission(225);
        if (s.getRebate().equals("500")) {
          s.updateCommission(-25);
        }
        if (s.getRebate().equals("750") || s.getRebate().equals("1000")) {
          s.updateCommission(-50);
        }
      }
      if (NSPOT.contains(s.getProduct())) {
        s.updateCommission(200);
        if (s.getRebate().equals("300") || s.getRebate().equals("500")
            || s.getRebate().equals("750") || s.getRebate().equals("1000")) {
          s.updateCommission(-25);
        }
      }
      if (RSPOT.contains(s.getProduct())) {
        s.updateCommission(175);
        if (s.getRebate().equals("500")) {
          s.updateCommission(-25);
        }
      }
    }
  }

  /**
   * Checks if leads list contains campagin, If so, do the needed 
   * update of commission.
   */
  public void checkIfLeadsContainsCampaign(Sale s) {
    if (LEADS.contains(s.getCampaign())) {
      if (ORG.contains(s.getProduct()) || VAR.contains(s.getProduct())) {
        s.updateCommission(150);
        if (s.getRebate().equals("500")) {
          s.updateCommission(-35);
        }
        if (s.getRebate().equals("750") || s.getRebate().equals("1000")) {
          s.updateCommission(-70);
        }
      }
      if (NSPOT.contains(s.getProduct())) {
        s.updateCommission(115);
        if (s.getRebate().equals("300") || s.getRebate().equals("500")
            || s.getRebate().equals("750") || s.getRebate().equals("1000")) {
          s.updateCommission(-15);
        }
      }
      if (RSPOT.contains(s.getProduct())) {
        s.updateCommission(100);
        if (s.getRebate().equals("500")) {
          s.updateCommission(-50);
        }
      }
    }
  }

  /**
   * Calculates electricity comission.
   */
  public void calculateElectricityCommission() {
    for (Sale s : saleslist) {
      calculated += s.getCommission();
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
   * @param user the user
   */
  public void hourSalary(double hours, User user) {
    double hoursal = user.getHourRate() * hours;
    calculated += hoursal;
  }

  /**
   * Access method for calculated.
   *
   * @return the calculated
   */

  public double getCalculated() {
    double expectedCalc = Math.round(calculated * 10) / 10.0;
    return expectedCalc;
  }

  /**
   * Removes tax from calculated according to the TaxCount.
   *
   * @param user the User.
   */

  public void taxDeduction(User user) {
    calculated = (calculated * ((100 - user.getTaxCount()) / 100));
  }

  /**
   * This method do the full calculation. This method is needed by API.
   *
   * @param url to the SalesReport.
   * @param user the user.
   * @throws FileNotFoundException Signals that an attempt to open the file
   *                                denoted by a specified pathname has failed.
   */
  
  public void doCalculation(
      String url, User user)
      throws IOException {
    updateList(url);
    removeUnwanted();
    updateElectricityCommission();
    calculateElectricityCommission();
    addMobile(mobileamount);
    hourSalary(hours, user);
    taxDeduction(user);
    
    user.addUserSale(new UserSale(this.salesperiod, getCalculated(), this.paid));
  }
}
