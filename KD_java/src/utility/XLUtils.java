package utility;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import config.Constants;
import execEngine.driverScript;

public class XLUtils 
{
	private static XSSFSheet xlWS;
	private static XSSFWorkbook xlWB;
	private static XSSFCell xlCell;
	private static XSSFRow xlRow;
	
	public static void setExcelFile(String xlPath) throws Exception
	{
		try
		{
		FileInputStream xlFile = new FileInputStream(xlPath);
		xlWB = new XSSFWorkbook(xlFile);
		}
		catch (Exception e)
		{
			Log.error("Class: XLUtils | Method: setExcelFile | Exception description: " + e.getMessage());
			driverScript.bResult = false;
		}
	}
	
	public static String getCellData(int RowNum, int ColNum, String SheetName) throws Exception
	{		
		try
		{
			xlWS = xlWB.getSheet(SheetName);
			xlCell = xlWS.getRow(RowNum).getCell(ColNum);
			String CellData = xlCell.getStringCellValue();
			return CellData;
		}
		catch (Exception e)
		{
			Log.error("Class: XLUtils | Method: getCellData | Exception description: " + e.getMessage());
			driverScript.bResult = false;
			return "";
		}
	}
	
	public static int getRowCount(String SheetName)
	{
		int iNumber = 0;
		try
		{
		xlWS = xlWB.getSheet(SheetName);
		iNumber = xlWS.getLastRowNum() + 1;		
		}
		catch (Exception e)
		{
			Log.error("Class: XLUtils | Method: getRowCount | Exception description: " + e.getMessage());
			driverScript.bResult = false;
		}
		
		return iNumber;
	}
	
	public static int getRowContains(String sTestCaseName, int colNum, String SheetName) throws Exception
	{
		int i;
		xlWS = xlWB.getSheet(SheetName);
		int rowCount = XLUtils.getRowCount(SheetName);
		
		for (i = 0; i < rowCount; i++)
		{
			if (XLUtils.getCellData(i, colNum, SheetName).equalsIgnoreCase(sTestCaseName))
			{
				break;
			}
		}
		
		return i;
	}
	
	public static int getTestStepsCount(String SheetName, String sTestCaseID, int iTestCaseStart) throws Exception
	{
		for (int i = iTestCaseStart; i <= XLUtils.getRowCount(SheetName); i++)
		{
			if (!sTestCaseID.equals(XLUtils.getCellData(i, Constants.Col_TestCaseID, SheetName)))
			{
				int number = i;
				return number;
			}
		}
		
		xlWS = xlWB.getSheet(SheetName);
		int number = xlWS.getLastRowNum() + 1;
		return number;
	}
}
