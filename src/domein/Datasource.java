package domein;

import java.io.IOException;
import java.util.List;

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
	public List<Double> getData() throws IOException;
	
	//TODO
	//meta data toevoegen als attribuut
	//file kunnen uploaden
	//wijzigingen worden opgeslagen
}