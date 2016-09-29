package com.alt.sundaykart.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.alt.sundaykart.automation.base.Constants;
import com.alt.sundaykart.automation.objectrepository.OR;

public class HomePage {
	WebDriver driver;
	WebDriverWait wait;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Constants.wait_time);
	}

	@FindBy(xpath = OR.cashBackPopupCloseLink)
	WebElement closeHomePagePopUp;
	@FindBy(xpath = OR.loginLink)
	WebElement Login_Click;
	@FindBy(id = OR.userNameTxtField)
	WebElement EnterUserName;
	@FindBy(id = OR.passwordTxtField)
	WebElement EnterPassword;
	@FindBy(id = OR.loginBtn)
	WebElement ClickLoginBTN;
	@FindBy(id = OR.userOptionsDrpdwn)
	WebElement userOptionsDrpdwn;
	@FindBy(xpath = OR.loginPagePopup)
	WebElement loginModalPopUp;
	@FindBy(how = How.XPATH, using = OR.cashBackPopUp)
	WebElement cashBackPopup;

	public void cashBackPopupClose() {
		closeHomePagePopUp.click();

	}

	public void loginSundaykart(String userName, String passWord) {
		wait.until(ExpectedConditions.elementToBeClickable(Login_Click));
		Login_Click.click();
		wait.until(ExpectedConditions.visibilityOf(loginModalPopUp));
		EnterUserName.sendKeys(userName);
		EnterPassword.sendKeys(passWord);
		ClickLoginBTN.click();
	}

	public void waitforUserLogin() {
		wait.until(ExpectedConditions.visibilityOf(cashBackPopup));
	}

	public boolean verifyUserLogin() {
		if (userOptionsDrpdwn.isDisplayed())
			return true;
		else
			return false;
	}

}