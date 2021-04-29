package apachePoi;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class AddUser {
WebDriver driver;
	
	@BeforeSuite
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("file:///C:/Users/rajat/Downloads/Selenium%20Softwares/Offline%20Website/Offline%20Website/pages/examples/add_user.html");
		driver.manage().window().maximize();
		
	}
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
	
	@Test
	public void addUserWithMultipleEntry(){
		for (int i = 1; i<=4; i++) {                        //rows
			driver.findElement(By.id("username")).clear();
			driver.findElement(By.id("username")).sendKeys(readCell("Data.xlsx", "adduser",i,0));
			driver.findElement(By.id("mobile")).clear();
			driver.findElement(By.id("mobile")).sendKeys(readCell("Data.xlsx", "adduser", i,1));
			driver.findElement(By.id("email")).clear();
			driver.findElement(By.id("email")).sendKeys(readCell("Data.xlsx", "adduser", i,2));
			driver.findElement(By.id("course")).clear();
			driver.findElement(By.id("course")).sendKeys(readCell("Data.xlsx", "adduser",i,3));
			WebElement femaleradio=driver.findElement(By.id("Female"));
			WebElement maleradio=driver.findElement(By.id("Male"));
			if(readCell("Data.xlsx", "adduser",i,4).equals("Male"))
				maleradio.click();
				else
					femaleradio.click();
			
			Select state=new Select(driver.findElement(By.tagName("select")));
			state.selectByVisibleText(readCell("Data.xlsx", "adduser",i,5));
			
			driver.findElement(By.id("password")).clear();
			driver.findElement(By.id("password")).sendKeys(readCell("Data.xlsx","adduser",i,6));
			
			driver.findElement(By.xpath("//button")).click();
			Alert al=driver.switchTo().alert();
			Assert.assertEquals(al.getText(),"User Added Successfully. You can not see added user.");
			al.accept();
		}
	}
}
