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
	
	//This method will click on the desired course and navigate to Course Details page
	public void coursePage() 
	{
		driver.findElement(By.linkText("My courses")).click();
		driver.findElement(By.linkText("Selenium")).click();
		driver.findElement(By.linkText("Course description")).click();
	}
	
	// This method will enter course title and description and Save it. It takes 2 parameters title & description.
	public void enterCourseDesc(String title,String description) throws InterruptedException
	{
		driver.findElement(By.xpath("//*[@id=\"content-section\"]/div/div[2]/a[1]/img")).click();
		driver.findElement(By.id("course_description_title")).clear();
		driver.findElement(By.id("course_description_title")).sendKeys(title);//pass Course title here
		Thread.sleep(5000);
		driver.switchTo().frame(0); // Switching to text editor frame
		WebElement tEditor = driver.switchTo().activeElement();
		new Actions(driver).moveToElement(tEditor).perform();
		driver.findElement(By.xpath("/html/body")).clear();
		driver.findElement(By.xpath("/html/body")).sendKeys(description);//pass course description here
		driver.switchTo().window(driver.getWindowHandle()); // swtiching back to page from editor frame
		Thread.sleep(5000);
		driver.findElement(By.id("course_description_submit")).click(); //Submitting the changes
	
	}
}
