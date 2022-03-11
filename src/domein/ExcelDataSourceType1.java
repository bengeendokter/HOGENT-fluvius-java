package domein;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
	
	//exception weg omdat print alleen voor te testen was
	public ExcelDataSourceType1(String link, String size) throws IOException {
		this.link = link;
		this.size = size;
		System.out.println(Arrays.toString(getData().toArray()));
	}
	
	//List<Double>
	public List<String> getData() throws IOException {
		if (link.charAt(link.length()-1) == 'x') {
			return leesAfXLSX();
		} 
		return leesAfXLS();
	}
	
	public List<String> leesAfXLS() throws IOException  {  
		FileInputStream fis=new FileInputStream(new File(link));  
		@SuppressWarnings("resource")
		HSSFWorkbook wb=new HSSFWorkbook(fis);   
		HSSFSheet sheet=wb.getSheetAt(0);    
		FormulaEvaluator formulaEvaluator=wb.getCreationHelper().createFormulaEvaluator();  
		
		//xlsx bestaat uit 1 of meerdere kolommen
    	List<List<String>> lijstGeheel1 = new ArrayList<>();
		List<String> lijst = new ArrayList<>();
		
		int teller = 0;
		boolean eenKolom = false;
		
		for(Row row: sheet)  {  
			//aantal rijen dat je wilt lezen is teller < x (voor alle rijen teller < 0)
			if (teller < 0) break;
	
			for(Cell cell: row) {      
	
				switch(formulaEvaluator.evaluateInCell(cell).getCellTypeEnum())  {
	  
					case NUMERIC:    
						lijst.add(String.valueOf(cell.getNumericCellValue()));
						break;  
						
					case STRING:   		
						lijst.add(cell.getStringCellValue());
						break;
						
					default:
						break;  
				}  
			} 
			
			if (teller == 0 && lijst.size()  == 1) eenKolom = true;
			if (!eenKolom) {
				lijstGeheel1.add(lijst);
				lijst =  new ArrayList<>();
			}	
			teller++;
			
		}  
		
		if (!eenKolom) lijst =  new ArrayList<>();
		
		return lijst;
	}
	
	public List<String> leesAfXLSX()  {  
		List<List<String>> meerdereKolommen = new ArrayList<>();
		List<String> enkelKolom = new ArrayList<>();
		
		try  {  
			File file = new File(link);     
			FileInputStream fis = new FileInputStream(file);     
			 
			@SuppressWarnings("resource")
			XSSFWorkbook wb = new XSSFWorkbook(fis);   
			XSSFSheet sheet = wb.getSheetAt(0);      
			Iterator<Row> itr = sheet.iterator();
			
			//xlsx bestaat uit 1 of meerdere kolommen
        	List<List<String>> lijstGeheel1 = new ArrayList<>();
			List<String> lijst = new ArrayList<>();
			
			
			int teller = 0;
			boolean eenKolom = false;
			//aantal lijnen x die je wil lezen -> teller < x (teller >=0 als je alles wil lezen)
			while (itr.hasNext() && teller >=0) {              
		  
				Row row = itr.next();  
				Iterator<Cell> cellIterator = row.cellIterator();     
				
				while (cellIterator.hasNext())  {
		
					Cell cell = cellIterator.next();  
					
					switch (cell.getCellTypeEnum())  {             
		
						case STRING:     
							//System.out.print(cell.getStringCellValue() + "\t\t\t"); 
							lijst.add(cell.getStringCellValue());
							break;  
							
						case NUMERIC:      
							
							lijst.add(String.valueOf(cell.getNumericCellValue()));
							break;  
						default:  
							break;
					}  
				}  
				
				if (teller == 0 && lijst.size()  == 1) eenKolom = true;
				if (!eenKolom) {
					lijstGeheel1.add(lijst);
					lijst =  new ArrayList<>();
				}	
				
				//System.out.println("");  
				teller++;
			}  
			
			if (!eenKolom) lijst =  new ArrayList<>();
				

			//lijst = lijst.stream().filter(e -> !e.matches(".*[a-z].*")).collect(Collectors.toList());
			
            meerdereKolommen = lijstGeheel1;
            enkelKolom = lijst;

			
		}  
		catch(Exception e) { 
			e.printStackTrace();  
		}  
		
		return enkelKolom;
	}
}
