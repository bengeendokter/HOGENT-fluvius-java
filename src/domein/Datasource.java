package domein;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.eclipse.persistence.jpa.jpql.parser.DateTime;

public interface Datasource{
	public int getDatasourceID();
	public String getNaam();
	//naam veranderd van getType naar getTypeDatasource
	public TypeDatasource getTypeDatasource();
	
	//specifiek voor types
	public String getLink();
	public String getHostname();
	public String getUsername();
	public String getPassword();
	
	
	//data geven
	//List<Double>
	
	//public List<Double> getData() throws IOException;
	public List<Double> getData(int kolom) throws IOException;
	
	//TODO
	//meta data toevoegen als attribuut
	//file kunnen uploaden
	//wijzigingen worden opgeslagen
	public boolean getCorrupt();
	public LocalDate getDatum();
	public String getMaat();
	public String getWijzigbaarheid();
	public boolean wijzigNood();
	public int getKolom();
	
}