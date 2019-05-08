package config;

import java.util.concurrent.TimeUnit;
import static execEngine.driverScript.OR;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import utility.Log;

import org.openqa.selenium.chrome.ChromeDriver;

public class ActionKeywords {
	
	public static WebDriver driver;
	
	public static void openBrowser(String object)
	{
		Log.info("Opening browser: ");
		driver = new ChromeDriver();
	}
	
	public static void navigate(String object)
	{
		Log.info("Navigating to " + Constants.URL);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(Constants.URL);
	}
	
	public static void click(String object)
	{
		Log.info("Clicking on element " + object);
		driver.findElement(By.xpath(OR.getProperty(object))).click();
	}
	
	public static void input_Username(String object)
	{
		Log.info("Entering username");
		driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(Constants.UserName);
	}
	
	public static void input_Password(String object)
	{
		Log.info("Entering password");
		driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(Constants.Password);
	}
	
	/*public static void click_Login(String object)
	{
		driver.findElement(By.xpath(OR.getProperty(object))).click();
	}*/
	
	public static void waitFor(String object) throws Exception
	{
		Log.info("Waiting 5 seconds");
		Thread.sleep(5000);
	}
	
	/*public static void click_Logout()
	{
		driver.findElement(By.xpath(".//*[@id='account_logout']/a")).click();
	}*/
	
	public static void closeBrowser()
	{
		Log.info("Closing browser");
		driver.quit();
	}
}
