package domein;

import java.io.IOException;
import java.util.List;
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
	public Map<String, Double> getData() throws IOException;
}