package salarychecker.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {

    private static ObservableList<Record> dataList
            = FXCollections.observableArrayList();

    public static void readCSV() {

        String CsvFile = "src/main/resources/SalesReport.csv";

        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader(CsvFile));

            int i = 1;
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(";");

                if(fields.length==38) {
                    Record record = new Record(fields[0], fields[14], fields[15],
                            fields[22], fields[25], fields[34], fields[35], fields[37]);
                    dataList.add(record);
                }else{
                    Record record = new Record("0", "0", "0",
                            "0", "0","0", "0", "0");
                    dataList.add(record);
                }

            }

        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /** 
     * SalgsID (A-colon)
     * AnleggStatus (O-colon)
     * Salgstype (P-colon)
     * Bekreftet (W-colon)
     * Kampanje (Z-colon)
     * Bruddgebyr(KR) (AI-colon)
     * Norsk Vannkraft (AJ-colon)
     * ProductHubNavn (AL-colon)
     */
    public static void printCSV(){
        for (Record r: dataList) {
            System.out.println(r.toString());
        }
    }


}
