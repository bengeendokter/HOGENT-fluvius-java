package domein;

public class DTODatasource {
	public String naam;
	public String typeDatasource;
	public String link;
	public String hostname;
	public String username;
	public String password;
	public boolean corrupt;
	public String wijzigbaarheid;
	public String maat;
	public int kolom;

	public DTODatasource(String naam, String typeDatasource, String link, String hostname, String username,
			String password, boolean corrupt, String wijzigbaarheid, String maat, int kolom) {
		this.naam = naam;
		this.typeDatasource = typeDatasource;
		this.link = link;
		this.hostname = hostname;
		this.username = username;
		this.password = password;
		this.corrupt = corrupt;
		this.wijzigbaarheid = wijzigbaarheid;
		this.maat = maat;
		this.kolom = kolom;
	}

	@Override
	public String toString() {
		return naam;
	}

}