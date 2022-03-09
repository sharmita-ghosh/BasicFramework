package org.resources;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {
	
	static ExtentReports report;
	@BeforeTest
	public static ExtentReports configReport() {
		
		String path =System.getProperty("user.dir")+"\\Reports\\extentreport.html";
		ExtentSparkReporter extent = new ExtentSparkReporter(path);
		extent.config().setReportName("Web Automation Report");
		extent.config().setDocumentTitle(path);
		
		report = new ExtentReports();
		
		report.attachReporter(extent);
		report.setSystemInfo("Tested BY", "Sharmita");
		return report;
	}
	
	

}
