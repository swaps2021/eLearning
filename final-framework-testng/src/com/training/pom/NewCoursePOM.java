package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class NewCoursePOM {
private WebDriver driver; 
	
	public NewCoursePOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="login")
	private WebElement userName; 
	
	@FindBy(id="password")
	private WebElement password;
	
	@FindBy(id="formLogin_submitAuth")
	private WebElement loginBtn; 
	
	public void sendUserName(String userName) {
		this.userName.clear();
		this.userName.sendKeys(userName);
	}
	
	public void sendPassword(String password) {
		this.password.clear(); 
		this.password.sendKeys(password); 
	}
	
	public void clickLoginBtn() {
		this.loginBtn.click(); 
	}
	
	
	//This method will create a new course with the 3 parameters passes: title, category and code
	public void createNewCourse(String title,String category, String code)
	{
		driver.findElement(By.linkText("Create a course")).click();
		driver.findElement(By.id("title")).sendKeys(title);
		driver.findElement(By.id("advanced_params")).click();
		//Below webelement captures the 3rd option from dropdown
		WebElement categ = driver.findElement(By.xpath("/html/body/div[1]/section/div/div[2]/div/section[2]/form/fieldset/div[3]/div[1]/div[1]/div/button"));
		categ.click();
		
		driver.findElement(By.id("add_course_wanted_code")).sendKeys(code);
		driver.findElement(By.id("add_course_submit")).click(); //Submit for creating new course
	}
	
	//This method will enter course into details
	public void courseIntro(String text) throws InterruptedException
	{
		driver.findElement(By.xpath("/html/body/div[1]/section/div/div[2]/div/section/div/div[1]/div/div[1]/a/em")).click();
		Thread.sleep(5000);
		driver.switchTo().frame(0);// Switching to intro editor frame
		WebElement tEditor = driver.switchTo().activeElement();
		new Actions(driver).moveToElement(tEditor).perform();
		driver.findElement(By.xpath("/html/body")).sendKeys(text);
		driver.switchTo().window(driver.getWindowHandle());// exiting from editor frame to page
		Thread.sleep(3000);
		driver.findElement(By.id("introduction_text_intro_cmdUpdate")).click();//Updating the Course Intro
		}

}
