package com.qa.testcases;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.constants.Constants;
import com.qa.utilities.RandomUtilities;
import com.qa.utilities.TestUtility;

public class SignUpTest extends TestBase {
	public SignUpTest() {
		super();
	}

	@Test(dataProvider = "testdata")
	public void Verify_User_is_able_to_complete_the_sign_up_and_receive_activation_email_for_all_Account_types(String AccountType,String country)
			throws InterruptedException, IOException {
		PO.selectAccountType(AccountType);
		PO.selectCountry(country);
		PO.enterFirstName(RandomUtilities.randomString());
		PO.enterLastName(RandomUtilities.randomString());
		PO.enterEmail(RandomUtilities.randomEmail());
		PO.enterMobile(RandomUtilities.randomNumber());
		PO.checkPrivacyPolicy();
		PO.enterCaptcha(String.valueOf(PO.getCaptcha()));
		PO.clickOpenAccount();
		Assert.assertTrue(PO.verifyMailSent());
		System.out.println(PO.emailSentorNotText());
		Assert.assertEquals(PO.emailSentorNotText(), Constants.activationLinkSentText);
	} 

	@Test
	public void Verify_User_is_not_able_to_sign_up_without_selecting_account_type() throws InterruptedException {
		PO.clickOpenAccount();
		Assert.assertTrue(PO.errorMessageIsDisplayed("accountType"));
		System.out.println(PO.getErrorMessage("accountType"));
		Assert.assertEquals(PO.getErrorMessage("accountType"), Constants.accountTypeErrorMessage);
	}

	@Test
	public void Verify_User_is_not_able_to_sign_up_with_already_existing_email() {
		PO.selectAccountType("Individual");
		PO.selectCountry("Ireland");
		PO.enterFirstName(RandomUtilities.randomString());
		PO.enterLastName(RandomUtilities.randomString());
		PO.enterEmail("Kokulklein@gmail.com");
		PO.enterMobile(RandomUtilities.randomNumber());
		PO.checkPrivacyPolicy();
		int value = PO.getCaptcha();
		PO.enterCaptcha(String.valueOf(value));
		PO.clickOpenAccount();
		Assert.assertTrue(PO.errorMessageIsDisplayed("email"));
		Assert.assertEquals(PO.getErrorMessage("email"), Constants.duplicateemailErrorMessage);
	}

	@Test
	public void Verify_User_is_not_able_to_sign_up_without_firstName__lastName_email_and_capcha() {
		PO.clickOpenAccount();
		Assert.assertTrue(PO.errorMessageIsDisplayed("firstName"));
		Assert.assertEquals(PO.getErrorMessage("firstName"), Constants.invalidDataErrorMessage);
		Assert.assertTrue(PO.errorMessageIsDisplayed("lastName"));
		Assert.assertEquals(PO.getErrorMessage("lastName"), Constants.invalidDataErrorMessage);
		Assert.assertTrue(PO.errorMessageIsDisplayed("email"));
		Assert.assertEquals(PO.getErrorMessage("email"), Constants.mandatoryFieldErrorMessage);
		Assert.assertTrue(PO.errorMessageIsDisplayed("captcha"));
		Assert.assertEquals(PO.getErrorMessage("captcha"), Constants.invalidDataErrorMessage);
	}

	@Test
	public void Verify_User_is_not_able_to_enter_non_alphabatic_characters_in__firstName__lastName_inputbox() {
		PO.enterFirstName("AASDD123");
		PO.enterLastName("LAST@@");
		PO.clickOpenAccount();
		Assert.assertTrue(PO.errorMessageIsDisplayed("firstName"));
		Assert.assertEquals(PO.getErrorMessage("firstName"), Constants.invalidDataErrorMessage);
		Assert.assertTrue(PO.errorMessageIsDisplayed("lastName"));
		Assert.assertEquals(PO.getErrorMessage("lastName"), Constants.invalidDataErrorMessage);
	}

