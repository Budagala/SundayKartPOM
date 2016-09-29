package com.alt.sundaykart.automation.listeners;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.alt.sundaykart.automation.base.BrowserFactory;
import com.alt.sundaykart.automation.base.Constants;
import com.alt.sundaykart.automation.utility.Log;

public class ScreenShotListener implements ITestListener {

	WebDriver driver = null;
	String filePath = Constants.screenShot_Path;

	public void onTestFailure(ITestResult result) {
		System.out.println("***** Error " + result.getName() + " test has failed *****");
		Log.error("***** Error " + result.getName() + " test has failed *****");
		String methodName = result.getName().toString().trim();
		 driver = BrowserFactory.driver;
		 takeScreenShot(methodName,driver);
	}

	public void takeScreenShot(String methodName,WebDriver driver) {
		// get the driver
	
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(filePath + methodName + ".jpg"));
			System.out.println("***Placed screen shot in " + filePath + " ***");
			Log.info("***Placed screen shot in " + filePath + " ***");
		} catch (IOException e) {
			
			Log.error("Exception occured while taking screenshot:"+e.toString());
			e.printStackTrace();
		}
	}

	public void onFinish(ITestContext context) {
	}

	public void onTestStart(ITestResult result) {
	}

	public void onTestSuccess(ITestResult result) {
	}

	public void onTestSkipped(ITestResult result) {
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	public void onStart(ITestContext context) {
	}

	
}
