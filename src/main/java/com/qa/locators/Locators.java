package com.qa.locators;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.qa.base.DriverManager;
import com.qa.base.TestBase;
public class Locators extends TestBase {
	public Locators()
	{
		PageFactory.initElements(DriverManager.getDriver(), this);
	}
	
	@FindBy(xpath="//select[contains(@name,'country')]")
	protected WebElement countrydrpdwn;
	
	@FindBy(xpath="//input[@name='first_name']")
	protected WebElement firstNameInputbox;
	
	@FindBy(xpath="//input[@name='last_name']")
	protected WebElement lastNameInputbox;
	
	@FindBy(xpath="//input[@name='email']")
	protected WebElement emailInputBox;
	
	@FindBy(xpath="//input[@type='tel']")
	protected WebElement mobileInputBox;
	
	@FindBy(xpath="//input[@id='terms_of_use_agree']/following-sibling::label")
	protected WebElement privacyPolicyCheckBox;
	
	@FindBy(css="#cal_captcha_f10_question")
	protected WebElement captchaQuestion;
	
	@FindBy(xpath="//input[contains(@id,'captcha_text')]")
	protected WebElement captchaInputBox;
	
	@FindBy(xpath="//input[@name='button_subscribe']")
	protected WebElement openAccountBtn;
	
	@FindBy(css="#register_account_type_education_error")
	protected WebElement AccountTypeErrorMessage;
	
	@FindBy(css="#register_first_name_error")
	protected WebElement firstNameErrorMessage;
	
	@FindBy(css="#register_last_name_error")
	protected WebElement lastNameErrorMessage;
	
	@FindBy(css="#register_email_error")
	protected WebElement emailErrorMessage;
	
	@FindBy(xpath="//div[contains(@id,'mobile_number_mobile_phone_error')]/div")
	protected WebElement mobilePhoneErrorMessage;
	
	@FindBy(xpath="//div[contains(@id,'register___calc_captcha_text_error')]/div")
	protected WebElement captchaErrorMessage;
	
	@FindBy(xpath="//div[contains(@id,'register_calc_captcha_error')]")
	protected WebElement InvalidCaptchaErrorMessage;
	
	@FindBy(xpath="//select[contains(@name,'mobile_number_international_dialing_code')]")
	protected WebElement mobileDailCode;
	
	@FindBy(xpath="//h5")
	protected WebElement activateEmailSent;
	
	@FindBy(xpath="//h2")
	protected WebElement CantPayFromSelectedCountryText;
	
	@FindBy(css="#custom_label_field___label_education_linked")
	protected WebElement educationBannerAlert;
	
	@FindBy(css="*[role='alert']")
	protected WebElement IndividualBannerAlert;
	
	@FindBy(xpath="//div[contains(@id,'register_sole_trader_warning_label_form_text_paragraph')]")
	protected WebElement SoleTraderBannerAlert;
	
	@FindBy(css="#password")
	protected WebElement enterPassword;
	
	@FindBy(css="#confirm_password")
	protected WebElement confirmPassword;
	
	@FindBy(css="#button_subscribe")
	protected WebElement subscribeButton;
	
	
	
	
	
	

}
