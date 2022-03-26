package domein;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "DataSource")
@NamedQueries({
	@NamedQuery(name = "datasource.findByNaam", query = "select d from domein.MVODatasource d where d.naam = :naam")})
public class MVODatasource implements Serializable, Datasource
{
private static final long serialVersionUID = 1L;
	//TODO size weg, alleen naam attribuut, controle databank type
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int datasourceID;


	@Column(unique=true)
	private String naam;
	
	//nieuw
	private LocalDate datum;
	private boolean corrupt;
	private String wijzigbaarheid;
	private String maat;
	private boolean wijzigNood;
	private int kolom;
	
	
	
	//type uitbreiding
	//private String typeDatasource;
	@OneToOne(cascade = CascadeType.PERSIST)
	private TypeDatasource typeDatasource;
	
	@Override
	//List<Double>
	public Map<String, Double> getData(int kolom) throws IOException {
		Map<String, Double> map = new HashMap<>();
		typeDatasource.getData(kolom).forEach(d -> {
			map.put(String.format("%s_%s", naam, map.size()), d);
			});
		return map;
		//return Arrays.asList(3.4, 5.6, 7.8);
	}
	
	protected MVODatasource()
	{
		
	}
	
	public MVODatasource(DTODatasource dds) throws IOException
	{
		setNaam(dds.naam);
		
		if (dds.typeDatasource == null || dds.typeDatasource.isBlank()) {
			throw new IllegalArgumentException("Selecteer aub een type");
		}
		if (dds.typeDatasource.equals("excel"))
			setTypeDatasource(new ExcelDataSourceType(dds.link));
		else if (dds.typeDatasource.equals("csv"))
			setTypeDatasource(new CsvDataSourceType(dds.link));
		else if (dds.typeDatasource.equals("databank"))
			setTypeDatasource(new DatabankDataSourceType(dds.hostname, dds.username, dds.password));
		else throw new IllegalArgumentException("Ongeldige type geselecteerd");
		
		setWijzigbaarheid(dds.wijzigbaarheid);
		setDatum();
		setMaat(dds.maat);
		setKolom(dds.kolom);
		setCorrupt(dds.corrupt);
		setWijzigNood(dds.wijzigbaarheid);
		
		//TODO setter met dds.typeDatasource waarde, dus String
				//TODO size van ExcelDataSourceType met dds.size
	
		
		
	}
	
	
	public void setDatasourceID(int mock)
	{
		this.datasourceID = mock;
	}
	
	
	public int getDatasourceID()
	{
		return datasourceID;
	}

	public String getNaam() {
		return naam;
	}
	
	public TypeDatasource getTypeDatasource() {
		/*if (typeDatasource instanceof CsvDataSourceType)
			return "csv";
		else if (typeDatasource instanceof ExcelDataSourceType)
			return "excel";
		else if (typeDatasource instanceof DatabankDataSourceType)
			return "excel";
		return "";*/
		return typeDatasource;
		
	}
	
	public final void setNaam(String naam)
	{
		if(naam == null || naam.isBlank())
		{
			throw new IllegalArgumentException("De naam van de Datasource mag niet leeg zijn");
		}
		
		this.naam = naam;
	}
	
	//String of TypeDatasource
	public final void setTypeDatasource(TypeDatasource typeDatasource)
	{
		this.typeDatasource = typeDatasource;
		
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(naam);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MVODatasource other = (MVODatasource) obj;
		return Objects.equals(naam, other.naam);
	}
	
	@Override
	public String toString()
	{
		return naam;
	}

	@Override
	public String getLink() {
		return typeDatasource.getLink();
	}

	@Override
	public String getHostname() {
		return typeDatasource.getHostname();
	}

	@Override
	public String getUsername() {
		return typeDatasource.getUsername();
	}

	@Override
	public String getPassword() {
		return typeDatasource.getPassword();
	}
	
	//nieuw
	@Override
	public boolean getCorrupt() {
		return corrupt;
	}
	
	@Override
	public LocalDate getDatum() {
		return datum;
	}
	
	@Override
	public String getMaat() {
		return maat;
	}
	
	@Override
	public String getWijzigbaarheid() {
		return wijzigbaarheid;
	}
	
	@Override
	public boolean wijzigNood() {
		return wijzigNood;
	}
	
	@Override
	public int getKolom() {
		
		return kolom;
	}

	public void setDatum() {
		this.datum = LocalDate.now(); 
	}

	public void setCorrupt(boolean corrupt) {
		this.corrupt = corrupt;
	}

	public void setWijzigbaarheid(String wijzigbaarheid) {
		if(wijzigbaarheid == null || wijzigbaarheid.isBlank())
		{
			throw new IllegalArgumentException("De wijzigbaarheid van de Datasource mag niet leeg zijn");
		}
	
		this.wijzigbaarheid = wijzigbaarheid;
	}

	public void setMaat(String maat) {
		if(maat == null || maat.isBlank())
		{
			throw new IllegalArgumentException("De maat van de Datasource mag niet leeg zijn");
		}
		this.maat = maat;
	}

	public void setWijzigNood(String wijzigbaarheid) {
		LocalDate d = getDatum();
		
		if (wijzigbaarheid.equals("snel") && d.isBefore(LocalDate.now().minusMonths(3))) 
			this.wijzigNood = true;
		this.wijzigNood = false;
		
	}

	public void setKolom(int kolom) {
		if(kolom <=0)
		{
			throw new IllegalArgumentException("De kolom van de Datasource is geen geldige waarde");
		}
		this.kolom = kolom;
	}
	
	

}