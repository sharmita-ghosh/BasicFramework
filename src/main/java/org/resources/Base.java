package org.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Base {

	public WebDriver driver;
	Properties property = new Properties();
	public WebDriver initializeDriver() throws IOException {
		
		//Properties property = new Properties();
		String filepath = System.getProperty("user.dir");
		FileInputStream FileIS= new FileInputStream(filepath+"\\src\\main\\java\\org\\resources\\dataproperties.properties");
		property.load(FileIS);
		String browsername = property.getProperty("browser");
		String driverpath = property.getProperty("driverpathchrome");
		System.out.println(driverpath);
		String path = System.getProperty("user.dir");
		System.out.println(path);
		System.out.println(path+driverpath);
		
		if (browsername.equals("chrome")) {
			
			System.setProperty("webdriver.chrome.driver", path+driverpath);
			
			driver = new ChromeDriver();
			
		}
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//Duration duration = Duration.ofSeconds(30);
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		return driver;
	}
	
	public void getScreenShot(String testcasename, WebDriver driver) throws IOException {
		
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		//String path1=System.getProperty("user.dir");
		//String path2=property.getProperty("initialpath1");
		//String path3=property.getProperty("initialpath2");
		String destinationscrrenshotpath = System.getProperty("user.dir")+"\\Reports\\"+testcasename+".png";
		System.out.println(destinationscrrenshotpath);
		FileUtils.copyFile(src, new File(destinationscrrenshotpath));
	}
}
