package com.alt.sundaykart.automation.testcases;

import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.alt.sundaykart.automation.base.BrowserFactory;
import com.alt.sundaykart.automation.base.Constants;
import com.alt.sundaykart.automation.base.ExtentManager;
import com.alt.sundaykart.automation.pages.HomePage;
import com.alt.sundaykart.automation.utility.Log;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class HomePageTestCases {

	public WebDriver driver;
	ExtentReports sundayKartreport;
	ExtentTest scTestCases;

	@BeforeSuite
	public void setUpSundayKart() {

		sundayKartreport = ExtentManager.instance();
		DOMConfigurator.configure(Constants.logConfigxmlPath);
   }

	@Test
	public void loginToSundaykart() {

		try {

			Log.startTestCase("loginToSundaykart");
			scTestCases = sundayKartreport.startTest("loginToSundaykart", "Login to SundayKart");
			scTestCases.log(LogStatus.INFO, "Opened the sundaykarturl in firefox browser");
			driver = BrowserFactory.openBrowser("firefox", Constants.project_url);
			HomePage homepage = PageFactory.initElements(driver, HomePage.class);
			homepage.cashBackPopupClose();
			scTestCases.log(LogStatus.INFO, "closed the Cashback popup close");
			homepage.loginSundaykart("saihari521@gmail.com", "sai");
			homepage.waitforUserLogin();
			Log.info("Login in to sundaykart");
			scTestCases.log(LogStatus.INFO, "Login in to sundaykart");
			scTestCases.log(LogStatus.INFO, scTestCases
					.addScreenCapture(ExtentManager.CaptureScreen(driver, Constants.screenShot_Path + "loginpage")));
			homepage.cashBackPopupClose();
			Assert.assertTrue(homepage.verifyUserLogin());
			scTestCases.log(LogStatus.PASS, "Test Case is passed");

		} catch (Exception e) {

			Log.error("Expection occured while executing test case:loginToSundaykart" + e.toString());
			scTestCases.log(LogStatus.ERROR, e.toString());
			org.testng.Assert.fail(e.toString());

		}

	}

	@AfterMethod
	public void closeTestCase(ITestResult result) {
		sundayKartreport.endTest(scTestCases);
		Log.endTestCase(result.getName());

	}

	@AfterSuite
	public void closeBrowser() {
		sundayKartreport.flush();
		sundayKartreport.close();

	}

}
