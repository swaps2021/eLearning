package com.training.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewProjectPOM {
	private WebDriver driver;
	private JavascriptExecutor js;
		
		public NewProjectPOM(WebDriver driver) {
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
		
		//This method will allow Teacher to create a New Project
		public Boolean createProj(String pTitle, String pSubtitle) throws InterruptedException 
		{
			driver.findElement(By.linkText("My courses")).click();
			driver.findElement(By.linkText("Selenium - Tests")).click();
			driver.findElement(By.linkText("Projects")).click();
			driver.findElement(By.xpath("/html/body/div[1]/section/div/div[2]/a/img")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id=\"add_blog_blog_name\"]")).sendKeys(pTitle);
			driver.findElement(By.xpath("//*[@id=\"add_blog_blog_subtitle\"]")).sendKeys(pSubtitle);
			driver.findElement(By.xpath("//*[@id=\"add_blog_submit\"]")).click();
			Thread.sleep(3000);
			String mesg = driver.findElement(By.xpath("/html/body/div[1]/section/div/div[3]")).getText();
			if (mesg.matches("The project has been added."))
				return true;
			else
				return false;
		}
		
		//This method will create a new Task after click on the new Project link
		public Boolean welcomeMessage()
		{
			WebElement div1 = driver.findElement(By.xpath("//*[@id=\"content-section\"]/div/div[4]"));
			WebElement table1 = div1.findElement(By.tagName("table"));
			WebElement row = table1.findElement(By.linkText("e-Learning3"));
			row.click();
			WebElement welcome = driver.findElement(By.xpath("/html/body/div[1]/section/div/div[2]/div/section/div/div[2]/div/div[2]/article/div/div/h3/a"));
			String wMesg = welcome.getText();
			if (wMesg.matches("Welcome !"))
				return true;
			else
				return false;
		}
		
		//This method will create a new Task inside the Project
		public Boolean newTask(String title)
		{
			driver.findElement(By.xpath("/html/body/div[1]/section/div/div[2]/div/div/div/div/a[2]/img")).click();
			driver.findElement(By.xpath("//*[@id=\"add_post_title\"]")).sendKeys(title);
			driver.findElement(By.xpath("//*[@id=\"add_post_save\"]")).click();
			String mesg = driver.findElement(By.xpath("/html/body/div[1]/section/div/div[2]/div/div[2]")).getText();
			if(mesg.matches("The article has been added."))
				return true;
			else
				return false;
		}
		
		//This method will create a new Role inside the Project
				public Boolean newRole(String title)
				{
					driver.findElement(By.xpath("/html/body/div[1]/section/div/div[2]/div/div[1]/div/div/a[3]/img")).click();
					driver.findElement(By.xpath("/html/body/div[1]/section/div/div[2]/div/section/div/div/div/div/a[1]")).click();
					driver.findElement(By.xpath("/html/body/div[1]/section/div/div[2]/div/section/div/div/div/form/div[1]/div/input")).sendKeys(title);
					driver.findElement(By.xpath("/html/body/div[1]/section/div/div[2]/div/section/div/div/div/form/div[5]/div/button")).click();
					String mesg = driver.findElement(By.xpath("/html/body/div[1]/section/div/div[2]/div/div[2]")).getText();
					if(mesg.matches("The task has been created"))
						return true;
					else
						return false;
				}
		
		//This method will assign a new Role to a project member
				public Boolean assignRole()
				{
					driver.findElement(By.xpath("/html/body/div[1]/section/div/div[2]/div/section/div/div/div/div/a[2]")).click();
					//driver.findElement(By.xpath("/html/body/div[1]/section/div/div[2]/div/section/div/div/div/form/fieldset/div[1]/div[1]/div/button/span[1]")).sendKeys("vinod d");
					//driver.findElement(By.xpath("/html/body/div[1]/section/div/div[2]/div/section/div/div/div/form/fieldset/div[1]/div[1]/div/div/ul/li/a/span[1]")).click();
					driver.findElement(By.xpath("//*[@id=\"assign_task_submit\"]")).click();
					System.out.println("Role Assigned - Submitted");
					String mesg = driver.findElement(By.xpath("/html/body/div[1]/section/div/div[2]/div/div[2]")).getText();
					if(mesg.matches("The task has been assigned."))
						return true;
					else
						return false;
				}
				
		//This method will subscribe a new user to project
				public Boolean userMgmt()
				{
					driver.findElement(By.xpath("/html/body/div[1]/section/div/div[2]/div/div[1]/div/div/a[4]/img")).click();
					WebElement div2 = driver.findElement(By.xpath("/html/body/div[1]/section/div/div[2]/div/section/div/div/div"));
					WebElement table2 = div2.findElement(By.tagName("table"));
					List <WebElement> rows = table2.findElements(By.tagName("tr"));
					WebElement reg;
					String subscr;
					for(int i=2;i<=rows.size();i++)
					{
						reg = driver.findElement(By.xpath("/html/body/div[1]/section/div/div[2]/div/section/div/div/div/form[1]/div[1]/table[1]/tbody/tr["+i+"]/td[5]/a"));
						subscr = reg.getText();
						if(subscr.matches("Register"))
						{
							reg.click();
							break;
						}
					}
					
					String mesg = driver.findElement(By.xpath("/html/body/div[1]/section/div/div[2]/div/div[2]")).getText();
					if(mesg.matches("The user has been registered"))
						return true;
					else
						return false;
				}
		
}
