package utility;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import config.Constants;

public class XLUtils 
{
	private static XSSFSheet xlWS;
	private static XSSFWorkbook xlWB;
	private static XSSFCell xlCell;
	
	public static void setExcelFile(String xlPath) throws Exception
	{
		FileInputStream xlFile = new FileInputStream(xlPath);
		xlWB = new XSSFWorkbook(xlFile);		
	}
	
	public static String getCellData(int RowNum, int ColNum, String SheetName) throws Exception
	{
		xlWS = xlWB.getSheet(SheetName);
		try
		{
			xlCell = xlWS.getRow(RowNum).getCell(ColNum);
			String CellData = xlCell.getStringCellValue();
			return CellData;
		}
		catch (Exception e)
		{
			return "";
		}
	}
	
	public static int getRowCount(String SheetName)
	{
		xlWS = xlWB.getSheet(SheetName);
		int number = xlWS.getLastRowNum() + 1;
		return number;
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
