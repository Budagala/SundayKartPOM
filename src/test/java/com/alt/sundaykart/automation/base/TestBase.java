package com.alt.sundaykart.automation.base;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.alt.sundaykart.automation.utility.ExcelUtilities;
import com.alt.sundaykart.automation.utility.Log;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class TestBase {
	
	protected WebDriver driver;
	protected ExtentReports sundayKartreport;
	protected ExtentTest scTestCases;
	
	@Parameters({"browser"})
	@BeforeClass
	public void globalSetUP(String browser)
	{
		sundayKartreport = ExtentManager.instance();	
		driver = BrowserFactory.openBrowser(browser, Constants.project_url);
		ExcelUtilities.setExcelFile(Constants.testDataFilePath, "TestData");
	}
	
	@BeforeMethod
	public void setUP()
	{
	
		DOMConfigurator.configure(Constants.logConfigxmlPath);	
		
	}
	
	@AfterMethod
	public void afterTestCompletes(ITestResult result)
	{
		sundayKartreport.endTest(scTestCases);
		Log.endTestCase(result.getName());

	}
	
	@AfterClass
	public void closeBrowser() {
		sundayKartreport.flush();
		sundayKartreport.close();
		driver.quit();

	}

}
