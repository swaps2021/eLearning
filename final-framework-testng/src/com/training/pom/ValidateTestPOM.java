package com.training.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ValidateTestPOM {
private WebDriver driver; 
	
	public ValidateTestPOM(WebDriver driver) {
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
	
	@FindBy(linkText="Selenium - Tests")
	private WebElement courseName;
	
	@FindBy(linkText="Tests")
	private WebElement testsIcon;
	
	@FindBy(css="img[alt=\"Results and feedback\"]")
	private WebElement rfIcon;
	
	//This method will navigate to the course tests page and select the Test to Validate
	public void enterCourseTests() throws InterruptedException 
	{
		this.myCourses.click();
		this.courseName.click();
		this.testsIcon.click();
		this.rfIcon.click();//Click on Results and feedback icon against the Test
		Thread.sleep(5000);
	}
	
	@FindBy(name="send_notification")
	private WebElement sendCheckBox;
	
	@FindBy(xpath="//*[@id=\"notification_content\"]")
	private WebElement emailBody;
	
	// This method will validate the Test submitted by a learner and return the email message contents
	public String validateTest() throws InterruptedException
	{
		List<WebElement> rows = driver.findElements(By.xpath("//*[@id=\"results\"]/tbody/tr"));
		Boolean status = false;
		String val = "Not validated";
		int i;
		for(i =2;i<=rows.size();i++)
		{
			status = val.matches(driver.findElement(By.xpath("/html/body/div[1]/section/div/div[3]/div[3]/div[3]/div/table/tbody/tr["+i+"]/td[10]/span")).getText());
			if(status)
			{
				driver.findElement(By.xpath("/html/body/div[1]/section/div/div[3]/div[3]/div[3]/div/table/tbody/tr["+i+"]/td[12]/div/a[1]/img")).click();
				break;
				
			}
		}
		this.sendCheckBox.click();
		Thread.sleep(5000);
		
		String eText = this.emailBody.getAttribute("value");//Extract email contents from textEditor
		return eText;
		}
	
	@FindBy(xpath="//*[@id=\"myform_submit\"]")
	private WebElement emailSubmit;
	
	@FindBy(xpath="//*[@id=\"content-section\"]/div/div[2]")
	private WebElement sentMessage;
	
	//This method will send the email and return the success message
	public String sendEmail() 
	{
		this.emailSubmit.click();
		String mesg = this.sentMessage.getText();
		return mesg;
	}
}
