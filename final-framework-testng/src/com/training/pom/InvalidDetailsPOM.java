package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InvalidDetailsPOM {
private WebDriver driver; 
	
	public InvalidDetailsPOM(WebDriver driver) {
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
		
	@FindBy(linkText="Administration")
	private WebElement adminTab;
	
	@FindBy(xpath="//*[@id=\"tabs-1\"]/div/div[2]/div[2]/ul/li[2]/a")
	private WebElement addUserLink;
	
	@FindBy(xpath="//*[@id=\"firstname\"]")
	private WebElement enterFirstName;
	
	@FindBy(xpath="//*[@id=\"lastname\"]")
	private WebElement enterLastName;
	
	@FindBy(xpath="//*[@id=\"email\"]")
	private WebElement enterEmailId;
	
	@FindBy(xpath="//input[@id='phone']")
	private WebElement enterPhone;
	
	@FindBy(xpath="//*[@id=\"username\"]")
	private WebElement enterUserName;
	
	@FindBy(xpath="//fieldset[1]//div[9]//div[1]//div[2]//label[1]/input[1]")
	private WebElement passwordRadio;
	
	@FindBy(xpath="//*[@id=\"password\"]")
	private WebElement enterPassword;
	
	@FindBy(xpath="//*[@id=\"user_add\"]/fieldset/div[35]/div[1]/button[1]/em")
	private WebElement addUserButton;
	
	@FindBy(xpath="//div[@class='alert alert-warning']")
	private WebElement addedMesg;
	
	@FindBy(xpath="//*[@id=\"users5c4fee8a514ee\"]")
	private WebElement userTable;
	
	public String addNewUser(String fName, String lName, String emailId, String phone,String uName, String pwd) throws InterruptedException
	{
		this.adminTab.click();
		WebElement usersTab = driver.findElement(By.xpath("//*[@id=\"tabs-1\"]"));
		usersTab.findElement(By.linkText("Add a user")).click();
		this.enterFirstName.sendKeys(fName);
		this.enterLastName.sendKeys(lName);
		this.enterEmailId.sendKeys(emailId);
		this.enterPhone.sendKeys(phone);
		this.enterUserName.sendKeys(uName);
		this.passwordRadio.click();
		this.enterPassword.sendKeys(pwd);
		this.addUserButton.click();
		Thread.sleep(5000);
		String mesg = this.addedMesg.getText();
		System.out.println(mesg);
		
		return mesg;
		
	}
}
