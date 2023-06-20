package com.learnautomatiom.helper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

public class Utility {
	
	
	
	public static WebElement waitForWebElement(WebDriver driver,By locator)
	{
		WebElement element=null;
		for(int i=0;i<30;i++)
		{
			
			try 
			{
				element=driver.findElement(locator);
				
				if(element.isDisplayed() && element.isEnabled())
				{
					highlightWebElement(driver, locator);
				}
			} catch (Exception e) 
			{	
				System.out.println("Waiting for element conditions to be checked");
				wait(1);
			}
			
		}
			
		return element;
			
	}
	
	public static WebElement waitForWebElement(WebDriver driver,By locator,int time)
	{
		WebElement element=null;
		for(int i=0;i<time;i++)
		{
			
			try 
			{
				element=driver.findElement(locator);
				
				if(element.isDisplayed() && element.isEnabled())
				{
					highlightWebElement(driver, locator);
				}
			} catch (Exception e) 
			{	
				System.out.println("Waiting for element conditions to be checked");
				wait(1);
			}
			
		}
			
		return element;
			
	}
	
	
	

	
	public static void wait(int second)
	{
		
		try 
		{
			Thread.sleep(second*1000);
		} catch (InterruptedException e) {
			
			System.out.println("Something Went Wrong");
		}
		
		
	}
	
	

	
	public static WebElement highlightWebElement(WebDriver driver,By locator)
	{
		
		WebElement ele=driver.findElement(locator);
		
		JavascriptExecutor js=(JavascriptExecutor)driver;

		js.executeScript("arguments[0].setAttribute('style','background: yellow; border: solid 2px red')",ele);

		Utility.wait(1);
		
		js.executeScript("arguments[0].setAttribute('style','border: solid 2px black')",ele);

		
		return ele;
	}
		
	public static String getCurrentDate()
	{
		SimpleDateFormat myformat=new SimpleDateFormat("HH_mm_ss_dd_MM_yyyy");
		
		String newFormat=myformat.format(new Date());
		
		return newFormat;
	}
	
	public static String getCurrentDateNew()
	{
		return new SimpleDateFormat("HH_mm_ss_dd_MM_yyyy").format(new Date());
	}
	
	
	/*
	 * 	 This method will capture the WebElement Screenshot
	 *   @param - driver reference
	 *   @param - WebElement reference for which we need to take screenshot
	 */
	public static void captureScreenshotOfWebElement(WebElement ele)
	{
		
		File src=ele.getScreenshotAs(OutputType.FILE);
		
		try 
		{
			FileHandler.copy(src, new File("./screenshot/WebElement_"+Utility.getCurrentDate()+".png"));
			
		} catch (IOException e) {
			
			System.out.println("Can not take screenshot of WebElement");
		}
		
	}
	
	
	
	
	public static void captureScreenshot(WebDriver driver)
	{
		
		try 
		{
			FileHandler.copy(((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE), new File("./screenshot/screenshot"+getCurrentDate()+".png"));
			
		} catch (IOException e) {
			
			System.out.println("Exception "+e.getMessage());
		}
		
		
	}
	
	/*
	 * 
	 * 	This method will wait till 15 second max for alert to appear
	 *  @param - WebDriver instance
	 *  @return - Alert interface reference
	 * 
	 */
	public static Alert waitForAlert(WebDriver driver)
	{
			
		Alert alt=null;
		
		for(int i=0;i<15;i++)
		{
			
			
			try 
			{
				alt=driver.switchTo().alert();

				break;
			}
			catch(NoAlertPresentException e)
			{
				try 
				{
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					
				}
				
				System.out.println("Alert Not Found - Retrying");
			}
			
			
		}
		
		return alt;
		
	}
	
	/*
	 * 
	 * 	This method will wait till the time that specified in parameter for alert to appear
	 *  @param - WebDriver instance
	 *  @param - time in seconds
	 *  @return - Alert interface reference
	 * 
	 */
	public static Alert waitForAlert(WebDriver driver,int seconds)
	{
			
		Alert alt=null;
		
		for(int i=0;i<seconds;i++)
		{
			
			
			try 
			{
				alt=driver.switchTo().alert();

				break;
			}
			catch(NoAlertPresentException e)
			{
				try 
				{
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					
				}
				
				System.out.println("Alert Not Found - Retrying");
			}
			
			
		}
		
		return alt;
		
	}
	

	
	public static WebDriver startBrowser(String browserName,String applicationURL)
	{
			WebDriver driver=null;
			
			if(browserName.equalsIgnoreCase("Chrome") || browserName.equalsIgnoreCase("Google Chrome") || browserName.equalsIgnoreCase("GC"))
			{
				//Create a map to store  preferences 
				Map<String, Object> prefs = new HashMap<String, Object>();
				    
				//add key and value to map as follow to switch off browser notification
				//Pass the argument 1 to allow and 2 to block
				prefs.put("profile.default_content_setting_values.notifications", 2);
				    
				//Create an instance of ChromeOptions 
				ChromeOptions options = new ChromeOptions();
				    
				// set ExperimentalOption - prefs 
				options.setExperimentalOption("prefs", prefs);
				    
				System.setProperty("webdriver.chrome.logfile", "chromelog.txt");
				
				driver=new ChromeDriver(options);
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
			
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
			
			driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(10));
			
			driver.get(applicationURL);
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			
			return driver;
	}
	
	
	
	
	
	public static void selectValueFromList(WebDriver driver,String xpathValue,String elementToSearch) 
	{
		
		List<WebElement> allValues=driver.findElements(By.xpath(xpathValue));
		
		for(WebElement ele:allValues)
		{
			if(ele.getText().contains(elementToSearch)) {
				ele.click();
				break;
			}
		}
	
	}
	
	public static void selectValueFromList(WebDriver driver,By locator,String elementToSearch) 
	{
		
		List<WebElement> allValues=driver.findElements(locator);
		
		for(WebElement ele:allValues)
		{
			if(ele.getText().contains(elementToSearch)) {
				ele.click();
				break;
			}
		}
	
	}

}
