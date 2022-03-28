package domein;

import java.io.Reader;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.opencsv.CSVReader;

@Entity
@DiscriminatorValue("C")
public class CsvDataSourceType extends TypeDatasource implements Serializable  {
	private static final long serialVersionUID = 1L;
	private String link;
	
	protected CsvDataSourceType() {
		
	}
	
	public CsvDataSourceType(String link) {
		setLink(link);
		//System.out.println(getData(0));
	}
	
	public void setLink(String link)
	{
		if (link == null || link.isBlank() || link.isEmpty()) {
			throw new IllegalArgumentException("De link moet ingevuld zijn");
		}
		this.link = link;
	}
	
	public String getLink() {
		return link;
	}
	
	public String getHostname() {
		return "geen hostname";
	}
	
	public String getUsername() {
		return "geen username";
	}
	
	public String getPassword() {
		return "geen password";
	}
	
	//List<Double>
	public List<Double> getData(int kolom) {
		/*List<List<Double>> li = new ArrayList<>();
		li.add(Arrays.asList(9.9, 5.6, 7.8));
		li.add(Arrays.asList(5.6, 7.8, 8.6));
		li.add(Arrays.asList(3.4, 3.3, 7.8));
		
		return li.stream().map(e -> e.get(kolom-1)).collect(Collectors.toList());*/
		return leesAf(kolom);
	}
	
	@SuppressWarnings("unused")
	public List<Double> leesAf(int kolom) {
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
		meerdereKolommen.remove(0);
        
        //elementen uit lijst nemen met index de kolom
        List<List<Double>> nieuw = meerdereKolommen.stream()
        .map(line -> line.stream().map(Double::parseDouble).collect(Collectors.toList()))
        .collect(Collectors.toList());
       
        List<Double> lijstje = nieuw.stream().map(e -> e.get(kolom)).collect(Collectors.toList());
        
        //System.out.println(lijstje);
        return lijstje;


    }
	
	@Override
	public int geefKolomLengte() {
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
		/*List<Double> lijst1 = eenKolom.stream().filter(e -> !e.matches(".*[a-z].*")).map(e -> Double.parseDouble(e)).collect(Collectors.toList());
		return lijst1;*/
		

        
        //System.out.println(lijstje);
        return meerdereKolommen.get(0).size();

	}

	@Override
	public String toString() {
		return "csv";
	}
}




