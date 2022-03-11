package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class CsvReader {
	private static final String SAMPLE_CSV_FILE_PATH =
			"src/data/verbruiksgegevens-per-maand.csv";
			//"src/data/dataProject2.csv";

    public static void main(String[] args) {
        try (
            Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
            CSVReader csvReader = new CSVReader(reader);
        ) {
            // Reading Records One by One in a String array
            String[] nextRecord;
            int teller = 0;
            while ((nextRecord = csvReader.readNext()) != null && teller < 10) {
                /*System.out.println("Waarde : " + nextRecord[1]);
                System.out.println("Naam : " + nextRecord[2]);
                System.out.println("Leeftijd : " + nextRecord[3]);
                System.out.println("==========================");*/
                
                if (nextRecord != null) {
                	//System.out.println(Arrays.toString(nextRecord));
                	String[] record = nextRecord[0].split(";");
                	
                	//Arrays.asList(nextRecord).forEach(e -> System.out.println(e.toString()));
                	
                	System.out.println("Hoofdgemeente : " + record[0]);
                	System.out.println("Energie : " + record[1]);
                    System.out.println("SLP : " + record[2]);
                    System.out.println("202109 : " + record[3]);
                    System.out.println("202108 : " + record[4]);
                	System.out.println("202107 : " + record[5]);
                    System.out.println("202106 : " + record[6]);
                    System.out.println("202105 : " + record[7]);
                    System.out.println("202104 : " + record[8]);
                    System.out.println("202103 : " + record[9]);
                    System.out.println("202102 : " + record[10]);
                    System.out.println("202101 : " + record[11]);
                    System.out.println("202012 : " + record[12]);
                    System.out.println("202011 : " + record[13]);
                    System.out.println("202010 : " + record[14]);
                    System.out.println("202009 : " + record[15]);
                    System.out.println("Regio : " + record[16]);
                    System.out.println("==========================");
                }
                teller++;
            }
        }
        catch (Exception e)  {
        	e.printStackTrace();
        }
    }
}
