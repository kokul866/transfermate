package com.qa.listeners;

import java.io.IOException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qa.reports.ExtentReportSetup;
import com.qa.utilities.TestUtility;

public class ExtentReportListener extends ExtentReportSetup implements ITestListener
{
	ThreadLocal<ExtentTest> tl=new ThreadLocal<ExtentTest>();
	public void onTestStart(ITestResult result) 
	{
		extentTest = extent.createTest(result.getMethod().getMethodName());
		tl.set(extentTest);
	}

	public void onTestSuccess(ITestResult result) 
	{
		tl.get().log(Status.PASS, "Test Case Passed is ::: " +result.getMethod().getMethodName());
		//extentTest.log(Status.PASS, "Test Case Passed is ::: " +result.getMethod().getMethodName());
	}

	public void onTestFailure(ITestResult result)
	{
		extentTest.log(Status.FAIL, "Test Case Failed is ::: " +result.getMethod().getMethodName());
		tl.get().log(Status.FAIL, result.getThrowable());
	//	extentTest.log(Status.FAIL, result.getThrowable());
		try 
		{
			TestUtility.getScreenshot(result.getMethod().getMethodName());
			tl.get().addScreenCaptureFromPath(TestUtility.getScreenshot(result.getMethod().getMethodName()));
		//extentTest.addScreenCaptureFromPath(TestUtility.getScreenshot(result.getMethod().getMethodName()));
		} 
		catch(IOException e) 
		{
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) 
	{
		//tl.get().log(Status.SKIP, "Test Case Skipped is ::: " +result.getMethod().getMethodName());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) 
	{

	}

	public void onStart(ITestContext context) 
	{
    	extent = ExtentReportSetup.extentReportSetup();
	}

	public void onFinish(ITestContext context) 
	{
		extent.flush();
	}
}
