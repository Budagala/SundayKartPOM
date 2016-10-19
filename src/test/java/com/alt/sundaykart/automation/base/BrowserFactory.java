package com.alt.sundaykart.automation.base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserFactory {

	public static WebDriver driver; 
	
	public static WebDriver openBrowser(String browserName, String url) {

	
		if (browserName.equalsIgnoreCase("firefox")) {

			System.setProperty("webdriver.gecko.driver", Constants.firefox_browser);
			driver = new FirefoxDriver();
			driver.get(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Constants.wait_time, TimeUnit.SECONDS);

		} else if (browserName.equalsIgnoreCase("chrome")) {

			System.setProperty("webdriver.chrome.driver", Constants.chrome_browser);
			ChromeOptions options =new ChromeOptions();
			options.addArguments("chrome.switches","--disable-extensions");
			driver = new ChromeDriver(options);
			driver.get(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Constants.wait_time, TimeUnit.SECONDS);
			System.out.println("Chrome browser");

		}

		else if (browserName.equalsIgnoreCase("IE")) {

			System.setProperty("webdriver.ie.driver",Constants.ie_browser);
			driver = new InternetExplorerDriver();
			driver.get(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Constants.wait_time, TimeUnit.SECONDS);

		}

		return driver;

	}

}
