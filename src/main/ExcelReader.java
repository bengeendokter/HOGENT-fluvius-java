package main;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelReader {
	/*public static void main(String args[]) throws IOException  {  
		//obtaining input bytes from a file  
		//C:\\demo\\student.xls
		FileInputStream fis=new FileInputStream(new File("src/data/testex.xls"));  
		//creating workbook instance that refers to .xls file  
		@SuppressWarnings("resource")
		HSSFWorkbook wb=new HSSFWorkbook(fis);   
		//creating a Sheet object to retrieve the object  
		HSSFSheet sheet=wb.getSheetAt(0);  
		//evaluating cell type   
		FormulaEvaluator formulaEvaluator=wb.getCreationHelper().createFormulaEvaluator();  
		for(Row row: sheet)  {   //iteration over row using for each loop  
	
			for(Cell cell: row) {    //iteration over cell using for each loop  
	
				switch(formulaEvaluator.evaluateInCell(cell).getCellTypeEnum())  {
	  
					case NUMERIC:   //field that represents numeric cell type  
						//getting the value of the cell as a number  
						System.out.print(cell.getNumericCellValue()+ "\t\t");   
						break;  
						
					case STRING:    //field that represents string cell type  
						//getting the value of the cell as a string  
						System.out.print(cell.getStringCellValue()+ "\t\t");  
						break;
						
					default:
						break;  
				}  
			}  
			System.out.println();  
		}  
	}*/
	
	public static void main(String[] args)  {  
		try  {  
			File file = new File("src/data/gegevens.xlsx");   //creating a new file instance  
			FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file  
			//creating Workbook instance that refers to .xlsx file  
			@SuppressWarnings("resource")
			XSSFWorkbook wb = new XSSFWorkbook(fis);   
			XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object  
			Iterator<Row> itr = sheet.iterator();    //iterating over excel file  
			int teller = 0;
			while (itr.hasNext() && teller < 3) {              
		  
				Row row = itr.next();  
				Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column  
				
				while (cellIterator.hasNext())  {
		
					Cell cell = cellIterator.next();  
					
					switch (cell.getCellTypeEnum())  {             
		
						case STRING:    //field that represents string cell type  
							System.out.print(cell.getStringCellValue() + "\t\t\t");  
							break;  
							
						case NUMERIC:    //field that represents number cell type  
							System.out.print(cell.getNumericCellValue() + "\t\t\t");  
							break;  
						default:  
							break;
					}  
				}  
				System.out.println("");  
				teller++;
			}  
		}  
		catch(Exception e) { 
			e.printStackTrace();  
		}  
	} 
}
