package com.training.pom;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UnSubscribeuserPOM {
private WebDriver driver; 
	
	public UnSubscribeuserPOM(WebDriver driver) {
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
	private WebElement mycourses;
	
	@FindBy(linkText="Selenium - Unsubscribe Test")
	private WebElement courseName; 
	
	@FindBy(partialLinkText="Users")
	private WebElement usersLink;
	
	@FindBy(linkText="Learners")
	private WebElement learnersLink;
	
	//This method will navigate to the User list page for the selected Course
	public void courseUser()
	{
		this.mycourses.click();
		this.courseName.click();//Enter Course Page
		this.usersLink.click();//clicking on users Icon
		this.learnersLink.click();//Enter Learners Tab
	}
	
	@FindBy(xpath="//input[@name='user[]']")
	private WebElement userCheckBox;
	
	@FindBy(linkText="Unsubscribe")
	private WebElement unsubscribe;
	
	@FindBy(xpath="/html/body/div[1]/section/div/div[2]")
	private WebElement successMesg;
	
	//This Method will unsubscribe the selected user from the Learners Tab
	public String unSubscribeUser() throws InterruptedException
	{
		this.userCheckBox.click();//Click on checkbox against the username
		this.unsubscribe.click();//click unsubscribe button
		Thread.sleep(3000);
		driver.switchTo().alert().accept();// Clicking OK for Alert Window
		String actual = this.successMesg.getText();//Extracting Message
		return actual; //returning the message text
		}

}
