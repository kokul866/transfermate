package com.qa.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.qa.base.DriverManager;
import com.qa.locators.Locators;
import com.qa.utilities.TestUtility;
import com.qa.utilities.emailActivationHelper;

public class PageObjects extends Locators {
	public static PageObjects instance = null;

	public PageObjects() {

	}

	public static PageObjects getInstance() {
		if (instance == null) {
			instance = new PageObjects();
		}
		return instance;
	}

	public void enterFirstName(String firstName) {
		TestUtility.sendKeys(firstNameInputbox, firstName);
	}

	public void enterLastName(String lastName) {
		TestUtility.sendKeys(lastNameInputbox, lastName);
	}

	public void enterEmail(String emailID) {
		TestUtility.sendKeys(emailInputBox, emailID);
	}

	public void enterMobile(String mobile) {
		TestUtility.sendKeys(mobileInputBox, mobile);
	}

	public void enterCaptcha(String val) {
		TestUtility.sendKeys(captchaInputBox, val);
	}

	public void clickOpenAccount() {
		TestUtility.clickOn(openAccountBtn);
	}

	public void checkPrivacyPolicy() {
		TestUtility.clickOn(privacyPolicyCheckBox);
	}

	public void selectCountry(String Country) {
		TestUtility.selectValueFromDropDownByText(countrydrpdwn, Country);
	}

	public void selectMobileDailCode(String code) {
		TestUtility.selectValueFromDropDownByValue(mobileDailCode, code);
	}

	public boolean errorMessageIsDisplayed(String fieldName) {
		Boolean flag = null;
		if (fieldName.equalsIgnoreCase("firstName")) {
			TestUtility.moveToElement(firstNameInputbox);
			flag = TestUtility.isElementDisplayed(firstNameErrorMessage);
		} else if (fieldName.equalsIgnoreCase("lastName")) {
			TestUtility.moveToElement(lastNameInputbox);
			flag = TestUtility.isElementDisplayed(lastNameErrorMessage);
		} else if (fieldName.equalsIgnoreCase("email")) {
			TestUtility.moveToElement(emailInputBox);
			flag = TestUtility.isElementDisplayed(emailErrorMessage);
		} else if (fieldName.equalsIgnoreCase("mobile")) {
			TestUtility.moveToElement(mobileInputBox);
			flag = TestUtility.isElementDisplayed(mobilePhoneErrorMessage);
		} else if (fieldName.equalsIgnoreCase("captcha")) {
			TestUtility.moveToElement(captchaInputBox);
			flag = TestUtility.isElementDisplayed(captchaErrorMessage);
		}
		else if (fieldName.equalsIgnoreCase("accountType")) {
			flag = TestUtility.isElementDisplayed(AccountTypeErrorMessage);	
		}
		else if(fieldName.equalsIgnoreCase("InvalidCaptcha"))
			flag = TestUtility.isElementDisplayed(InvalidCaptchaErrorMessage);
		else
			System.out.println("fieldName does not exisit in sign up page");
		return flag;

	}

	public String getErrorMessage(String fieldName) {
		String errortext = null;
		if (fieldName.equalsIgnoreCase("firstName"))
			errortext = TestUtility.getText(firstNameErrorMessage);
		else if (fieldName.equalsIgnoreCase("lastName"))
			errortext = TestUtility.getText(lastNameErrorMessage);
		else if (fieldName.equalsIgnoreCase("email"))
			errortext = TestUtility.getText(emailErrorMessage);
		else if (fieldName.equalsIgnoreCase("mobile"))
			errortext = TestUtility.getText(mobilePhoneErrorMessage);
		else if (fieldName.equalsIgnoreCase("captcha"))
			errortext = TestUtility.getText(captchaErrorMessage);
		else if (fieldName.equalsIgnoreCase("accountType"))
			errortext = TestUtility.getText(AccountTypeErrorMessage);
		else if(fieldName.equalsIgnoreCase("InvalidCaptcha"))
			errortext = TestUtility.getText(InvalidCaptchaErrorMessage);
		else
			System.out.println("fieldName does not exisit in sign up page");
	
		return errortext;

	}

	public int getCaptcha() {
		int value = 0;
		String text = TestUtility.getText(captchaQuestion).split("=")[0].trim();
		text = text.replaceAll("\\s+", "");  
		if(text.contains("-")) {
			value = Integer.parseInt(text.split("-")[0]) - Integer.parseInt(text.split("-")[1]);
		}
		else if(text.contains("+")) {
			value = Integer.parseInt(text.split("\\+")[0]) + Integer.parseInt(text.split("\\+")[1]);
		}
		else
			System.out.println("Captcha answer not calculated");
		
		return value;

	}
	
	public void selectAccountType(String account) {
		String element = "//label[text()" + "='"+account +"']";
		TestUtility.clickOn(DriverManager.getDriver().findElement(By.xpath(element)));
	}
	
	public boolean verifyMailSent() {
     return  TestUtility.isElementDisplayed(activateEmailSent);
	}
	
	public String emailSentorNotText() {
		return TestUtility.getText(activateEmailSent);
	}
	public boolean educationBannerAlertDisplayed() {
		return TestUtility.isElementDisplayed(educationBannerAlert);
	}
	
	public boolean IndidivualBannerAlertDisplayed() {
		return TestUtility.isElementDisplayed(IndividualBannerAlert);
	}
	
	public boolean SoleTraderBannerAlertDisplayed() {
		return TestUtility.isElementDisplayed(SoleTraderBannerAlert);
	}
	
	public String geteducationBannerAlertText() {
		return TestUtility.getText(educationBannerAlert);
	}
	
	public String getIndidivualBannerAlertText() {
		return TestUtility.getText(IndividualBannerAlert);
	}
	
	public String getSoleTraderBannerAlertText() {
		return TestUtility.getText(SoleTraderBannerAlert);
	}
	
	public String getPaymentNotAvailableCountryText() {
		
		  return TestUtility.getText(CantPayFromSelectedCountryText);
	}
	
	public void ActivateNewAccount(String host, String storeType, String user, String password) {
		  DriverManager.getDriver().get(emailActivationHelper.getNewAccountActivationLink(host, storeType, user, password));
	}
	
	public void enterPassword(String value) {
		TestUtility.sendKeys(enterPassword, value);
	}
	
	public void confirmPassword(String value) {
		TestUtility.sendKeys(confirmPassword, value);
	}
	
	public void clickOnSubscribeButton() {
		TestUtility.clickOn(subscribeButton);
	}
}
