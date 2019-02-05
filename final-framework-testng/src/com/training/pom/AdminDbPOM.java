package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminDbPOM {
private WebDriver driver; 
	
	public AdminDbPOM(WebDriver driver) {
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
	
	@FindBy(xpath="//*[@id=\"username\"]")
	private WebElement enterUserName;
	
	@FindBy(xpath="//*[@id=\"qf_76b786\"]")
	private WebElement passwordRadio;
	
	@FindBy(xpath="//*[@id=\"password\"]")
	private WebElement enterPassword;
	
	@FindBy(xpath="//*[@id=\"user_add\"]/fieldset/div[35]/div[1]/button[1]/em")
	private WebElement addUserButton;
	
	@FindBy(xpath="//*[@id=\"content-section\"]/div/div[2]/div/div[2]")
	private WebElement addedMesg;
	
	@FindBy(xpath="//*[@id=\"users5c4fee8a514ee\"]")
	private WebElement userTable;
	
	public void addNewUser(String fName, String lName, String emailId, String phone, String uName, String pwd) throws InterruptedException
	{
		this.adminTab.click();
		WebElement usersTab = driver.findElement(By.xpath("//*[@id=\"tabs-1\"]"));
		usersTab.findElement(By.linkText("Add a user")).click();
		this.enterFirstName.sendKeys(fName);
		this.enterLastName.sendKeys(lName);
		this.enterEmailId.sendKeys(emailId);
		this.enterUserName.sendKeys(uName);
		driver.findElement(By.xpath("//form[@id='user_add']/fieldset/div[9]/div/div[2]/label/input")).click();
		//this.passwordRadio.click();
		this.enterPassword.sendKeys(pwd);
		this.addUserButton.click();
		Thread.sleep(5000);
		String mesg = this.addedMesg.getText();
		System.out.println(mesg);
		
	}
}
