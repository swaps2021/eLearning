package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TeachLoginPOM {
private WebDriver driver; 
	
	public TeachLoginPOM(WebDriver driver) {
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
	
	@FindBy(id="homepage-course")
	private WebElement homePageText; 
	
	//This method will check the welcome text in HomePage after Teacher Login
	public boolean checkWelcomeText() {
		String hometext = this.homePageText.getText();
		return hometext.startsWith("Hello tester1 tester1 and welcome");// Extracting the desired welcome text from the page
			}
}



