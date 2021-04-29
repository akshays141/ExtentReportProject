package apachePoi;

import java.io.FileInputStream;


import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class XlsXOnlyExcelPoi {       //reads xlsx file
@Test
public void test() throws Exception{
	String value=null;
	FileInputStream fis=new FileInputStream("Data.xlsx");
	XSSFWorkbook wb=new XSSFWorkbook(fis);
	XSSFSheet sh=wb.getSheet("login");
	int rows=sh.getPhysicalNumberOfRows();
	for(int i=0;i<rows;i++)
	{
		int cols=sh.getRow(i).getLastCellNum();
			for (int j = 0; j <cols; j++)
			{
				XSSFCell cell=sh.getRow(i).getCell(j);
				
				if(cell.getCellTypeEnum() == CellType.STRING)
				value=cell.getStringCellValue();
				
				else if(cell.getCellTypeEnum()==CellType.NUMERIC)
				value = String.valueOf((long)cell.getNumericCellValue());
				
				System.out.print(value+"    ");
			}
			System.out.println();
	}
	wb.close();
	}
}

