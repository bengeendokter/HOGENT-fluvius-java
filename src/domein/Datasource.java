package domein;

import java.io.IOException;
import java.util.List;

public interface Datasource{
	public int getDatasourceID();
	public String getNaam();
	//naam veranderd van getType naar getTypeDatasource
	public String getTypeDatasource();
	public String getLink();
	
	//data geven
	//List<Double>
	public List<Double> getData() throws IOException;
}