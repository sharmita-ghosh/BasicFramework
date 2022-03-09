package org.basic.business.flow;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.page.object.collections.Login;
import org.page.object.collections.UserAdd;
import org.page.object.collections.UserEdit;
import org.resources.Base;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class LandingPage extends Base {

	public WebDriver driver;
	UserAdd adduser;
	UserEdit useredit;
	Actions actions;
	

	@BeforeTest
	public void driverInitialize() throws IOException {
		driver = initializeDriver();
		adduser = new UserAdd(driver);
		useredit = new UserEdit(driver);
		actions =  new Actions(driver); 
	}
	
	
	/*User Loading and Login to Paylocity Site */
	@Test
	public void basePage() {

		driver.get("https://wmxrwq14uc.execute-api.us-east-1.amazonaws.com/Prod/Account/Login");
		driver.manage().window().maximize();
		System.out.println("Successfully Landed on Paylocity site");
		Login login = new Login(driver);
		login.getUname().sendKeys("TestUser160");
		login.getPwd().sendKeys("OCABwco&.G>2");
		login.getLogin().click();
		System.out.println("Successfully Logged in");
		Assert.assertEquals(driver.getTitle(), "Employees - Paylocity Benefit Dashboard");

	}
	
	
	/*User Addition Functionality */
	@Test(dataProvider = "getAddUserData")
	public void userdetailsAdd(String fname, String lname) {

		adduser.getAddbutton().click();
		adduser.getFirstname().sendKeys(fname);
		adduser.getLastname().sendKeys(lname);
		adduser.getDependents().sendKeys("1");
		adduser.getAddEmp().click();
		System.out.println("User Add Successfully");

	}

	@DataProvider
	public Object[][] getAddUserData() {
		Object[][] data = new Object[1][2];
		data[0][0] = "firstname20";
		data[0][1] = "lastname20";
		;
		return data;
	}

	/*User Modification Functionality */
	@Test(dependsOnMethods={"userdetailsAdd"}, dataProvider="getEditUserData", alwaysRun = true)
	public void userdetailsEdit(String updatefn, String updateln, String editfn, String editln) {
		
		int i;
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
		List<WebElement> tablevalues = driver.findElements(By.xpath("//table[@id='employeesTable']/tbody/tr"));
		List<WebElement> allediticons = driver.findElements(By.xpath("//i[@class= 'fas fa-edit']"));
		
		for (i=0; i<tablevalues.size(); i++)
		{
			
			String empdetails = tablevalues.get(i).getText();
			String empfirstname = empdetails.split(" ")[1].trim();
			String emplastname = empdetails.split(" ")[2].trim();
		
			
			
			if (empfirstname.equalsIgnoreCase(updatefn) && emplastname.equalsIgnoreCase(updateln)){
				allediticons.get(i).click();
				System.out.println("Edit icon clicked");
				adduser.getFirstname().clear();
				adduser.getFirstname().sendKeys(editfn);
				adduser.getLastname().clear();
				adduser.getLastname().sendKeys(editln);
				adduser.getDependents().clear();
				adduser.getDependents().sendKeys("2");
				useredit.getUpdateButton().click();
				System.out.println("Edit functonality PASSED");
				break;
			}
			
		}

	}

	@DataProvider
	public Object[][] getEditUserData() {
		Object[][] data = new Object[1][4];
		data[0][0] = "firstname20";
		data[0][1] = "lastname20";
		data[0][2] = "firstname22";
		data[0][3] = "lastname22";
		return data;
	}
	
	
	/*User Details Validation Functionality */
	@Test(dependsOnMethods={"userdetailsEdit"}, dataProvider="getValidateUser", alwaysRun = true)
	public void userdetailsValidation(String verifyfirstname, String verifylastname, String verifydependents, String verifysalary, String Verifygrosspay, String Verifybenefits, String Verifynetpay) throws InterruptedException
	{
    	driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
    	int i;
		List<WebElement> tablevalues = driver.findElements(By.xpath("//table[@id='employeesTable']/tbody/tr"));
		
		Thread.sleep(50);
		for (i=0; i<tablevalues.size(); i++)
		{
			
			String empdetails = tablevalues.get(i).getText();
		
			String empfirstname = empdetails.split(" ")[1].trim();
			String emplastname = empdetails.split(" ")[2].trim();
			String dependents = empdetails.split(" ")[3].trim();
			String salary = empdetails.split(" ")[4].trim();
			String grosspay = empdetails.split(" ")[5].trim();
			String benefits = empdetails.split(" ")[6].trim();
			String netpay = empdetails.split(" ")[7].trim();
		
			if (empfirstname.equalsIgnoreCase(verifyfirstname) && emplastname.equalsIgnoreCase(verifylastname) && dependents.equalsIgnoreCase(verifydependents) && salary.equalsIgnoreCase(verifysalary) && grosspay.equalsIgnoreCase(Verifygrosspay) && benefits.equalsIgnoreCase(Verifybenefits) && netpay.equalsIgnoreCase(Verifynetpay))
			{
				System.out.println("Passed : Employee Details matched at " +i +"th Row");
				break;
			}
			
			
	}
    }
	
	
	@DataProvider
	public Object[][] getValidateUser() {
		Object [][] data = new Object [1][7];
		data[0][0]="firstname22";
		data[0][1]="lastname22";
		data[0][2]="2";
		data[0][3]="52000.00";
		data[0][4]="2000.00";
		data[0][5]="76.92";
		data[0][6]="1923.08";
		return data;
	}
	
	/*User Deletion Functionality */
	@Test(dependsOnMethods={"userdetailsEdit"}, dataProvider="DeleteUser", alwaysRun = true)
    public void userdetailsWipeOut(String deleteuserfn, String deleteuserln) {
		
		int i;
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
	
		List<WebElement> tablevalues = driver.findElements(By.xpath("//table[@id='employeesTable']/tbody/tr"));
		List<WebElement> alldeleteicons = driver.findElements(By.xpath("//i[@class= 'fas fa-times']"));
		for (i=0; i<tablevalues.size(); i++)
		{
			
			String empdetails = tablevalues.get(i).getText();
		
			String empfirstname = empdetails.split(" ")[1].trim();
			String emplastname = empdetails.split(" ")[2].trim();
		
			
			
			if (empfirstname.equalsIgnoreCase(deleteuserfn) && emplastname.equalsIgnoreCase(deleteuserln)){
				
				alldeleteicons.get(i).click();
				Assert.assertEquals(driver.findElement(By.className("col-sm-12")).getText(), "Delete employee record for "+empfirstname +(" ")+emplastname+("?"));
				driver.findElement(By.id("deleteEmployee")).click()	;
				
				System.out.println("Delete functionality passed");
				break;
			}
			
		}
		
	}
	@DataProvider
	public Object[][] DeleteUser() {
		Object [][] data = new Object [1][2];
		data[0][0]="firstname22";
		data[0][1]="lastname22";
		
		return data;
	}
	
	

	@AfterTest
	public void closeWindow() {
		driver.close();

	}

}
