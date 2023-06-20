package com.learnautomatiom.factory;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.learnautomatiom.dataProvider.ConfigReader;

public class BrowserFactory 
{
	
	public static WebDriver startBrowser(String browserName,String applicationURL)
	{
			WebDriver driver=null;
			
			if(browserName.equalsIgnoreCase("Chrome") || browserName.equalsIgnoreCase("Google Chrome") || browserName.equalsIgnoreCase("GC"))
			{
				ChromeOptions opt=new ChromeOptions();
				
				if(ConfigReader.getProperty("headless").contains("true"))
				{
					opt.addArguments("--headless=new");	
				}
				
				driver=new ChromeDriver();
			}
			else if(browserName.equalsIgnoreCase("Edge") || browserName.equalsIgnoreCase("Microsoft Edge"))
			{
				driver=new EdgeDriver();
			}
			else if(browserName.equalsIgnoreCase("Firefox") || browserName.equalsIgnoreCase("FF"))
			{
				driver=new FirefoxDriver();
			}
			else
			{
				System.out.println("Sorry we do not support "+browserName + " Browser");
			}
		
			driver.manage().window().maximize();
			
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Integer.parseInt(ConfigReader.getProperty("pageLoad"))));
			
			driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(Integer.parseInt(ConfigReader.getProperty("scriptTimeOut"))));
			
			driver.get(applicationURL);
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(ConfigReader.getProperty("implicitWait"))));
			
			return driver;
	}

}