	@Test
	public void Verify_Mobile_inputbox_only_allows_numbers() {
		PO.selectAccountType("Individual");
		PO.selectCountry("France");
		PO.enterFirstName("addds");
		PO.enterLastName("sddffdff");
		PO.enterEmail("sjsdhsd@gsss.com");
		PO.enterMobile("qa@");
		PO.clickOpenAccount();
		Assert.assertTrue(PO.errorMessageIsDisplayed("mobile"));
		Assert.assertEquals(PO.getErrorMessage("mobile"), Constants.invalidDataErrorMessage);
	}
	
	@Test
	public void Verify_User_is_not_able_to_sign_up_with_invalid_captcha() {
		PO.selectAccountType("Individual");
		PO.selectCountry("Australia");
		PO.enterFirstName("addds");
		PO.enterLastName("sddffdff");
		PO.enterEmail("sjsdhsd@gsss.com");
		PO.enterMobile("123467544");
		int value = PO.getCaptcha()+ 1;
		PO.enterCaptcha(String.valueOf(value));
		PO.checkPrivacyPolicy();
		PO.clickOpenAccount();
		Assert.assertTrue(PO.errorMessageIsDisplayed("InvalidCaptcha"));
		Assert.assertEquals(PO.getErrorMessage("InvalidCaptcha"), Constants.invalidDataErrorMessage);
	} 
	
	@Test
	public void Verify_Account_Type_Banner_Alert() {
		Assert.assertFalse(PO.educationBannerAlertDisplayed());
		PO.selectAccountType("Education");
		Assert.assertTrue(PO.educationBannerAlertDisplayed());
		Assert.assertTrue(PO.geteducationBannerAlertText().contains(Constants.educationBannerPrimaryText));
		Assert.assertTrue(PO.geteducationBannerAlertText().contains(Constants.educationBannerSecondaryText));
		PO.selectAccountType("Individual");
		Assert.assertFalse(PO.educationBannerAlertDisplayed());
		Assert.assertTrue(PO.IndidivualBannerAlertDisplayed());
		Assert.assertTrue(PO.getIndidivualBannerAlertText().contains(Constants.IndividualBannerText));
	    PO.selectAccountType("Sole Trader");
		Assert.assertTrue(PO.SoleTraderBannerAlertDisplayed());
		Assert.assertTrue(PO.getSoleTraderBannerAlertText().contains(Constants.SoleTraderBannerText));
		PO.selectAccountType("Partnership");
		Assert.assertFalse(PO.educationBannerAlertDisplayed());
		Assert.assertFalse(PO.SoleTraderBannerAlertDisplayed());
	    Assert.assertFalse(PO.IndidivualBannerAlertDisplayed());
		PO.selectAccountType("Corporate");
		Assert.assertFalse(PO.educationBannerAlertDisplayed());
	    Assert.assertFalse(PO.IndidivualBannerAlertDisplayed());
	    Assert.assertFalse(PO.SoleTraderBannerAlertDisplayed());
}
	@Test
	public void Verify_User_is_not_able_to_complete_the_sign_up_with_Country_where_payment_cannot_be_done()
			throws InterruptedException, IOException {
		PO.selectAccountType("Education");
		PO.selectCountry("India");
		PO.enterFirstName(RandomUtilities.randomString());
		PO.enterLastName(RandomUtilities.randomString());
		PO.enterEmail(RandomUtilities.randomEmail());
		PO.enterMobile(RandomUtilities.randomNumber());
		PO.checkPrivacyPolicy();
		PO.enterCaptcha(String.valueOf(PO.getCaptcha()));
		PO.clickOpenAccount();
		Assert.assertTrue(PO.verifyMailSent());
		Assert.assertEquals(PO.getPaymentNotAvailableCountryText(), Constants.NotpayablefromCountrytext);
		Assert.assertEquals(PO.emailSentorNotText(), Constants.workingonNonPayablecontryText);
	} 

 

	@DataProvider(name = "testdata")
	public Object[][] data() throws IOException {
		Object[][] data = TestUtility.getTestData("Sheet1");
		return data;
	}
	

}
