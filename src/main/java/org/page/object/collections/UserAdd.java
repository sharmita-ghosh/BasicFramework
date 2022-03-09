package org.page.object.collections;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserAdd {

public WebDriver driver;
	
	By addbutton=By.cssSelector("button#add");
	By firstname=By.xpath("//input[@id='firstName']");
	By lastname=By.xpath("//input[@id='lastName']");
	By addemp= By.id("addEmployee");
	By dependents = By.id("dependants");
	
	



	public UserAdd(WebDriver driver) {
		this.driver = driver;
	}



	public WebElement getAddbutton() {
		/*WebElement element;
		//Webdriver driver;
		WebDriverWait wait = new WebDriverWait(driver, 100);
		element= wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button#add")));
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);*/
		return driver.findElement(addbutton);
	}
	
	public WebElement getFirstname() {
		return driver.findElement(firstname);
	}
	public WebElement getLastname() {
		
		return driver.findElement(lastname);
	}
	public WebElement getDependents() {
		
		return driver.findElement(dependents);
	}
	public WebElement getAddEmp() {
		
		return driver.findElement(addemp);
	}


}
