package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;



public class ExcelReader {
	public static void main(String args[]) throws IOException  {  
		//testex
		FileInputStream fis=new FileInputStream(new File("src/data/xlsDouble.xls"));  
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
			if (teller >= 10) break;
	
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
		
		System.out.println(Arrays.toString(lijst.toArray()));
        System.out.println("----------");
        System.out.println(Arrays.toString(lijstGeheel1.toArray()));
        
        //kolomnamen verwijderen uit lijst
        lijstGeheel1.remove(0);
        
        //elementen uit lijst nemen met index de kolom
        List<List<Double>> nieuw = lijstGeheel1.stream()
        .map(line -> line.stream().map(Double::parseDouble).collect(Collectors.toList()))
        .collect(Collectors.toList());
       
        List<Double> lijstje = nieuw.stream().map(e -> e.get(1)).collect(Collectors.toList());
        
        System.out.println(lijstje);
	}
	
	/*public static void main(String[] args)  {  
		try  {  
			File file = new File("src/data/xlsxDouble.xlsx");     
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
				

			lijst = lijst.stream().filter(e -> !e.matches(".*[a-z].*")).collect(Collectors.toList());
			

			System.out.println(Arrays.toString(lijst.toArray()));
            System.out.println("----------");
            System.out.println(Arrays.toString(lijstGeheel1.toArray()));
            
            //kolomnamen verwijderen uit lijst
            lijstGeheel1.remove(0);
            
            //elementen uit lijst nemen met index de kolom
            List<List<Double>> nieuw = lijstGeheel1.stream()
            .map(line -> line.stream().map(Double::parseDouble).collect(Collectors.toList()))
            .collect(Collectors.toList());
           
            List<Double> lijstje = nieuw.stream().map(e -> e.get(1)).collect(Collectors.toList());
            
            System.out.println(lijstje);

		}  
		catch(Exception e) { 
			e.printStackTrace();  
		}  
	} */
}
