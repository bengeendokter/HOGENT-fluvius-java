package domein;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("E")
//@Table(name = "CsvDataSource")
public class ExcelDataSourceType1 extends TypeDatasource1 implements Serializable  {
	//"file:src/data/dataProject.csv"
	
	/*private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int csvID;*/
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String link;
	private String size;
	
	protected ExcelDataSourceType1() {
		
	}
	
	public ExcelDataSourceType1(String link, String size) {
		this.link = link;
		this.size = size;
	}
	
	
	public List<Double> getData() {
		return Arrays.asList(3.4, 5.6, 7.8);
	}
}
