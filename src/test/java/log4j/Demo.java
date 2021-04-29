package log4j;



import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import org.testng.Assert;

public class Demo {
	WebDriver driver;
	
	Logger log=Logger.getLogger(Demo.class);
	
	@Test(priority=1)
	public void testLogin(){
		log.info("testCase 1 running");
		log.info("launching chrome driver");
	System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
	driver = new ChromeDriver();
	log.info("launching JBK Application");
	driver.get("file:///C:/Users/rajat/Downloads/Selenium%20Softwares/Offline%20Website/Offline%20Website/index.html");
	log.info("maximizing browser window");
	driver.manage().window().maximize();
	log.info("entering credentials for login");
	driver.findElement(By.id("email")).sendKeys("kiran@gmail.com");
	driver.findElement(By.id("password")).sendKeys("123456");
	driver.findElement(By.xpath("//button")).click();
	log.info("user logged in as kiran");
	Assert.assertEquals(driver.getTitle(), "JavaByKiran | Dashboard");
	}
	
	@Test(priority=2)
	public void lauchUrl(){
		log.info("Running testCase 2:");
		log.info("launching chrome driver");
		try{
			log.warn("chromedriver file should be at specific location");
			System.setProperty("webdriver.chrome.driver", "F:/chromedriver.exe");
			driver = new ChromeDriver();
			
		}catch(Exception e){
			log.error("chromedriver file not found");
		}
		log.info("launching JBK Application");
		
		
		driver.get("file:///C:/Users/rajat/Downloads/Selenium%20Softwares/Offline%20Website/Offline%20Website/index.html");
 	log.info("maximizing browser window");
		driver.manage().window().maximize();
		Assert.assertEquals(driver.getCurrentUrl(), "file:///C:/Users/rajat/Downloads/Selenium%20Softwares/Offline%20Website/Offline%20Website/index.html");
	}
}
	
	
	
	
	
	
	

