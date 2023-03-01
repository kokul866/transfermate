package com.qa.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.base.DriverManager;
import com.qa.base.TestBase;
import com.qa.constants.Constants;


import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.FlagTerm;
public class TestUtility extends TestBase 
{
	public static Actions actions;
	public static Select select;
	public static Alert alert;
    static int timeout=Constants.EXPLICIT_WAIT;
    public static JavascriptExecutor javaScript;
    public static Workbook book;
	public static Sheet sheet;

	
	//Explicit Wait to Click on WebElement.
	public static void clickOn( WebElement element) 
	{
		try {
		new WebDriverWait(DriverManager.getDriver(), timeout).until(ExpectedConditions.elementToBeClickable(element));
		element.click();
		}
		catch(Exception err) {
			System.out.println("Element is not clicked ==>> Please Check :::"+err.getMessage());
	}
	}

	//Explicit Wait to Send Data to WebElement.
	public static void sendKeys(WebElement element,String value) 
	{
		try {
		new WebDriverWait(DriverManager.getDriver(), timeout).until(ExpectedConditions.visibilityOf(element));
		element.sendKeys(value);
		}
		catch(Exception err) {
			System.out.println("Sending keys failed ==>> Please Check :::"+err.getMessage());		
	}
}
	
	//GetText of a WebElement
	public static String getText(WebElement element)
	{
		new WebDriverWait(DriverManager.getDriver(), timeout).until(ExpectedConditions.visibilityOf(element));
		return element.getText();
	}

