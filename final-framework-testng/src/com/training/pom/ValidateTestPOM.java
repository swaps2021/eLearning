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
	
	//This method will navigate to the course tests page and select the Test to Validate
	public void enterCourseTests() throws InterruptedException 
	{
		driver.findElement(By.linkText("My courses")).click();
		driver.findElement(By.linkText("Selenium - Tests")).click();
		driver.findElement(By.linkText("Tests")).click();
		driver.findElement(By.cssSelector("img[alt=\"Results and feedback\"]")).click();
		Thread.sleep(5000);
	}
	
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
		
		driver.findElement(By.name("send_notification")).click();
		Thread.sleep(5000);
		
		//Extract email contents from textEditor
		WebElement textArea = driver.findElement(By.xpath("//*[@id=\"notification_content\"]"));
		String eText = textArea.getAttribute("value");
		return eText;
		}
	
	//This method will send the email and return the message
	public String sendEmail() 
	{
		driver.findElement(By.xpath("//*[@id=\"myform_submit\"]")).click();
		String mesg = driver.findElement(By.xpath("//*[@id=\"content-section\"]/div/div[2]")).getText();
		return mesg;
	}
}
