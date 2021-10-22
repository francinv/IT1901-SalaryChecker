package salarychecker.core;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Calculation {

    private List<Sale> saleslist = new ArrayList<Sale>();

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
     "Garantistrøm Kampanje Vår 2021", "Garantistrøm Standard", "Garantistrøm Nord", "Garantipris", "Garantistrøm Kampanje Høst");

    private List<String> NSPOT = Arrays.asList("Strøm til Spotpris", "Strøm til Spotpris Kampanje", "Solstrøm", "Trønderspot", "Trønderspot Kampanje", "Elbil-avtalen");

    private List<String> RSPOT = Arrays.asList("Trønderspot Spesial", "Strøm til Spotpris Spesial");

    private List<String> BUN = Arrays.asList("EuroBonus-avtalen", "PowerSpot");

    private double calculated;
    private User user;
    private SalaryCSVReader salaryCSVReader = new SalaryCSVReader();

    public Calculation(User user){
        this.user = user;
    }

    public List<Sale> getSaleslist() {
        return new ArrayList<>(saleslist);
    }

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

            if (s.getTX3().equals("Ja") && s.getNVK().equals("Nei")){
                s.setProvisjon(75);
            }
            if (s.getNVK().equals("Ja") && s.getTX3().equals("Nei")){
                s.setProvisjon(50);
            }
            if (s.getNVK().equals("Ja") && s.getTX3().equals("Ja")){
                s.setProvisjon(125);
            }

            if (s.getSalgsType().equals("Produktbytte") && WINBACK.contains(s.getCampaign()) || s.getSalgsType().equals("Produktbytte") && LEADS.contains(s.getCampaign())) {
                s.updateProvisjon(50);
            }

            if (BUN.contains(s.getProduct()) && WINBACK.contains(s.getCampaign()) || BUN.contains(s.getProduct()) && LEADS.contains(s.getCampaign())) {
                s.updateProvisjon(50);
            }

            if(NYSALG.contains(s.getCampaign())) {
                
                if (ORG.contains(s.getProduct()) || VAR.contains(s.getProduct())){
                    s.updateProvisjon(225);
                    if (s.getRebate().equals("500")){
                        s.updateProvisjon(-25);
                    }
                    if(s.getRebate().equals("750") || s.getRebate().equals("1000")){
                        s.updateProvisjon(-50);
                    }
                }
                if (NSPOT.contains(s.getProduct())) {
                    s.updateProvisjon(200);
                    if (s.getRebate().equals("300") || s.getRebate().equals("500") || s.getRebate().equals("750") || s.getRebate().equals("1000") ) {
                        s.updateProvisjon(-25);
                    }
                }
                if (RSPOT.contains(s.getProduct())) {
                    s.updateProvisjon(175);
                    if (s.getRebate().equals("500")){
                        s.updateProvisjon(-25);
                    }
                }
                
            }

            if (WINBACK.contains(s.getCampaign())) {
                if(ORG.contains(s.getProduct()) ||  VAR.contains(s.getProduct())) {
                    s.updateProvisjon(110);
                    if (s.getRebate().equals("500")){
                        s.updateProvisjon(-30);
                    }
                    if(s.getRebate().equals("750") || s.getRebate().equals("1000")){
                        s.updateProvisjon(-60);
                    }
                }
                if (NSPOT.contains(s.getProduct())) {
                    s.updateProvisjon(80);
                    if (s.getRebate().equals("300") || s.getRebate().equals("500") || s.getRebate().equals("750") || s.getRebate().equals("1000") ) {
                        s.updateProvisjon(-30);
                    }
                }
                if (RSPOT.contains(s.getProduct())) {
                    s.updateProvisjon(50);
                    if (s.getRebate().equals("500")){
                        s.updateProvisjon(-25);
                    }
                }

            }

            if(COMEBACK.contains(s.getCampaign())){

                if(ORG.contains(s.getProduct()) || VAR.contains(s.getProduct())) {
                    s.updateProvisjon(175);
                    if (s.getRebate().equals("500")){
                        s.updateProvisjon(-25);
                    }
                    if(s.getRebate().equals("750") || s.getRebate().equals("1000")){
                        s.updateProvisjon(-50);
                    }
                }
                if (NSPOT.contains(s.getProduct())) {
                    s.updateProvisjon(150);
                    if (s.getRebate().equals("300") || s.getRebate().equals("500") || s.getRebate().equals("750") || s.getRebate().equals("1000") ) {
                        s.updateProvisjon(-25);
                    }
                }
                if (RSPOT.contains(s.getProduct())) {
                    s.updateProvisjon(125);
                    if (s.getRebate().equals("500")){
                        s.updateProvisjon(-25);
                    }
                }
            }
            
            if(LEADS.contains(s.getCampaign())) {
                if(ORG.contains(s.getProduct()) || VAR.contains(s.getProduct())) {
                    s.updateProvisjon(150);
                    if (s.getRebate().equals("500")){
                        s.updateProvisjon(-35);
                    }
                    if(s.getRebate().equals("750") || s.getRebate().equals("1000")){
                        s.updateProvisjon(-70);
                    }
                }
                if (NSPOT.contains(s.getProduct())) {
                    s.updateProvisjon(115);
                    if (s.getRebate().equals("300") || s.getRebate().equals("500") || s.getRebate().equals("750") || s.getRebate().equals("1000") ) {
                        s.updateProvisjon(-15);
                    }
                }
                if (RSPOT.contains(s.getProduct())) {
                    s.updateProvisjon(100);
                    if (s.getRebate().equals("500")){
                        s.updateProvisjon(-50);
                    }
                }
            } 
            System.out.println(s.getProvisjon());
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

    public void hourSalary(double hours) {
        double hoursal = user.getHourRate() * hours;
        calculated += hoursal;
    }

    public double getCalculated() {
        return calculated;
    }

    public void taxDeduction() {
        calculated = (calculated * ((100-user.getTaxCount())/100));
    }

    /**
     * 
     * @param url
     * @param hours
     * @param mobileamount
     * @throws FileNotFoundException
     */
    public void doCalculation(String url, double hours, int mobileamount) throws FileNotFoundException{
        updateList(url);
        removeUnwanted();
        updateElectricityCommission();
        calculateElectricityCommission();
        addMobile(mobileamount);
        hourSalary(hours);
        taxDeduction();
    }
}