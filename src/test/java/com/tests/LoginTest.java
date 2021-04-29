package com.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.Pages.DashboaardPage;
import com.Pages.LoginPage;

public class LoginTest {
	WebDriver driver;
	LoginPage lp = null; /// to connect with loginPage(we need to create object
	DashboaardPage dp = null;

	@BeforeSuite
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();// browser open
		driver.get("file:///C:/Users/rajat/Downloads/Selenium%20Softwares/Offline%20Website/Offline%20Website/index.html");
		driver.manage().window().maximize();// to maximize the browser

		lp = new LoginPage(driver);
		dp=new DashboaardPage(driver);
	}

	@Test(priority=1)
	public void loginTestCase() throws Exception {
		lp.enterUserName("kiran@gmail.com");
		lp.enterPassword("123456");
		lp.clickLoginButton(); //signin button--click--dashboard---LoGOutbutton
		dp.clickLogoutButton();  //LoGOutbutton click--return back to loginpg
		Thread.sleep(7000);
	}

	@Test(priority=2)
	public void registerTest() { //loginpg--Register a new membership
		lp.clickRegisterLink();
	}
}
