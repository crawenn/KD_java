package utility;

import java.io.FileInputStream;
import java.nio.file.Path;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtils 
{
	private static XSSFSheet xlWS;
	private static XSSFWorkbook xlWB;
	private static XSSFCell xlCell;
	
	//method to set file path and open it
	public static void setExcelFile(String xlPath, String SheetName) throws Exception
	{
		FileInputStream xlFile = new FileInputStream(xlPath);
		xlWB = new XSSFWorkbook(xlFile);
		xlWS = xlWB.getSheet(SheetName);
	}
	
	//method to read test data
	public static String getCellData(int RowNum, int ColNum) throws Exception
	{
		xlCell = xlWS.getRow(RowNum).getCell(ColNum);
		String CellData = xlCell.getStringCellValue();
		return CellData;
	}
}
