package apachePoi;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPoiExcel {
	@Test(dataProvider="getExcel")
	public void test(String uname,String pass){
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("file:///C:/Users/rajat/Downloads/Selenium%20Softwares/Offline%20Website/Offline%20Website/index.html");
		driver.findElement(By.id("email")).sendKeys(uname);
		driver.findElement(By.id("password")).sendKeys(pass);
		driver.findElement(By.xpath("//button")).click();	
	}
	@DataProvider
	public Object[][] getExcel() throws Exception {
		String data=null;
		FileInputStream fis=new FileInputStream("Data.xlsx");
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		XSSFSheet sh=wb.getSheet("login");
		
		int rows=sh.getPhysicalNumberOfRows();
		String arr[][]=new String[rows-1][sh.getRow(rows-1).getLastCellNum()];
		for(int i=1;i<rows;i++)
		{
			int cols=sh.getRow(i).getLastCellNum();
				for (int j = 0; j <cols; j++)
				{  XSSFCell cell=sh.getRow(i).getCell(j);
					
					if(cell.getCellTypeEnum() == CellType.STRING)
					data=cell.getStringCellValue();
					
					else if(cell.getCellTypeEnum()==CellType.NUMERIC)
					data=String.valueOf(cell.getNumericCellValue());
					
					arr[i-1][j]=data;
				}
		}
		wb.close();
		return arr;
		}
	
		
	}

