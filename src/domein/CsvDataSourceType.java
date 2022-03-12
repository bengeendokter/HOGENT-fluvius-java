package domein;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;


import javax.persistence.DiscriminatorValue;


import com.opencsv.CSVReader;

@Entity
@DiscriminatorValue("C")
public class CsvDataSourceType extends TypeDatasource implements Serializable  {
	private static final long serialVersionUID = 1L;
	private String link;
	
	protected CsvDataSourceType() {
		
	}
	
	public CsvDataSourceType(String link) {
		this.link = link;
		System.out.println(getData());
	}
	
	//List<Double>
	public List<Double> getData() {
		//return Arrays.asList(3.4, 5.6, 7.8);
		return leesAf();
	}
	
	public List<Double> leesAf() {
		List<List<String>> meerdereKolommen =  new ArrayList<>();
    	List<String> eenKolom =  new ArrayList<>();
		try (
	            Reader reader = Files.newBufferedReader(Paths.get(link));
	            CSVReader csvReader = new CSVReader(reader);	
	        ) {
	        	
	        	//csv bestaat uit 1 of meerdere kolommen
	        	List<List<String>> lijstGeheel1 = new ArrayList<>();
	        	List<String> lijstGeheel2 =  new ArrayList<>();
	        	
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
	            
	            meerdereKolommen = lijstGeheel1;
	            eenKolom = lijstGeheel2;
	            
	        }
	        catch (Exception e)  {
	        	e.printStackTrace();
	        }
		List<Double> lijst1 = eenKolom.stream().filter(e -> !e.matches(".*[a-z].*")).map(e -> Double.parseDouble(e)).collect(Collectors.toList());
		return lijst1;


    }
}




