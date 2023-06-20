package com.learnautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	
	protected WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		
	}
	private By newUserLink=By.linkText("New user? Signup");
	
	private By errorMessage=By.className("errorMessage");

	private By username=By.id("email1");

	private By password=By.id("password1");
	
	private By loginButton=By.className("submit-btn");
	
	
	public void  clickOnNewUserLink()
	{
		driver.findElement(newUserLink).click();
		
	}

	public void loginToApplication(String uname,String pass)
	{

		driver.findElement(username).sendKeys(uname);
		driver.findElement(password).sendKeys(pass);
		driver.findElement(loginButton).click();
		
	}
	
	public String captureErrorMessage()
	{
		String error_msg=driver.findElement(errorMessage).getText();
		
		return error_msg;
	}
	
}
