package domein;

import java.time.LocalDate;
import java.util.List;

import org.eclipse.persistence.jpa.jpql.parser.DateTime;
public class DTODatasource {
	public String naam;
	public String typeDatasource;
	public String link;
	public String hostname;
	public String username;
	public String password;
	
	//public LocalDate datum;
	public boolean corrupt;
	public String wijzigbaarheid;
	public String maat;
	//public boolean wijzigNood;
	public int kolom;
	
	
	public DTODatasource(String naam, String typeDatasource, String link, String hostname, String username, String password
			,boolean corrupt
			,String wijzigbaarheid
			,String maat
			,int kolom) {
		this.naam = naam;
		this.typeDatasource = typeDatasource;
		this.link = link;
		this.hostname = hostname;
		this.username = username;
		this.password = password;
		//nieuw
		//this.datum = datum;
		this.corrupt= corrupt;
		this.wijzigbaarheid = wijzigbaarheid;
		this.maat = maat;
		//this.wijzigNood = wijzigNood;
		this.kolom = kolom;
	}


	@Override
	public String toString() {
		return naam;
	}
	
	
}