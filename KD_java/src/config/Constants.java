package config;

import java.io.FileInputStream;

import utility.XLUtils;

public class Constants 
{
	public static final String URL = "http://store.demoqa.com";
	public static final String testDataPath = "C:\\Users\\taracz\\KD_java\\KD_java\\bin\\dataEngine\\DataEngine.xlsx";
	public static final String ORPath = "C:\\Users\\taracz\\KD_java\\KD_java\\bin\\config\\OR.txt";
	public static final String testDataFile = "DataEngine.xlsx";
	
	public static final int Col_TestCaseID = 0;
	public static final int Col_TestScenarioID = 1;
	public static final int Col_PageObject = 3;
	public static final int Col_ActionKeyword = 4;
	
	public static final String Sheet_TestSteps = "Test Steps";
	
	public static final String UserName = "testuser_3";
	public static final String Password = "Test@123";
}
