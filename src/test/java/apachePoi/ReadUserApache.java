package apachePoi;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ReadUserApache {
	WebDriver driver;
	int row=1;
	@BeforeSuite
	public void launchUserPage() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("file:///C:/Users/rajat/Downloads/Selenium%20Softwares/Offline%20Website/Offline%20Website/index.html");
		driver.findElement(By.id("email")).sendKeys("kiran@gmail.com");
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.xpath("//button")).click();
		driver.findElement(By.xpath("//span[text()='Users']")).click();
	}
	@Test(dataProvider="userData")
	public void verifyUserTable(String sr, String name, String  email, String mobile, 
			String course, String gender, String state, String action ) {
		ArrayList<String> expData = new ArrayList<String>();
		expData.add(sr);
		expData.add(name);
		expData.add(email);
		expData.add(mobile);
		expData.add(course);
		expData.add(gender);
		expData.add(state);
		expData.add(action);	
		ArrayList<String> actData = new ArrayList<String>();
		if (row==1) {
			List<WebElement> headers = driver.findElements(By.tagName("th"));// 8 element
			for (WebElement element : headers) {
				String text = element.getText();
				actData.add(text);
			}
			row++;
		}
		else {
			List<WebElement> rowData = driver.findElements(By.xpath("//tr["+row+"]/td"));// 8 element
			for (WebElement element : rowData) {
				String text = element.getText();
				actData.add(text);
		}
			row++;
		}	
		Assert.assertEquals(actData, expData);
	}
	
	@DataProvider
	public String[][] userData() throws Exception {
		FileInputStream fis = new FileInputStream("Data.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sh=wb.getSheet("user");
		int rows=sh.getPhysicalNumberOfRows();
		String data[][]=new String[rows][sh.getRow(rows-1).getLastCellNum()];	
		
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<sh.getRow(i).getLastCellNum();j++)
			{
				Cell cell= sh.getRow(i).getCell(j);	
				//System.out.println(cell.toString()+" ");
				if(cell.getCellTypeEnum()==CellType.STRING)
					data[i][j]=cell.getStringCellValue();
					
					else if(cell.getCellTypeEnum()==CellType.NUMERIC)             //double
					{           
						String value = String.valueOf((long)cell.getNumericCellValue());
						data[i][j]=value;
					}
					
					
			}//for2
		}//for1
return data;
}//
}
