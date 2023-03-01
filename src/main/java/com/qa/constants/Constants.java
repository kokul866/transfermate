package com.qa.constants;

public class Constants 
{
	
	public static final int PAGE_LOAD_TIMEOUT = 50;
	public static final int IMPLICIT_WAIT = 10;
	public static final int EXPLICIT_WAIT = 10;
	
	public static final String projectpath = System.getProperty("user.dir");
	public static final String configpath = projectpath+ "//src//main//java//com//qa//config//Configuration.properties";
	public static final String testdatapath = projectpath + "//src//main//java//com//qa//testdata//transfer.xlsx";
	
	public static final String invalidDataErrorMessage = "Please enter correct information!";
	public static final String accountTypeErrorMessage = "Please Select Account Type";
	public static final String duplicateemailErrorMessage = "Already exists!";
	public static final String mandatoryFieldErrorMessage = "The field is mandatory";
	public static final String educationBannerPrimaryText= "If you are a student or parent wanting to make a payment to an Educational Institute or for Accommodation, do not sign up here. Please go to TransferMate Education and sign up there instead.";
	public static final String educationBannerSecondaryText=  "If you are an Educational Institude wanting to start using TransferMate, please sign up using the form below.";
	public static final String IndividualBannerText ="Use an 'Individual' account for personal payments (e.g. buying a car / property, moving funds between personal accounts)."; 
	public static final String SoleTraderBannerText= "Use a 'Sole Trader' account if you're a registered sole trader making business payments.";
	public static final String activationLinkSentText= "We've sent a link to activate your TransferMate account.";
	public static final String NotpayablefromCountrytext= "Sorry, we can't pay from India yet!";
	public static final String workingonNonPayablecontryText= "We're working on cheaper, faster, safer payments from India. We can notify you when this happens.";
}
