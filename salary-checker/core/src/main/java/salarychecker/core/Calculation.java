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
    private List<String> LEADS = Arrays.asList("Bring Flytt", "Bring Prospect", "Diverese leads fjordkraft", "Flytt FK",  "Posten", "Småbaser", "Strøm.com", "Strømleverandøren", "Diverese leads FK", "CPH leads", "Eiendomsregisteret TK", "Leads KS", "Småbaser", "Tjenestetorget.no", "Coop/Trumf");

    private List<String> ORG = Arrays.asList("Agrol Spotpris", "Bergen Huseierforening - Topp 5 Garanti", "BOB Garanti", "BOB Spotpris", "Bobilforeningen Spotpris", "Farmaceutene Spotpris", )

    
    





Industri Energi - Garanti 
Industri Energi - Spotpris
Innkjøpspris Borettslag
Jordmorforeningen Spotpris
KNIF Spotpris
KNIF 50 Sikret
LOfavør Garanti
Lofavør Spotpris
Norges Eiendomsmeglerforbund Spotpris
Norsk Narkotikapolitiforening Spotpris
Samfunnsviterene Spotpris Fornybar
Vestbo Spotpris
Visma Spotpris








    
    

    SalaryCSVReader salaryCSVReader = new SalaryCSVReader();

    public void updateList(String url) throws FileNotFoundException {
        saleslist = salaryCSVReader.csvToBean(url);
        
    }

    public void removeUnwanted() {
        saleslist = saleslist.stream()
                    .filter(s->s.getAnleggStatus().equals("23-Etablert"))
                    .collect(Collectors.toList());
    }

    public void filteronlyTX3() {
        withTX3 = saleslist.stream()
            .filter(s->s.getTX3().equals("Ja"))
            .filter(s->s.getNVK().equals("Nei"))
            .collect(Collectors.toList());

    }

    public void filteronlyNVK() {
        withNVK = saleslist.stream()
                    .filter(s->s.getNVK().equals("Ja"))
                    .filter(s->s.getTX3().equals("Nei"))
                    .collect(Collectors.toList());

    }

    public void filterNVKTX3() {
        fullPackage = saleslist.stream()
                    .filter(s->s.getNVK().equals("Ja"))
                    .filter(s->s.getTX3().equals("Ja"))
                    .collect(Collectors.toList());

    }

    public void filterNoExtras() {
        noExtras = saleslist.stream()
                    .filter(s->s.getNVK().equals("Nei"))
                    .filter(s->s.getTX3().equals("Nei"))
                    .collect(Collectors.toList());

    }

    public void calculateProvisjon() {
        for (Sale s : withTX3) {
            s.setProvisjon(75);
            System.out.println(s);
        }

        for (Sale s : withNVK) {
            s.setProvisjon(50);
            System.out.println(s);
        }

        for (Sale s : fullPackage) {
            s.setProvisjon(125);
            System.out.println(s);
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        String url = "salary-checker/core/src/main/resources/salarychecker/core/SalesReport.csv";
        Calculation calculation = new Calculation();
        calculation.updateList(url);
        calculation.removeUnwanted();
        calculation.filteronlyTX3();
        calculation.filteronlyNVK();
        calculation.filterNVKTX3();
        calculation.filterNoExtras();
        calculation.updateProvisjon();

    }
    
}
