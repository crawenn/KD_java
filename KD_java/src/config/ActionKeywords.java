package config;

import java.util.concurrent.TimeUnit;
import static execEngine.driverScript.OR;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import execEngine.driverScript;
import utility.Log;

public class ActionKeywords {
	
	public static WebDriver driver;
	
	public static void openBrowser(String object, String data)
	{
		try
		{
			if (data.equals("Mozilla"))
			{
				driver = new FirefoxDriver();
				Log.info("Starting Mozilla Firefox");
			}
			else if (data.equals("Chrome"))
			{
				driver = new ChromeDriver();
				Log.info("Starting Google Chrome");
			}
			else if (data.equals("IE"))
			{
				driver = new InternetExplorerDriver();
				Log.info("Starting Internet Explorer");
			}
			
			int implicitWaitTime = (10);
			driver.manage().timeouts().implicitlyWait(implicitWaitTime, TimeUnit.SECONDS);
		}
		catch (Exception e)
		{
			Log.info("Could not open browser " + e.getMessage());
			driverScript.bResult = false;
		}
	}
	
	public static void navigate(String object, String data)
	{
		try
		{
			Log.info("Navigating to " + Constants.URL);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get(Constants.URL);
		}
		catch (Exception e)
		{
			Log.error("Not able to navigate to " + e.getMessage());
			driverScript.bResult = false;
		}
	}
	
	public static void click(String object, String data)
	{
		try
		{
			Log.info("Clicking on element " + object);
			driver.findElement(By.xpath(OR.getProperty(object))).click();
		}
		catch (Exception e)
		{
			Log.error("Not able to click " + e.getMessage());
			driverScript.bResult = false;
		}
	}
	
	public static void input(String object, String data)
	{
		try
		{
			Log.info("Entering text into: " + object);
			driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(data);
		}
		catch (Exception e)
		{
			Log.error("Not able to input to " + object + ": " + e.getMessage());
			driverScript.bResult = false;
		}
	}	
	
	public static void waitFor(String object, String data) throws Exception
	{
		try
		{
			Log.info("Waiting 5 seconds");
			Thread.sleep(5000);
		}
		catch (Exception e)
		{
			Log.error("Could not wait: " + e.getMessage());
			driverScript.bResult = false;
		}
	}
		
	public static void closeBrowser(String object, String data)
	{
		try
		{
			Log.info("Closing browser");
			driver.quit();
		}
		catch (Exception e)
		{
			Log.error("Not able to close browser: " + e.getMessage());
			driverScript.bResult = false;
		}
	}
}