package org.basic.business.flow;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.resources.Base;
import org.resources.ExtentReport;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listeners implements ITestListener {


	Base baseclass = new Base();
	ExtentReports extent =ExtentReport.configReport();
	ExtentTest test = null;
	//ExtentReports report = ExtentReport.configReport();*/
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
		 test = extent.createTest(result.getMethod().getMethodName());
		
	
	}
	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		test.log(Status.PASS, "Test Case Passed!");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		test.log(Status.FAIL, "Test Case Failed!");
		WebDriver driver = null;
		String gettcname= result.getMethod().getMethodName();
		try {
			driver =(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} 
		catch(Exception e) {}
		try {
			baseclass.getScreenShot(gettcname, driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
	
		extent.flush();
	}

}
