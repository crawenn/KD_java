package config;

import java.util.concurrent.TimeUnit;
import static execEngine.driverScript.OR;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import execEngine.driverScript;
import utility.Log;

public class ActionKeywords {
	
	public static WebDriver driver;
	
	public static void openBrowser(String object)
	{
		try
		{
			Log.info("Opening browser: ");
			driver = new ChromeDriver();
		}
		catch (Exception e)
		{
			Log.info("Could not open browser " + e.getMessage());
			driverScript.bResult = false;
		}
	}
	
	public static void navigate(String object)
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
	
	public static void click(String object)
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
	
	public static void input_Username(String object)
	{
		try
		{
			Log.info("Entering username");
			driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(Constants.UserName);
		}
		catch (Exception e)
		{
			Log.error("Not able to enter username: " + e.getMessage());
			driverScript.bResult = false;
		}
	}
	
	public static void input_Password(String object)
	{
		try
		{
			Log.info("Entering password");
			driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(Constants.Password);
		}
		catch (Exception e)
		{
			Log.error("Not able to enter password: " + e.getMessage());
			driverScript.bResult = false;
		}
	}
	
	public static void waitFor(String object) throws Exception
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
		
	public static void closeBrowser()
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