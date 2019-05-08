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
	
	private static WebDriver driver = null;
	
	public static Properties OR;
	public static ActionKeywords actionKeywords;
	public static String sActionKeyword;
	public static String sPageObject;
	public static Method method[];
	
	public static int iTestStep;
	public static int iTestLastStep;
	public static String sTestCaseID;
	public static String sRunMode;
	
	public void DriverScript() throws NoSuchMethodException, SecurityException
	{
		actionKeywords = new ActionKeywords();
		method = actionKeywords.getClass().getMethods();
	}

	public static void main(String[] args) throws Exception
	{
		XLUtils.setExcelFile(Constants.testDataPath);
		
		String ORPath = Constants.ORPath;
		
		FileInputStream fs = new FileInputStream(ORPath);
		OR = new Properties(System.getProperties());
		OR.load(fs);
		
		driverScript startEngine = new driverScript();
		startEngine.execute_TestCase();
	}
	
	private void execute_TestCase() throws Exception
	{
		int iTotalTestCases = XLUtils.getRowCount(Constants.Sheet_TestCases);
		
		for (int iTestCase = 1; iTestCase <= iTotalTestCases; iTestCase++)
		{
			sTestCaseID = XLUtils.getCellData(iTestCase,  Constants.Col_TestCaseID, Constants.Sheet_TestCases);
			
			sRunMode = XLUtils.getCellData(iTestCase, Constants.Col_Runmode, Constants.Sheet_TestCases);
			
			if (sRunMode.equals("Yes"))
			{
				iTestStep = XLUtils.getRowContains(sTestCaseID, Constants.Col_TestCaseID, Constants.Sheet_TestSteps);
				
				iTestLastStep = XLUtils.getTestStepsCount(Constants.Sheet_TestSteps, sTestCaseID, iTestStep);
				
				for ( ; iTestStep <= iTestLastStep; iTestStep++)
				{
					sActionKeyword = XLUtils.getCellData(iTestStep, Constants.Col_PageObject, Constants.Sheet_TestSteps);
					sPageObject = XLUtils.getCellData(iTestStep, Constants.Col_PageObject, Constants.Sheet_TestSteps);
					execute_Actions();
				}
			}
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
