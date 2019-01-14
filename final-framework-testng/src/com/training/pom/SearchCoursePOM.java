package com.training.pom;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchCoursePOM {
private WebDriver driver; 
	
	public SearchCoursePOM(WebDriver driver) {
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
	
	
	
	public void searchCourse() throws InterruptedException
	{
		driver.findElement(By.linkText("Course catalog")).click();
		driver.findElement(By.name("search_term")).sendKeys("Selenium");
		driver.findElement(By.xpath("//*[@id=\"content-section\"]/div/div[2]/div[1]/div/div/div[1]/form/div/div/button/em")).click();
	
		//List<WebElement> courses;
		//int size;
		//List<WebElement> pages = driver.findElements(By.xpath("//*[@id=\"content-section\"]/div/div/nav/ul/li"));
		/*for(int i=1;i<=pages.size();i++)
		{
			driver.findElement(By.xpath("//a[contains(text(),"+i+")]")).click();
			Thread.sleep(3000);
			courses = driver.findElements(By.partialLinkText("selenium"));
			size = courses.size();
			System.out.println("Page:"+i+" has"+size+" Selenium Courses");
		}*/
	}
	
}
