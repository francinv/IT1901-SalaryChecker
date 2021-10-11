package salarychecker.core;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Calculation {

    public List<Sale> saleslist = new ArrayList<Sale>();
    public List<Sale> withTX3 = new ArrayList<Sale>();
    public List<Sale> withNVK = new ArrayList<Sale>();
    public List<Sale> fullPackage = new ArrayList<Sale>();
    public List<Sale> noExtras = new ArrayList<Sale>();

    private List<String> NYSALG = Arrays.asList("Nysalg FK", "Borettslag", "Nysalg TK");
    private List<String> WINBACK = Arrays.asList("WB FK", "WB Lokal", "WB TK");
    private List<String> COMEBACK = Arrays.asList("FK lista", "Lokal lista");
    private List<String> LEADS = Arrays.asList("Bring Flytt", "Bring Prospect", "Diverese leads fjordkraft", "Flytt FK",  "Posten", "Småbaser", "Strøm.com",
     "Strømleverandøren", "Diverese leads FK", "CPH leads", "Eiendomsregisteret TK", "Leads KS", "Småbaser", "Tjenestetorget.no", "Coop/Trumf");

    private List<String> ORG = Arrays.asList("Agrol Spotpris", "Bergen Huseierforening - Topp 5 Garanti", "BOB Garanti", "BOB Spotpris", "Bobilforeningen Spotpris",
    "Farmaceutene Spotpris", "Industri Energi - Garanti", "Industri Energi - Spotpris", "Innkjøpspris Borettslag", "Jordmorforeningen Spotpris", "KNIF Spotpris",
    "KNIF 50 Sikret","LOfavør Garanti", "LOfavør Spotpris", "Norges Eiendomsmeglerforbund Spotpris", "Norsk Narkotikapolitiforening Spotpris", "Samfunnsviterene Spotpris Fornybar",
    "Vestbo Spotpris", "Visma Spotpris", "LO Favør Spotpris", "LO Favør Topp 3 Garanti", "Ranheim Fotball", "Studentsamskipnaden", "TOBB Garanti", "TOBB Spotpris",
    "Garantipris", "Coop / Trumf");

    private List<String> VAR = Arrays.asList("Forutsigbar", "Garantistrøm", "Garantistrøm Oktober-kampanje","Garantistrøm September-kampanje", "Garantistrøm Kampanje Høst 2021",
     "Garantistrøm Kampanje Vår 2021", "Garantistrøm Standard", "Garantistrøm Nord", "Garantipris");

    private List<String> NSPOT = Arrays.asList("Strøm til Spotpris", "Strøm til Spotpris Kampanje", "Solstrøm", "Trønderspot", "Trønderspot Kampanje", "Elbil-avtalen");

    private List<String> RSPOT = Arrays.asList("Trønderspot Spesial", "Strøm til Spotpris Spesial");

    private List<String> BUN = Arrays.asList("EuroBonus-avtalen", "PowerSpot");

    private int calculated = 0;

    SalaryCSVReader salaryCSVReader = new SalaryCSVReader();

    public void updateList(String url) throws FileNotFoundException {
        saleslist = salaryCSVReader.csvToBean(url);

    }

    public void removeUnwanted() {
        saleslist = saleslist.stream()
                    .filter(s->s.getAnleggStatus().equals("23-Etablert"))
                    .collect(Collectors.toList());
    }

    public void updateElectricityCommission() {
        for (Sale s : saleslist) {
            s.setProvisjon(0);

            if (s.getSalgsType().equals("Produktbytte") && WINBACK.contains(s.getCampaign()) || s.getSalgsType().equals("Produktbytte") && LEADS.contains(s.getCampaign())) {
                s.updateProvisjon(50);
            }
            else if (s.getTX3().equals("Ja") && s.getNVK().equals("Nei")){
                s.updateProvisjon(75);
            }
            else if (s.getNVK().equals("Ja") && s.getTX3().equals("Nei")){
                s.updateProvisjon(50);
            }
            else if (s.getTX3().equals("Ja") && s.getTX3().equals("Ja")){
                s.updateProvisjon(125);
            }
            else if(NYSALG.contains(s.getCampaign()) && ORG.contains(s.getProduct()) || NYSALG.contains(s.getCampaign()) && VAR.contains(s.getProduct())) {
                s.updateProvisjon(225);
                if (s.getRebate().equals("500")){
                    s.updateProvisjon(-25);
                }
                else if(s.getRebate().equals("750") || s.getRebate().equals("1000")){
                    s.updateProvisjon(-50);
                }
            }
            else if (NYSALG.contains(s.getCampaign()) && NSPOT.contains(s.getProduct())) {
                s.updateProvisjon(200);
                if (s.getRebate().equals("300") || s.getRebate().equals("500") || s.getRebate().equals("750") || s.getRebate().equals("1000") ) {
                    s.updateProvisjon(-25);
                }
            }
            else if (BUN.contains(s.getProduct())) {
                s.updateProvisjon(50);
            }
            else if (NYSALG.contains(s.getCampaign()) && RSPOT.contains(s.getProduct())) {
                s.updateProvisjon(175);
                if (s.getRebate().equals("500")){
                    s.updateProvisjon(-25);
                }
            }
            else if(WINBACK.contains(s.getCampaign()) && ORG.contains(s.getProduct()) || WINBACK.contains(s.getCampaign()) && VAR.contains(s.getProduct())) {
                s.updateProvisjon(110);
                if (s.getRebate().equals("500")){
                    s.updateProvisjon(-30);
                }
                else if(s.getRebate().equals("750") || s.getRebate().equals("1000")){
                    s.updateProvisjon(-60);
                }
            }
            else if (WINBACK.contains(s.getCampaign()) && NSPOT.contains(s.getProduct())) {
                s.updateProvisjon(80);
                if (s.getRebate().equals("300") || s.getRebate().equals("500") || s.getRebate().equals("750") || s.getRebate().equals("1000") ) {
                    s.updateProvisjon(-30);
                }
            }
            else if (WINBACK.contains(s.getCampaign()) && RSPOT.contains(s.getProduct())) {
                s.updateProvisjon(50);
                if (s.getRebate().equals("500")){
                    s.updateProvisjon(-25);
                }
            }
            else if(COMEBACK.contains(s.getCampaign()) && ORG.contains(s.getProduct()) || COMEBACK.contains(s.getCampaign()) && VAR.contains(s.getProduct())) {
                s.updateProvisjon(175);
                if (s.getRebate().equals("500")){
                    s.updateProvisjon(-25);
                }
                else if(s.getRebate().equals("750") || s.getRebate().equals("1000")){
                    s.updateProvisjon(-50);
                }
            }
            else if (COMEBACK.contains(s.getCampaign()) && NSPOT.contains(s.getProduct())) {
                s.updateProvisjon(150);
                if (s.getRebate().equals("300") || s.getRebate().equals("500") || s.getRebate().equals("750") || s.getRebate().equals("1000") ) {
                    s.updateProvisjon(-25);
                }
            }
            else if (COMEBACK.contains(s.getCampaign()) && RSPOT.contains(s.getProduct())) {
                s.updateProvisjon(125);
                if (s.getRebate().equals("500")){
                    s.updateProvisjon(-25);
                }
            }
            else if(LEADS.contains(s.getCampaign()) && ORG.contains(s.getProduct()) || LEADS.contains(s.getCampaign()) && VAR.contains(s.getProduct())) {
                s.updateProvisjon(150);
                if (s.getRebate().equals("500")){
                    s.updateProvisjon(-35);
                }
                else if(s.getRebate().equals("750") || s.getRebate().equals("1000")){
                    s.updateProvisjon(-70);
                }
            }
            else if (LEADS.contains(s.getCampaign()) && NSPOT.contains(s.getProduct())) {
                s.updateProvisjon(115);
                if (s.getRebate().equals("300") || s.getRebate().equals("500") || s.getRebate().equals("750") || s.getRebate().equals("1000") ) {
                    s.updateProvisjon(-15);
                }
            }
            else if (LEADS.contains(s.getCampaign()) && RSPOT.contains(s.getProduct())) {
                s.updateProvisjon(100);
                if (s.getRebate().equals("500")){
                    s.updateProvisjon(-50);
                }
            }
        }
    }

    public void calculateElectricityCommission() {
        for (Sale s : saleslist) {
            calculated += s.getProvisjon();
        }
    }

    public void addMobile(int amount) {
        int per = 200;
        int mobcommission = per * amount;
        calculated += mobcommission;
    }

    public void hourSalary(int hours) {
        User user = new User();
        int hoursal = user.getTimesats() * hours;
        calculated += hoursal;
    }

    public int getCalculated() {
        return calculated;
    }

    // public int taxDeduction(double tax) {

    // }
    public static void main(String[] args) throws FileNotFoundException {
        String url = "salary-checker/core/src/main/resources/salarychecker/core/SalesReport.csv";
        Calculation calculation = new Calculation();
        calculation.updateList(url);
        calculation.removeUnwanted();
        calculation.updateElectricityCommission();
        calculation.calculateElectricityCommission();
        calculation.addMobile(5);
        calculation.hourSalary(30);
        calculation.getCalculated();


    }

}
