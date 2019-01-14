package com.training.pom;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
	
	
	//This method will navigate to the User list page for the selected Course
	public void courseUser()
	{
		driver.findElement(By.linkText("My courses")).click();
		driver.findElement(By.linkText("Selenium - Unsubscribe Test")).click();//Enter Course Page
		//driver.findElement(By.xpath("//*[@id=\"toolimage_5343\"]")).click();
		driver.findElement(By.partialLinkText("Users")).click();//clicking on users Icon
		driver.findElement(By.linkText("Learners")).click();//Learners Tab
	}
	
	//This Method will unsubscribe the selected user from the Learners Tab
	public String unSubscribeUser() throws InterruptedException
	{
		driver.findElement(By.xpath("//input[@name='user[]']")).click();//Click on checkbox against the username
		//driver.findElement(By.xpath("//a[contains(text(),'Unsubscribe')]")).click();
		driver.findElement(By.linkText("Unsubscribe")).click();//click unsubscribe button
		Thread.sleep(3000);
		driver.switchTo().alert().accept();// Clicking OK for Alert Window
		//String uscribe = "User is now unsubscribed";
		String actual = driver.findElement(By.xpath("//*[@id=\"content-section\"]/div/div[2]")).getText();//Extracting Message
		//assertEquals(uscribe,actual);
		return actual;
		}

}
