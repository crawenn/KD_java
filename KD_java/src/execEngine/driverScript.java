package execEngine;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.nio.file.*;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Properties;

import config.ActionKeywords;
import config.Constants;
import utility.XLUtils;

public class driverScript {
	
//	private static WebDriver driver = null;
	
	public static Properties OR;
	public static ActionKeywords actionKeywords;
	public static String sActionKeyword;
	public static String sPageObject;
	public static Method method[];
	
	public void DriverScript() throws NoSuchMethodException, SecurityException
	{
		actionKeywords = new ActionKeywords();
		method = actionKeywords.getClass().getMethods();
	}

	public static void main(String[] args) throws Exception
	{			
		String xlPath = Constants.testDataPath;
		XLUtils.setExcelFile(xlPath.toString(), Constants.Sheet_TestSteps);
		
		String ORPath = Constants.ORPath;
		
		FileInputStream fs = new FileInputStream(ORPath);
		
		OR = new Properties(System.getProperties());
		OR.load(fs);
		
		//hard coded values for now, this loop is reading the values of col 3 (Action Keyword) row by row
		for (int iRow = 1; iRow <= 9; iRow++)
		{
			sActionKeyword = XLUtils.getCellData(iRow, Constants.Col_ActionKeyword);
			sPageObject = XLUtils.getCellData(iRow, Constants.Col_PageObject);
			
			execute_Actions();			
		}
	}
	
	public static void execute_Actions() throws Exception
	{
		for (int i = 0; i < method.length; i++)
		{
			if (method[i].getName().equals(sActionKeyword))
			{
				method[i].invoke(actionKeywords, sPageObject);
				break;
			}
		}
	}
}
