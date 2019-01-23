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
	
	@FindBy(linkText="Create a course")
	private WebElement newCourse; 
	
	@FindBy(id="title")
	private WebElement coursTitle; 
	
	@FindBy(id="advanced_params")
	private WebElement advParams;
	
	@FindBy(xpath="/html/body/div[1]/section/div/div[2]/div/section[2]/form/fieldset/div[3]/div[1]/div[1]/div/button")
	private WebElement projCateg;
	
	@FindBy(id="add_course_wanted_code")
	private WebElement courseCode;
	
	@FindBy(id="add_course_submit")
	private WebElement courseSubmit;
	
	//This method will create a new course with the 3 parameters passed: title, category and code
	public void createNewCourse(String title,String category, String code)
	{
		this.newCourse.click();
		this.coursTitle.sendKeys(title);
		this.advParams.click();
		this.projCateg.click();//Capture the 3rd option(Projects)
		this.courseCode.sendKeys(code);
		this.courseSubmit.click(); //Submit for creating new course
	}
	
	@FindBy(xpath="/html/body/div[1]/section/div/div[2]/div/section/div/div[1]/div/div[1]/a/em")
	private WebElement introButton;
	
	@FindBy(id="introduction_text_intro_cmdUpdate")
	private WebElement updateIntro;
	
	//This method will enter course into details
	public void courseIntro(String text) throws InterruptedException
	{
		this.introButton.click();
		Thread.sleep(5000);//Waiting for text editor to open
		driver.switchTo().frame(0);// Switching to editor frame
		WebElement tEditor = driver.switchTo().activeElement();
		new Actions(driver).moveToElement(tEditor).perform();
		driver.findElement(By.xpath("/html/body")).sendKeys(text);
		driver.switchTo().window(driver.getWindowHandle());// exiting from editor frame to page
		Thread.sleep(3000);
		this.updateIntro.click();//Updating the Course Intro
		}

}
