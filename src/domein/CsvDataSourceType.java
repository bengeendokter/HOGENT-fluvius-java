package domein;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CsvDataSource")
public class CsvDataSourceType implements Serializable, TypeDatasource {
	//"file:src/data/dataProject.csv"
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int csvID;
	
	
	private String link;
	
	protected CsvDataSourceType() {
		
	}
	
	public CsvDataSourceType(String link) {
		this.link = link;
	}
	
	public List<Double> getData() {
		return Arrays.asList(3.4, 5.6, 7.8);
	}
}
