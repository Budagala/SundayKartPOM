package com.alt.sundaykart.automation.testcases;

import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.alt.sundaykart.automation.base.Constants;
import com.alt.sundaykart.automation.base.ExtentManager;
import com.alt.sundaykart.automation.base.TestBase;
import com.alt.sundaykart.automation.pages.HomePage;
import com.alt.sundaykart.automation.utility.Log;
import com.relevantcodes.extentreports.LogStatus;

public class HomePageTestCases extends TestBase {


	@Test
	public void loginToSundaykart() {

		try {

			Log.startTestCase("loginToSundaykart");
			scTestCases = sundayKartreport.startTest("loginToSundaykart", "Login to SundayKart");
			scTestCases.log(LogStatus.INFO, "Opened the sundaykarturl in firefox browser");
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

}
