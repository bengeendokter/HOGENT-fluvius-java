package domein;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

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
	public Map<String, Double> getData(int kolom) throws IOException;
	
	//TODO
	//meta data toevoegen als attribuut
	//file kunnen uploaden
	//wijzigingen worden opgeslagen
	//true bij corrupt
	public boolean getCorrupt();
	public LocalDate getDatum();
	public String getMaat();
	public String getWijzigbaarheid();
	//true als het moet wijzigen
	public boolean wijzigNood();
	public int getKolom();
	
}