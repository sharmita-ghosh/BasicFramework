package org.page.object.collections;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UserEdit {
	
	

	public WebDriver driver;

	//By editicon = By.xpath("//table[@id='employeesTable']/tbody/tr[1]/td[9]/i[@class= 'fas fa-edit']");
	By allediticons = By.xpath("//i[@class= 'fas fa-edit']");
	By updatebutton = By.id("updateEmployee");

	public UserEdit(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}
	public WebElement getEditIcon() {
		return driver.findElement(allediticons);
	}
	public WebElement getUpdateButton() {
		return driver.findElement(updatebutton);
	}
	
}
