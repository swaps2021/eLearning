package com.training.pom;

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
	
	@FindBy(linkText="My courses")
	private WebElement myCourses; 
	
	@FindBy(linkText="Selenium - Tests")
	private WebElement courseName; 
	
	@FindBy(id="tooldesc_6172")
	private WebElement reportIcon; 
	
	//This method will navigate to the course page and select the Reporting Icon
	public void enterCourse() throws InterruptedException 
	{
		this.myCourses.click();
		this.courseName.click();
		this.reportIcon.click();
		Thread.sleep(5000);
	}
	
	@FindBy(xpath="/html/body/div[1]/section/div/div[3]/div[2]/div[2]/div/table[1]/tbody/tr[2]/td[16]/center/a/img")
	private WebElement arrow; 
	
	// This method will validate contents of the learner Report and Select a Learner
	public void reportPage() throws InterruptedException
	{
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", this.arrow); // scroll down till element is visible
		Thread.sleep(3000);
		this.arrow.click();
	}
	
	@FindBy(xpath="//*[@id=\\\"content-section\\\"]/div/h3")
	private WebElement studentName; 
	
	@FindBy(xpath="/html/body/div[1]/section/div/div[4]/table/tbody/tr/td[1]")
	private WebElement testName;
	
	@FindBy(css="#content-section > div > div:nth-child(6) > table > tbody > tr > td:nth-child(5) > a > img")
	private WebElement quizIcon;
	
	@FindBy(xpath="/html/body/div[1]/section/div/div[2]/h2")
	private WebElement pageTitle;
	
	//This method will verify contents of learner Details and navigate to test Quiz
	public String learnerDetails() 
	{
		String stuName = this.studentName.getText();
		System.out.println("Reporting for Student: "+stuName);
		String testN = this.testName.getText();
		System.out.println("Test attempted: "+testN);
		this.quizIcon.click();
		String pageT = this.pageTitle.getText();
		return pageT;
	}
	
	@FindBy(name="send_notification")
	private WebElement sendCheckBox;
	
	@FindBy(id="myform_submit")
	private WebElement emailSubmit;
	
	@FindBy(xpath="/html/body/div[1]/section/div/div[2]")
	private WebElement successMesg;
	
	//This method will send email from Quiz Page and return success message
	public String sendEmail() throws InterruptedException
	{
		js.executeScript("arguments[0].scrollIntoView();", sendCheckBox);
		this.sendCheckBox.click();
		Thread.sleep(5000); // Wait for email text editor
		this.emailSubmit.click();
		String mesg = this.successMesg.getText();
		return mesg;
	}
	
	@FindBy(xpath="/html/body/div[1]/section/div/div[1]/ul/li[1]/a")
	private WebElement coursePage;
	
	//This method will navigate to the course Page and return page title
	public String coursePage()
	{
		this.coursePage.click();
		String cPage = driver.getTitle();
		return cPage;
	}
}
