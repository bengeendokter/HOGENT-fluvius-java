package domein;

import java.util.List;
public class DTODatasource {
	public String naam;
	public String typeDatasource;
	public String link;
	public String size;
	public String hostname;
	public String username;
	public String password;
	
	
	public DTODatasource(String naam, String typeDatasource, String link, String size, String hostname, String username, String password ) {
		this.naam = naam;
		this.typeDatasource = typeDatasource;
		if (typeDatasource.equals("databank")) {
			this.link = "geen link";
		} else {
			this.link = link;
		}
		
		this.size = size;
		this.hostname = hostname;
		this.username = username;
		this.password = password;
	}


	@Override
	public String toString() {
		return naam;
	}
	
	
}