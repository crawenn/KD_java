package execEngine;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.nio.file.*;

import config.ActionKeywords;
import utility.XLUtils;

public class driverScript {
	
//	private static WebDriver driver = null;

	public static void main(String[] args) throws Exception
	{			
		Path xlPath = Paths.get("bin\\DataEngine\\DataEngine.xlsx");						
		XLUtils.setExcelFile(xlPath.toString(), "Test Steps");
		
		//hard coded values for now, this loop is reading the values of col 3 (Action Keyword) row by row
		for (int iRow = 1; iRow <= 9; iRow++)
		{
			String sActionKeyword = XLUtils.getCellData(iRow, 3);
			
			//value comparison of excel cells
			
			if (sActionKeyword.equals("openBrowser"))
			{
				ActionKeywords.openBrowser();
			}
			else if (sActionKeyword.equals("navigate"))
			{
				ActionKeywords.navigate();
			}
			else if (sActionKeyword.equals("click_MyAccount"))
			{
				ActionKeywords.click_MyAccount();
			}
			else if (sActionKeyword.equals("input_Username"))
			{
				ActionKeywords.input_Username();
			}
			else if (sActionKeyword.equals("input_Password"))
			{
				ActionKeywords.input_Password();
			}
			else if (sActionKeyword.equals("click_Login"))
			{
				ActionKeywords.click_Login();
			}
			else if (sActionKeyword.equals("waitFor"))
			{
				ActionKeywords.waitFor();
			}
			else if (sActionKeyword.equals("click_Logout"))
			{
				ActionKeywords.click_Logout();
			}
			else if (sActionKeyword.equals("closwBrowser"))
			{
				ActionKeywords.closeBrowser();
			}
		}
	}
}
