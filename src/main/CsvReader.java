package main;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.opencsv.CSVReader;

public class CsvReader {
	private static final String SAMPLE_CSV_FILE_PATH =
			"src/data/csvDouble.csv";
			//"src/data/verbruiksgegevens-per-maand.csv";
			//"src/data/dataProject2.csv";

    public static void main(String[] args) {
        try (
            Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
            CSVReader csvReader = new CSVReader(reader);	
        ) {
        	
        	//csv bestaat uit 1 of meerdere kolommen
        	List<List<String>> lijstGeheel1 = new ArrayList<>();
        	List<String> lijstGeheel2 =  new ArrayList<>();
        	
    		/*if (csvReader.readNext()[0].contains(";")) {
        		 lijstGeheel1 = new ArrayList<>();
        	} else {
        		lijstGeheel2 = new ArrayList<>();
        	}*/
        	
            String[] nextRecord;
            int teller = 0;
          //aantal lijnen x die je wil lezen -> teller < x (teller >=0 als je alles wil lezen)
            while ((nextRecord = csvReader.readNext()) != null && teller >=0) {
                
                if (nextRecord != null) {

                	
                	//kolommen in array van String plaatsen door split (csv kolommen worden onderscheiden van elkaar door ;)
                	String[] record = nextRecord[0].split(";");
                	
                	
                	if (!nextRecord[0].contains(";")) {
                		if (teller == 0)
                			record[0] = record[0].substring(1);
                		
                		//voor 1 kolom waarde
                		//enige kolom toevoegen aan in lijstGeheel
                		lijstGeheel2.add(record[0]);
                	} else {
                		//voor kolommen waarden
                		//alle kolomen toevoegen als lijst in lijstGeheel
                		lijstGeheel1.add(new ArrayList<>(Arrays.asList(record)));
                	}
                	
                }
                teller++;
            }
            
            System.out.println(Arrays.toString(lijstGeheel2.toArray()));
            System.out.println("----------");
            System.out.println(Arrays.toString(lijstGeheel1.toArray()));
            
            //kolomnamen verwijderen uit lijst
            lijstGeheel1.remove(0);
            
            //elementen uit lijst nemen met index de kolom
            List<List<Double>> nieuw = lijstGeheel1.stream()
            .map(line -> line.stream().map(Double::parseDouble).collect(Collectors.toList()))
            .collect(Collectors.toList());
           
            List<Double> lijstje = nieuw.stream().map(e -> e.get(1)).collect(Collectors.toList());
            
            System.out.println(lijstje);
            
            
        }
        catch (Exception e)  {
        	e.printStackTrace();
        }
    }
}
