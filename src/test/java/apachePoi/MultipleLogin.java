package apachePoi;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class MultipleLogin {
	WebDriver driver;

	public String readCell(String fileName, String sheetName, int row, int col) 
	{
		FileInputStream fis;
		Workbook wb = null;
		Sheet sh =null;
		Cell cell=null;
		
		try {
			fis = new FileInputStream(fileName);
			wb = WorkbookFactory.create(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 sh = wb.getSheet(sheetName);
		 cell=sh.getRow(row).getCell(col);
		 
		 if(cell.getCellTypeEnum()==CellType.STRING)
			 return cell.getStringCellValue();
		 
		 else if(cell.getCellTypeEnum()==CellType.NUMERIC) 
			{
			 String value = String.valueOf((long)cell.getNumericCellValue());
			return value;	
			}
		return sheetName;
	}

	@BeforeSuite
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("file:///C:/Users/rajat/Downloads/Selenium%20Softwares/Offline%20Website/Offline%20Website/index.html");
		driver.manage().window().maximize();
	}

	@Test // Runs both .xls and .xlsx Excel file
	public void loginTest() {
		for(int i=1;i<=3;i++)
		{
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys(readCell("Data.xlsx", "login", i, 0));
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(readCell("Data.xls", "login", i, 1));// 123456
		driver.findElement(By.xpath("//button")).click();
		
		if(driver.getTitle().contains("Dashboard"))
			 driver.findElement(By.linkText("LOGOUT")).click();
	    }
		Assert.assertEquals(driver.getTitle(), "JavaByKiran | Log in");
}
}