package org.page.object.collections;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Login {



		
		public WebDriver driver;
		
		By uname=By.id("Username");
		By password=By.id("Password");
		By loginbutton=By.xpath("//button[@class='btn btn-primary']");
		
		
		
		public Login(WebDriver driver) {
			this.driver = driver;
		}

		public WebElement getLogin() {
			
			return driver.findElement(loginbutton);
		}
		
		public WebElement getPwd() {
			return driver.findElement(password);
		}
		public WebElement getUname() {
			return driver.findElement(uname);
		}


}
