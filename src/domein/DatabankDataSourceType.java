package domein;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;


import javax.persistence.DiscriminatorValue;


import com.opencsv.CSVReader;

@Entity
@DiscriminatorValue("D")
public class DatabankDataSourceType extends TypeDatasource implements Serializable  {
	private static final long serialVersionUID = 1L;
	private String hostname;
	private String username;
	private String password ;
	
	protected DatabankDataSourceType() {
		
	}
	
	public DatabankDataSourceType(String hostname, String username, String password ) {
		this.hostname = hostname;
		this.username = username;
		this.password = password;
		System.out.println(getData());
	}
	
	//List<Double>
	public List<Double> getData() {
		return leesAf();
	}
	
	public List<Double> leesAf() {
		//TODO data uit databank halen
    	List<Double> eenKolom =  new ArrayList<>();
    	//eenKolom.add("databank");
    	eenKolom.add(26.5);
    	eenKolom.add(13.7);
    	eenKolom.add(5.0);
		return eenKolom;

    }
}