	//Explicit Wait for Element To Be Visible.
	public static void waitForElementToBeVisible( By locator)
	{
		new WebDriverWait(DriverManager.getDriver(), timeout).
		until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	//To Handle Multiple Windows or Switch Between Multiple Windows.
	public void switchWindow( String firstWindow, String secondWindow) 
	{
		Set<String> windowHandles = DriverManager.getDriver().getWindowHandles();
		for(String windows : windowHandles) 
		{
			if(!windows.equals(firstWindow) && !windows.equals(secondWindow)) 
			{
				DriverManager.getDriver().switchTo().window(windows);
			}
		}
	}

	//To Check Element is Displayed or No.
	public static boolean isElementDisplayed(WebElement element) 
	{
		
		boolean elementDisplayed = element.isDisplayed();
		if(elementDisplayed) 
		{
			System.out.println("Element is Displayed");
		} 
		else 
		{
			System.out.println("Element is not Displayed");
		}
		return elementDisplayed;
	}

	//To Check Element is Enabled or No.
	public static boolean isElementEnabled(WebElement element) 
	{
		boolean elementEnabled = element.isEnabled();
		if(elementEnabled) 
		{
			System.out.println("Element is Enabled");
		} 
		else 
		{
			System.out.println("Element is not Enabled");
		}
		
		return elementEnabled;
	}

	//To Select a value from Drop Down by using SelectByVisibleText Method.
	public static void selectValueFromDropDownByText(WebElement element, String value) 
	{
		select = new Select(element);
		select.selectByVisibleText(value);
	}

	//To Select a value from Drop Down by using SelectByIndex Method.
	public static void selectValueFromDropDownByIndex(WebElement element, int value) 
	{
		select = new Select(element);
		select.selectByIndex(value);
	}

	//To Select a value from Drop Down by using SelectByValue Method.
	public static void selectValueFromDropDownByValue(WebElement element, String value) 
	{
		select = new Select(element);
		select.selectByValue(value);
	}

	//To Print all Values and Select a Required Value from Drop Down.
	public static void selectDropDownValue(String xpathValue, String value) 
	{
		List<WebElement> monthList = driver.findElements(By.xpath(xpathValue));
		System.out.println(monthList.size());

		for(int i=0; i<monthList.size(); i++) 
		{
			System.out.println(monthList.get(i).getText());
			if(monthList.get(i).getText().equals(value)) 
			{
				monthList.get(i).click();
				break;
			}
		}
	}

	//To Validate Drop Down Values.
	public static List<String> dropDownValuesValidation(WebElement element) 
	{
		Select select = new Select(element);
		List<WebElement> dropDownValues = select.getOptions();

		List<String> toolsDropDownValues = new ArrayList<String>();

		for(WebElement listOfDropDownValues : dropDownValues) 
		{
			toolsDropDownValues.add(listOfDropDownValues.getText());
		}
		return toolsDropDownValues;
	}
	
	//To Select Radio Button.
	public void selectRadioButton(List<WebElement> element, String value)
	{
		for(WebElement elements : element)
		{
			if(elements.getText().equalsIgnoreCase(value))
			{
				elements.click();
				break;
			}
		}
	}
	
	//To Accept Alert Pop-Up.
	public static void acceptAlertPopup() throws InterruptedException 
	{
		try 
		{
			alert = driver.switchTo().alert();
			System.out.println(alert.getText());
			alert.accept();
			System.out.println("Alert Accepted Successfully");
		} 
		catch(Exception e) 
		{
			System.out.println("Something Went Wrong ==>> Please Check ::: " +e.getMessage());
		}
	}

	//To Dismiss Alert Pop-Up.
	public static void dismissAlertPopup() throws InterruptedException 
	{
		try 
		{
			alert = driver.switchTo().alert();
			System.out.println(alert.getText());
			alert.dismiss();
			System.out.println("Alert Dismissed Successfully");
		} 
		catch(Exception e) 
		{
			System.out.println("Something Went Wrong ==>> Please Check ::: " + e.getMessage());
		}
	}

	//To Match Value with List of Elements and Click on it.
	public void clickOnMatchingValue(List<WebElement> listOfElements, String valueToBeMatched) 
	{
		for(WebElement element : listOfElements) 
		{
			if(element.getText().equalsIgnoreCase(valueToBeMatched)) 
			{
				element.click();
				return;
			}
		}
	}

	//To Click on Element using Actions Class.
	public void clickOnElementUsingActions(WebElement element) 
	{
		actions = new Actions(driver);
		actions.moveToElement(element).click().perform();
	}
		
	//To Mouse Hover and Click or Select an Element using Actions Class.
	public static void moveToElement( WebElement element) 
	{
		actions = new Actions(DriverManager.getDriver());
		actions.moveToElement(element).build().perform();
	}

	//To Perform Drag and Drop action using Actions Class - 1.
	public static void dragAndDrop_1(WebElement sourceElement, WebElement destinationElement) 
	{
		actions = new Actions(DriverManager.getDriver());
		actions.dragAndDrop(sourceElement, destinationElement).pause(Duration.ofSeconds(2)).release().build().perform();
		
	}

	//To Perform Drag and Drop action using Actions Class - 2.
	public static void dragAndDrop_2(WebElement sourceElement, WebElement destinationElement) 
	{
		actions = new Actions(DriverManager.getDriver());
		actions.clickAndHold(sourceElement).pause(Duration.ofSeconds(2)).moveToElement(destinationElement).pause(Duration.ofSeconds(2)).release().build().perform();
	}
	
	//To Perform Drag and Drop action using Actions Class - x,y location value
	public static void dragAndDrop_3(WebElement sourceElement,int x,int y) 
	{
		new WebDriverWait(DriverManager.getDriver(), timeout).until(ExpectedConditions.elementToBeClickable(sourceElement));
		actions = new Actions(DriverManager.getDriver());
		actions.dragAndDropBy(sourceElement, x, y).release().perform();
	}

	//To Perform Right Click action using Actions Class.
	public static void rightClick( WebElement element) 
	{
		actions = new Actions(DriverManager.getDriver());
		actions.contextClick(element).build().perform();
	}

	//To perform Double Click action using Actions Class.
	public static void doubleClick( WebElement element) 
	{
		actions = new Actions(DriverManager.getDriver());
		actions.doubleClick(element).build().perform();
	}

	//Extent Report - 1.
	public static String getSystemDate() 
	{
		DateFormat dateFormat = new SimpleDateFormat("_ddMMyyyy_HHmmss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	//Extent Reports Screesnshots
	public static String getScreenshot(String screenshotName) throws IOException 
	{
		String dateName = new SimpleDateFormat("_ddMMyyyy_HHmmss").format(new Date());
		TakesScreenshot ts =((TakesScreenshot)DriverManager.getDriver());
		File source = ts.getScreenshotAs(OutputType.FILE);

		String destination = System.getProperty("user.dir") + "//FailedTestsScreenshots//" + screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
	
	public static String getprop(String value) throws IOException
	{
		Properties property=new Properties();
		FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\pims\\qa\\config\\Configuration.properties");
		property.load(ip);
		return property.getProperty(value);
	}
	
	
	public boolean ElementisPresent(List<WebElement> element)
	{
		if((element).size() != 0){
			System.out.println("Element is Present");
			return true;
			}else{
			System.out.println("Element is Absent");
			return false;
			}
	}
	
	public static void waitForElementToBeClickable( WebElement element)
	{
		new WebDriverWait(DriverManager.getDriver(), timeout).until(ExpectedConditions.elementToBeClickable(element));
	}
	
	
	public static void moveByOffset(int x, int y) 
	{
		actions = new Actions(DriverManager.getDriver());
		actions.moveByOffset(x, y).build().perform();
		
	}
	
	public static Object[][] getTestData(String sheetName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(Constants.testdatapath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
			}
		}
		return data;
	}
	
	public static String check(String host, String storeType, String user, String password) {
		String messageContent = null;
		try {

			// create properties
			Properties properties = new Properties();

			properties.put("mail.imap.host", host);
			properties.put("mail.imap.port", "993");
			properties.put("mail.imap.starttls.enable", "true");
			properties.put("mail.imap.ssl.trust", host);

			Session emailSession = Session.getDefaultInstance(properties);

			// create the imap store object and connect to the imap server
			Store store = emailSession.getStore("imaps");

			store.connect(host, user, password);

			// create the inbox object and open it
			Folder inbox = store.getFolder("Inbox");
			inbox.open(Folder.READ_WRITE);

			// retrieve the messages from the folder in an array and print it
			Message[] messages = inbox.search(new FlagTerm(new Flags(Flag.SEEN), false));
			System.out.println("messages.length---" + messages.length);
			Message message  = messages[0];
		    messageContent = message.toString();
//			for (int i = 0, n = messages.length; i < n; i++) {
//				Message message = messages[i];
//				message.setFlag(Flag.SEEN, true);
//				System.out.println("---------------------------------");
//				System.out.println("Email Number " + (i + 1));
//				System.out.println("Subject: " + message.getSubject());
//				System.out.println("From: " + message.getFrom()[0]);
//				System.out.println("Text: " + message.getContent().toString());
//
//			}

			inbox.close(false);
			store.close();

		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return  messageContent;
	}
	
	//To Switch into a Frame using Name.
		public  void switchToFrame(String frameName) 
		{
			try 
			{
				DriverManager.getDriver().switchTo().frame(frameName);
				System.out.println("Navigated to Frame with Name ::: " +frameName);
			} 
			catch (NoSuchFrameException e) 
			{
				System.out.println("Unable to Locate Frame with Name ::: " +frameName +e.getStackTrace());
			} 
			catch (Exception e) 
			{
				System.out.println("Unable to Navigate to Frame with Name ::: " +frameName +e.getStackTrace());
			}
		}

		//To Switch into a Frame using Index.
		public static  void switchToFrame(int frame) 
		{
			try 
			{
				new WebDriverWait(DriverManager.getDriver(), timeout).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
				DriverManager.getDriver().switchTo().frame(frame);
				System.out.println("Navigated to Frame with Index ::: " +frame);
			} 
			catch(NoSuchFrameException e) 
			{
				System.out.println("Unable to Locate Frame with Index ::: " +frame +e.getStackTrace());
			} 
			catch(Exception e) 
			{
				System.out.println("Unable to Navigate to Frame with Index ::: " +frame +e.getStackTrace());
			}
		}
		
		public static void switchToFrame(WebElement framelement) 
		{
			try 
			{
				DriverManager.getDriver().switchTo().frame(framelement);
				System.out.println("Navigated to Frame with Name ::: " +framelement);
			} 
			catch (NoSuchFrameException e) 
			{
				System.out.println("Unable to Locate Frame with Name ::: " +framelement +e.getStackTrace());
			} 
			catch (Exception e) 
			{
				System.out.println("Unable to Navigate to Frame with Name ::: " +framelement +e.getStackTrace());
			}
		}

		//To Take Screenshot at End Of Test.
		public static void takeScreenshotAtEndOfTest() throws IOException 
		{
			File scrFile = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
			String currentDir = System.getProperty("user.dir");
			FileUtils.copyFile(scrFile, new File(currentDir + "/Screenshots/" + System.currentTimeMillis() + ".png"));
		}


//	public static void main(String[] args) {
//
//		String host = "imap.gmail.com";
//		String mailStoreType = "imap";
//		String username = "yourmailaddress@gmail.com";
//		String password = "yourpassword";
//
//		check(host, mailStoreType, username, password);
//
//	}

}
