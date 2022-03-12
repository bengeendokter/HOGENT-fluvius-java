package domein;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DataSource")
@NamedQueries({
	@NamedQuery(name = "datasource.findByNaam", query = "select d from domein.MVODatasource d where d.naam = :naam")})
public class MVODatasource implements Serializable, Datasource
{
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int datasourceID;


	@Column(unique=true)
	private String naam;


	private String link;
	
	//type uitbreiding
	//private String typeDatasource;
	@OneToOne(cascade = CascadeType.PERSIST)
	private TypeDatasource typeDatasource;
	
	@Override
	//List<Double>
	public List<String> getData() throws IOException {
		return typeDatasource.getData();
		//return Arrays.asList(3.4, 5.6, 7.8);
	}
	
	protected MVODatasource()
	{
		
	}
	
	public MVODatasource(DTODatasource dds) throws IOException
	{
		setNaam(dds.naam);
		//TODO setter met dds.typeDatasource waarde, dus String
				//TODO size van ExcelDataSourceType met dds.size
		if (dds.typeDatasource.equals("excel"))
			setTypeDatasource(new ExcelDataSourceType(dds.link, dds.size));
		else if (dds.typeDatasource.equals("csv"))
			setTypeDatasource(new CsvDataSourceType(dds.link));
		else if (dds.typeDatasource.equals("databank"))
			setTypeDatasource(new DatabankDataSourceType(dds.hostname, dds.username, dds.password));
		
		setLink(dds.link);
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
	
	public String getTypeDatasource() {
		return "csv";
		
		//TODO switch case met instanceof en dan "csv", "excel" of "databank" returnen
		//switch (typeDatasource) { case typeDatasource instanceof CsvDataSourceType ...}
	}

	public String getLink() {
		return link;
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
		/*if(typeDatasource == null || typeDatasource.isBlank())
		{
			throw new IllegalArgumentException("De type van de Datasource mag niet leeg zijn");
		}*/
		
		this.typeDatasource = typeDatasource;
		
	}
	
	public final void setLink(String link)
	{
		if(link == null || link.isBlank())
		{
			throw new IllegalArgumentException("De link van de Datasource mag niet leeg zijn");
		}
		
		this.link = link;
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

}