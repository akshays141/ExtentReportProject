package com.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReportDemo {
	
	ExtentReports extent;
	ExtentTest logger;
	WebDriver driver;
	
	@BeforeTest
	public void setup(){
	extent=new ExtentReports(System.getProperty("user.dir")+"/test-output/Report.html");
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();// browser open
		driver.get("file:///C:/Users/rajat/Downloads/Selenium%20Softwares/Offline%20Website/Offline%20Website/index.html");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.findElement(By.id("email")).sendKeys("kiran@gmail.com");
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.xpath("//button")).click();
		}
	
	@Test
	public void getTitle_Fail(){
	logger=extent.startTest("getTitle_Fail");
	String title=driver.getTitle();
	Assert.assertEquals(title, "no title");
	}
	@Test
	public void getTitle_Pass(){
		logger=extent.startTest("getTitle_Pass");
		String title=driver.getTitle();
		Assert.assertEquals(title, "JavaByKiran | Dashboard");	
	}
	@Test
	public void getTitle_Skip(){
		logger=extent.startTest("getTitle_Skip");
		throw new SkipException("skipping a testcase");
	}
	
	@AfterMethod
	public void checkResult(ITestResult result){
		if(result.getStatus()==ITestResult.FAILURE){
			logger.log(LogStatus.FAIL, "title is not matching");
		String path =TakeScreenShotEx.getScreenshot(driver,"getTitle_Fail");
		logger.log(LogStatus.FAIL,logger.addScreenCapture(path));
		}
		if(result.getStatus()==ITestResult.SKIP)
			logger.log(LogStatus.SKIP,"testcase is skipped");
		if(result.getStatus()==ITestResult.SUCCESS)
			logger.log(LogStatus.PASS, "testcase is passed");
		
		extent.endTest(logger);
	}
	@AfterTest
	public void tearDown(){
		extent.flush();
		extent.close();
		driver.close();
	}
	
}

