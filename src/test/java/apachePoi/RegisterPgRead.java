package apachePoi;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RegisterPgRead {
	WebDriver driver;
	
	@BeforeSuite
	public void setUp(){
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("file:///C:/Users/rajat/Downloads/Selenium%20Softwares/Offline%20Website/Offline%20Website/index.html");
		driver.manage().window().maximize();
		driver.findElement(By.partialLinkText("Register")).click();
	}
	
	@Test(dataProvider="getRegisterData")
	public void registerTest(String name,String mob,String email,String pass){
		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys(name);
		driver.findElement(By.id("mobile")).clear();
		driver.findElement(By.id("mobile")).sendKeys(mob);
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(pass);
		driver.findElement(By.xpath("//button")).click();
		Alert al=driver.switchTo().alert();
		al.accept();
		
	}//testcase end
	@DataProvider
	public String[][] getRegisterData()throws Exception{
	
		FileInputStream fis = new FileInputStream("Data.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("register");
		int row=sh.getPhysicalNumberOfRows();//4
		String [][]data = new String[row-1][sh.getRow(row-1).getLastCellNum()];
		for(int i=1;i<row;i++) 
		{
			int col =sh.getRow(i).getLastCellNum();
			for(int j=0;j<col;j++)  
			{
				Cell cell= sh.getRow(i).getCell(j);
					if(cell.getCellTypeEnum()==CellType.STRING)
						data[i-1][j]=cell.getStringCellValue();
				
					 if(cell.getCellTypeEnum()==CellType.NUMERIC) 
					 {
						 String value = String.valueOf((long)cell.getNumericCellValue());
						 data[i-1][j]=value;
					  }
					
			}
		}
		return data;
	}//Pro end
}//classend