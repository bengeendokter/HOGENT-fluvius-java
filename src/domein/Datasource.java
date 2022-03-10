package domein;

import java.util.List;

public interface Datasource{
	public int getDatasourceID();
	public String getNaam();
	//naam veranderd van getType naar getTypeDatasource
	public String getTypeDatasource();
	public String getLink();
	
	//data geven
	public List<Double> getData();
}