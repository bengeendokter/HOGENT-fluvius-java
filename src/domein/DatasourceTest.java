package domein;

import java.io.Serializable;
import java.util.Arrays;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Datasource1")
public class DatasourceTest implements Serializable, Datasource
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
	private TypeDatasource1 typeDatasource;
	
	@Override
	public List<Double> getData() {
		//return typeDatasource.getData();
		return Arrays.asList(3.4, 5.6, 7.8);
	}
	
	protected DatasourceTest()
	{
		
	}
	
	public DatasourceTest(DTODatasource dds)
	{
		setNaam(dds.naam);
		//setTypeDatasource(dds.typeDatasource);
		if (dds.typeDatasource.equals("excel"))
			setTypeDatasource(new ExcelDataSourceType1(dds.link, "50mb"));
		else if (dds.typeDatasource.equals("csv"))
			setTypeDatasource(new CsvDataSourceType1(dds.link));
		
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
		//return typeDatasource;
		return "csv";
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
	public final void setTypeDatasource(TypeDatasource1 typeDatasource)
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
		DatasourceTest other = (DatasourceTest) obj;
		return Objects.equals(naam, other.naam);
	}

	@Override
	public String toString()
	{
		return naam;
	}

}