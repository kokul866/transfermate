package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.constants.Constants;
import com.qa.locators.Locators;
import com.qa.pageobjects.PageObjects;
import com.qa.utilities.WebEventListener;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase
{
	public static WebDriver driver; 
	public static DesiredCapabilities cap;
	public static Properties property;
	public static ChromeOptions chromeOptions;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	public static Logger Log;
	public Locators locators;
	public PageObjects PO;	
	public TestBase()
	{
		try 
		{
			Log = Logger.getLogger(this.getClass());
			property = new Properties();
			FileInputStream inputStream = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/com/qa/config/Configuration.properties");
			property.load(inputStream);
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void initialization()
	{
        		
		String broswerName = property.getProperty("Browser");
		
	//	String broswerName = System.getProperty("Browser");
		if(broswerName.equals("Chrome"))
		{
			chromeOptions = new ChromeOptions();
			chromeOptions.setExperimentalOption("useAutomationExtension", false);
			chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
			chromeOptions.addArguments("--incognito");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(chromeOptions);
		}
		else if(broswerName.equals("Edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		else if(broswerName.equals("Firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
			
		else
		{
			System.out.println("Path of Driver Executable is not Set for any Browser");
		} 
		DriverManager.setWebDriver(driver);
        e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Constants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get(property.getProperty("Url"));
	}
	
	public  void initializeRemote() throws MalformedURLException
	{
		cap=DesiredCapabilities.chrome();
		URL url=new URL("http://localhost:4444/");
	    driver =new RemoteWebDriver(url,cap);
	    e_driver = new EventFiringWebDriver(driver);
	  	eventListener = new WebEventListener();
	  	e_driver.register(eventListener);
	  	driver = e_driver;
	    driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		DriverManager.setWebDriver(driver);
		driver.manage().timeouts().pageLoadTimeout(Constants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get(property.getProperty("Url"));
	}
	
	@BeforeMethod(alwaysRun=true)
	public void setUp() throws MalformedURLException
	{
		if(property.getProperty("ENV").equals("Local"))
			     initialization();
		else if(property.getProperty("ENV").equals("Remote"))
		         initializeRemote();
		Log.info("Application Launched Successfully");
		locators=new Locators();
		PO=new PageObjects();
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() throws IOException
	{
		driver.quit();
		Log.info("Browser Terminated");
		Log.info("-----------------------------------------------");
	}
}
