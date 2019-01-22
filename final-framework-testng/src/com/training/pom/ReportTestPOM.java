package com.training.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReportTestPOM {
private WebDriver driver;
private JavascriptExecutor js;
	
	public ReportTestPOM(WebDriver driver) {
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
	
	//This method will navigate to the course tests page and select the Reporting Icon
	public void enterCourse() throws InterruptedException 
	{
		driver.findElement(By.linkText("My courses")).click();
		driver.findElement(By.linkText("Selenium - Tests")).click();
		driver.findElement(By.id("tooldesc_6172")).click();
		Thread.sleep(5000);
	}
	
	// This method will validate contents of the learner Report and Select a Learner
	public void reportPage() throws InterruptedException
	{
		js = (JavascriptExecutor) driver;
		WebElement arrow = driver.findElement(By.xpath("/html/body/div[1]/section/div/div[3]/div[2]/div[2]/div/table[1]/tbody/tr[2]/td[16]/center/a/img"));
		js.executeScript("arguments[0].scrollIntoView();", arrow);
		Thread.sleep(3000);
		arrow.click();
	}
	
	//This method will verify contents of learner Details
	public String learnerDetails() 
	{
		String stuName = driver.findElement(By.xpath("//*[@id=\"content-section\"]/div/h3")).getText();
		System.out.println("Reporting for Student: "+stuName);
		String testName = driver.findElement(By.xpath("/html/body/div[1]/section/div/div[4]/table/tbody/tr/td[1]")).getText();
		System.out.println("Test attempted: "+testName);
		driver.findElement(By.cssSelector("#content-section > div > div:nth-child(6) > table > tbody > tr > td:nth-child(5) > a > img")).click();
		String pageTitle = driver.findElement(By.xpath("/html/body/div[1]/section/div/div[2]/h2")).getText();
		return pageTitle;
	}
	
	//This method will send email from Quiz Page
	public String sendEmail() throws InterruptedException
	{
		WebElement sendCheckBox = driver.findElement(By.name("send_notification"));
		js.executeScript("arguments[0].scrollIntoView();", sendCheckBox);
		sendCheckBox.click();
		Thread.sleep(5000);
		driver.findElement(By.id("myform_submit")).click();
		String mesg = driver.findElement(By.xpath("/html/body/div[1]/section/div/div[2]")).getText();
		return mesg;
	}
	
	//This method will navigate to the course Page
	public String coursePage()
	{
		driver.findElement(By.xpath("/html/body/div[1]/section/div/div[1]/ul/li[1]/a")).click();
		String cPage = driver.getTitle();
		return cPage;
	}
}
