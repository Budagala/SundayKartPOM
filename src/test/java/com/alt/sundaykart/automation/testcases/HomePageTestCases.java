package com.alt.sundaykart.automation.testcases;

import java.lang.reflect.Method;

import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.alt.sundaykart.automation.base.Constants;
import com.alt.sundaykart.automation.base.ExtentManager;
import com.alt.sundaykart.automation.base.TestBase;
import com.alt.sundaykart.automation.base.TestData;
import com.alt.sundaykart.automation.pages.HomePage;
import com.alt.sundaykart.automation.utility.Log;
import com.relevantcodes.extentreports.LogStatus;

public class HomePageTestCases extends TestBase {


	@Test
	public void loginToSundaykart(Method method) {

		try {

			Log.startTestCase(method.getName());
			scTestCases = sundayKartreport.startTest(method.getName(), "Login to SundayKart,Severity:Low");
			scTestCases.log(LogStatus.INFO, "Open the SundayKart URL","SundayKart Home Page is displayed");
			HomePage homepage = PageFactory.initElements(driver, HomePage.class);
			homepage.cashBackPopupClose();
			scTestCases.log(LogStatus.INFO, "Click on close button on CashBackPopup","Successfully Closed the Popup");
			homepage.loginSundaykart(TestData.username, TestData.password);
			homepage.waitforUserLogin();
			Log.info("Login in to sundaykart");
			scTestCases.log(LogStatus.INFO, "Login in to sundaykart");
			scTestCases.log(LogStatus.INFO,"Successfully Login to Sundaykart", scTestCases
					.addScreenCapture(ExtentManager.CaptureScreen(driver, Constants.screenShot_Path + "loginpage")));
			homepage.cashBackPopupClose();
			Assert.assertTrue(homepage.verifyUserLogin());
			scTestCases.log(LogStatus.PASS,"Verify user is login to sundaykart" ,"Test Case is passed");
			

		} catch (Exception e) {

			Log.error("Expection occured while executing test case:loginToSundaykart" + e.toString());
			scTestCases.log(LogStatus.ERROR,scTestCases
					.addScreenCapture(ExtentManager.CaptureScreen(driver, Constants.screenShot_Path + "loginpage")),e.toString());
			org.testng.Assert.fail(e.toString());

		}

	}

}
