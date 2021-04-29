package com.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DashboaardPage {
public DashboaardPage(WebDriver driver) {
		
		dpdriver = driver;
	}

WebDriver dpdriver;

//logout button
private WebElement getLogoutButton(){
	return dpdriver.findElement(By.partialLinkText("LOGOUT"));
	}

public void clickLogoutButton(){
	getLogoutButton().click();	
}
}
