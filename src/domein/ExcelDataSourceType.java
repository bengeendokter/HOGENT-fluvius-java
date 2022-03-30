package domein;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Entity
@DiscriminatorValue("E")
public class ExcelDataSourceType extends TypeDatasource implements Serializable  {
	private static final long serialVersionUID = 1L;
	private String link;
	
	protected ExcelDataSourceType() {
		
	}
	
	//exception weg omdat print alleen voor te testen was
	public ExcelDataSourceType(String link) throws IOException {
		setLink(link);
		//System.out.println(Arrays.toString(getData(1).toArray()));
		//System.out.println(getData(1));
	}
	
	public void setLink(String link)
	{
		if (link == null || link.isBlank() || link.isEmpty()) {
			throw new IllegalArgumentException("De link moet ingevuld zijn");
		}
		this.link = link;
	}
	
	public String getLink() {
		return link;
	}
	
	public String getHostname() {
		return "geen hostname";
	}
	
	public String getUsername() {
		return "geen username";
	}
	
	public String getPassword() {
		return "geen password";
	}
	
	//List<Double>
	public List<Double> getData(int kolom) throws IOException {
		if (link.charAt(link.length()-1) == 'x') {
			return leesAfXLSX(kolom);
		} 
		return leesAfXLS(kolom);
		/*List<List<Double>> li = new ArrayList<>();
		li.add(Arrays.asList(9.9, 5.6, 7.8));
		li.add(Arrays.asList(5.6, 7.8, 8.6));
		li.add(Arrays.asList(3.4, 3.3, 7.8));
		
		return li.stream().map(e -> e.get(kolom-1)).collect(Collectors.toList());*/
	}
	
	public List<Double> leesAfXLS(int kolom) throws IOException  {  
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
		/*List<Double> lijst1 = lijst.stream().filter(e -> !e.matches(".*[a-z].*")).map(e -> Double.parseDouble(e)).collect(Collectors.toList());
		return lijst1;*/
		
		//kolomnamen verwijderen uit lijst
		lijstGeheel1.remove(0);
        
        //elementen uit lijst nemen met index de kolom
        List<List<Double>> nieuw = lijstGeheel1.stream()
        .map(line -> line.stream().map(Double::parseDouble).collect(Collectors.toList()))
        .collect(Collectors.toList());
       
        List<Double> lijstje = nieuw.stream().map(e -> e.get(kolom)).collect(Collectors.toList());
        
        //System.out.println(lijstje);
        return lijstje;
	}
	
	@SuppressWarnings("unused")
	public List<Double> leesAfXLSX(int kolom)  {  
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
		/*List<Double> lijst1 = enkelKolom.stream().filter(e -> !e.matches(".*[a-z].*")).map(e -> Double.parseDouble(e)).collect(Collectors.toList());
		return lijst1;*/
		
		//kolomnamen verwijderen uit lijst
		meerdereKolommen.remove(0);
        
        //elementen uit lijst nemen met index de kolom
        List<List<Double>> nieuw = meerdereKolommen.stream()
        .map(line -> line.stream().map(Double::parseDouble).collect(Collectors.toList()))
        .collect(Collectors.toList());
       
        List<Double> lijstje = nieuw.stream().map(e -> e.get(kolom)).collect(Collectors.toList());
        
        //System.out.println(lijstje);
        return lijstje;
	}
	
	@Override
	public String toString() {
		return "excel";
	}

	@Override
	public int geefKolomLengte() throws IOException {
		if (link.charAt(link.length()-1) == 'x') {
			return geefKolomLengteXLSX();
		} 
		return geefKolomLengteXLS();
	}

	private int geefKolomLengteXLS() throws IOException {
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
		/*List<Double> lijst1 = lijst.stream().filter(e -> !e.matches(".*[a-z].*")).map(e -> Double.parseDouble(e)).collect(Collectors.toList());
		return lijst1;*/
		
		//kolomnamen verwijderen uit lijst
		return lijstGeheel1.get(0).size();
	}

	private int geefKolomLengteXLSX() {
		List<List<String>> meerdereKolommen = new ArrayList<>();
//		List<String> enkelKolom = new ArrayList<>();
		
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
//            enkelKolom = lijst;

			
		}  
		catch(Exception e) { 
			e.printStackTrace();  
		}  
		/*List<Double> lijst1 = enkelKolom.stream().filter(e -> !e.matches(".*[a-z].*")).map(e -> Double.parseDouble(e)).collect(Collectors.toList());
		return lijst1;*/
		
		//kolomnamen verwijderen uit lijst
		return meerdereKolommen.get(0).size();
	}
}
