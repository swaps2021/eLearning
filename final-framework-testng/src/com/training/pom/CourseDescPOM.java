package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CourseDescPOM {
private WebDriver driver; 
	
	public CourseDescPOM(WebDriver driver) {
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
	
	
	@FindBy(linkText="My courses")
	private WebElement myCourses; 
	
	@FindBy(linkText="Selenium")
	private WebElement seleniumCourse; 
	
	@FindBy(linkText="Course description")
	private WebElement courseDescrIcon;
	
	//This method will click on the desired course, then click on course Description Icon
	public void coursePage() 
	{
		this.myCourses.click();
		this.seleniumCourse.click();
		this.courseDescrIcon.click();
	}
	
	@FindBy(xpath="/html/body/div[1]/section/div/div[2]/a[1]/img")
	private WebElement descrIcon;
	
	@FindBy(id="course_description_title")
	private WebElement descrTitle;
	
	@FindBy(id="course_description_submit")
	private WebElement descrSubmit;
	
	// This method will enter course title and description and Save it. It takes 2 parameters title & description.
	public void enterCourseDesc(String title,String description) throws InterruptedException
	{
		this.descrIcon.click();
		this.descrTitle.clear();
		this.descrTitle.sendKeys(title);//pass Course title here
		Thread.sleep(5000);
		driver.switchTo().frame(0); // Switching to text editor frame
		WebElement tEditor = driver.switchTo().activeElement();
		new Actions(driver).moveToElement(tEditor).perform();
		driver.findElement(By.xpath("/html/body")).clear();
		driver.findElement(By.xpath("/html/body")).sendKeys(description);//pass course description here
		driver.switchTo().window(driver.getWindowHandle()); // switching back to page from editor frame
		Thread.sleep(5000);
		this.descrSubmit.click(); //Submitting the changes
	
	}
}
