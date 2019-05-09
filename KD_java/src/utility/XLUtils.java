package utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.apache.poi.ss.usermodel.Row;

import config.Constants;
import execEngine.driverScript;

public class XLUtils 
{
	private static XSSFSheet xlWS;
	private static XSSFWorkbook xlWB;
	private static XSSFCell xlCell;
	private static XSSFRow xlRow;
	
	public static void setExcelFile() throws Exception
	{
		try
		{
		//FileInputStream xlFile = new FileInputStream(Constants.testDataPath);
		xlWB = new XSSFWorkbook(Constants.testDataPath);
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
		int iRowNum = 0;
		try 
		{
			xlWS = xlWB.getSheet(SheetName);
			int rowCount = XLUtils.getRowCount(SheetName);
			
			for (; iRowNum < rowCount; iRowNum++)
			{
				if (XLUtils.getCellData(iRowNum, colNum, SheetName).equalsIgnoreCase(sTestCaseName))
				{
					break;
				}
			}
		}
		catch (Exception e)
		{
			Log.error("Class: XLUtils | Method: getRowContains | Exception description: " + e.getMessage());
			driverScript.bResult = false;
		}		
		
		return iRowNum;
	}
	
	public static int getTestStepsCount(String SheetName, String sTestCaseID, int iTestCaseStart) throws Exception
	{
		try
		{
			for (int i = iTestCaseStart; i <= XLUtils.getRowCount(SheetName); i++)
			{
				if (!sTestCaseID.equals(XLUtils.getCellData(i, Constants.Col_TestCaseID, SheetName)))
				{
					int number = i;
					//return number;
				}
			}
		
			xlWS = xlWB.getSheet(SheetName);
			int number = xlWS.getLastRowNum()+1;
			return number;
		}
		catch (Exception e)
		{
			Log.error("Class: XLUtils | Method: getTestStepsCount | Exception description: " + e.getMessage());
			driverScript.bResult = false;
			return 0;
		}
	}
	
	//@SuppressWarnings("static-access")
	public static void setCellData(String Result, int RowNum, int ColNum, String SheetName) throws Exception
	{
		try
		{
			xlWS = xlWB.getSheet(SheetName);
			xlRow = xlWS.getRow(RowNum);
			xlCell = xlRow.getCell(ColNum, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
		
			if (xlCell == null)
			{
				xlCell = xlRow.createCell(ColNum);
				xlCell.setCellValue(Result);
			}
			else
			{
				xlCell.setCellValue(Result);
			}
		
			FileOutputStream fileOut = new FileOutputStream(Constants.testDataPath);
			xlWB.write(fileOut);
			fileOut.close();
		
			xlWB = new XSSFWorkbook(new FileInputStream(Constants.testDataPath));
		}
		catch (Exception e)
		{
			driverScript.bResult = false;
		}
	}
}
