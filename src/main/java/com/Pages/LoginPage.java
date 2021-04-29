package com.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	WebDriver lpdriver;

	public LoginPage(WebDriver driver) {        //Constructor
		lpdriver=driver;
	}

	private WebElement getUserName(){
		return lpdriver.findElement(By.id("email"));
		
	}
	private WebElement getPassword(){
		return lpdriver.findElement(By.id("password"));
		
	}
	private WebElement getLoginButton(){
		return lpdriver.findElement(By.xpath("//button"));
		
	}
	private WebElement getRegisterLink(){
		return lpdriver.findElement(By.partialLinkText("Register"));
		
	}
	
	public void enterUserName(String text){
		getUserName().sendKeys(text);
	}
	public void enterPassword(String text){
		getPassword().sendKeys(text);
	}
	public void clickLoginButton(){
		getLoginButton().click();
	}
	public void clickRegisterLink(){
		getRegisterLink().click();
	}
}
