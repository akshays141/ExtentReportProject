package com.testcases;

import java.io.FileInputStream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;

public class DataProviderTest {
WebDriver driver;
	
	@BeforeTest
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();// browser open
		driver.get("file:///C:/Users/rajat/Downloads/Selenium%20Softwares/Offline%20Website/Offline%20Website/index.html");
		driver.manage().window().maximize();// to maximize the browser
	}
	
	
	@Test(dataProvider = "loginData")
	public void test01(String uname, String pass) {
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys(uname);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(pass);
		driver.findElement(By.xpath("//button")).click();
		if(driver.getTitle().equals("JavaByKiran | Dashboard"))
			driver.findElement(By.partialLinkText("LOGOUT")).click();
	//log out button click
	}
	
	@DataProvider
	  public Object[][] loginData() throws Exception{
		  	FileInputStream fis = new FileInputStream("Book1.xls");	
			Workbook wb= Workbook.getWorkbook(fis);
			Sheet sh = wb.getSheet("login");
			int row=sh.getRows();
			int col=sh.getColumns();
			String dataArr[][]= new String[row-1][col];
			for(int i=1;i<row;i++) {
				for(int j=0;j<col;j++) {
					String data=sh.getCell(j,i).getContents();	
					dataArr[i-1][j]=data;
				}
			}
			return dataArr; //return of DataProvider method
			
	  }//terminte DataProvider method

}//terminate main method

