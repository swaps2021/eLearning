package com.training.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewProjectPOM {
	private WebDriver driver;
		
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
		
		@FindBy(linkText="My courses")
		private WebElement myCourses;
		
		@FindBy(linkText="Selenium - Tests")
		private WebElement courseName;
		
		@FindBy(linkText="Projects")
		private WebElement projectsLink;
		
		@FindBy(xpath="/html/body/div[1]/section/div/div[2]/a/img")
		private WebElement createProjIcon;
		
		@FindBy(xpath="//*[@id=\"add_blog_blog_name\"]")
		private WebElement projTitle;
		
		@FindBy(xpath="//*[@id=\"add_blog_blog_subtitle\"]")
		private WebElement projSubTitle;
		
		@FindBy(xpath="//*[@id=\"add_blog_submit\"]")
		private WebElement submitBlog;
		
		@FindBy(xpath="/html/body/div[1]/section/div/div[3]")
		private WebElement submitMesg;
		
		//This method will allow Teacher to create a New Project from Course Page
		public Boolean createProj(String pTitle, String pSubtitle) throws InterruptedException 
		{
			this.myCourses.click();
			this.courseName.click();
			this.projectsLink.click();
			this.createProjIcon.click();
			Thread.sleep(3000);
			this.projTitle.sendKeys(pTitle);
			this.projSubTitle.sendKeys(pSubtitle);
			this.submitBlog.click();
			Thread.sleep(3000);
			String mesg = this.submitMesg.getText();
			if (mesg.matches("The project has been added."))
				return true;
			else
				return false;
		}
		
		@FindBy(xpath="//*[@id=\"content-section\"]/div/div[4]")
		private WebElement d1;
		
		@FindBy(xpath="/html/body/div[1]/section/div/div[2]/div/section/div/div[2]/div/div[2]/article/div/div/h3/a")
		private WebElement welcomeMesg;
		
		//This method will validate the welcome message after clicking the project name link
		public Boolean welcomeMessage()
		{
			WebElement table1 = this.d1.findElement(By.tagName("table"));
			WebElement row = table1.findElement(By.linkText("e-Learning7"));
			row.click();
			String wMesg = this.welcomeMesg.getText();
			if (wMesg.matches("Welcome !"))
				return true;
			else
				return false;
		}
		
		@FindBy(xpath="/html/body/div[1]/section/div/div[2]/div/div/div/div/a[2]/img")
		private WebElement newTaskButton;
		
		@FindBy(xpath="//*[@id=\"add_post_title\"]")
		private WebElement taskTitle;
		
		@FindBy(xpath="//*[@id=\"add_post_save\"]")
		private WebElement saveTaskButton;
		
		@FindBy(xpath="/html/body/div[1]/section/div/div[2]/div/div[2]")
		private WebElement savedMessage;
		
		//This method will create a new Task inside the Project
		public Boolean newTask(String title)
		{
			this.newTaskButton.click();
			this.taskTitle.sendKeys(title);
			this.saveTaskButton.click();
			String mesg = this.savedMessage.getText();
			if(mesg.matches("The article has been added."))
				return true;
			else
				return false;
		}
		
		@FindBy(xpath="/html/body/div[1]/section/div/div[2]/div/div[1]/div/div/a[3]/img")
		private WebElement rolesMgmntIcon;
		
		@FindBy(xpath="/html/body/div[1]/section/div/div[2]/div/section/div/div/div/div/a[1]")
		private WebElement addNewRoleIcon;
		
		@FindBy(xpath="/html/body/div[1]/section/div/div[2]/div/section/div/div/div/form/div[1]/div/input")
		private WebElement roleTitle;
		
		@FindBy(xpath="/html/body/div[1]/section/div/div[2]/div/section/div/div/div/form/div[5]/div/button")
		private WebElement saveRoleButton;
		
		@FindBy(xpath="/html/body/div[1]/section/div/div[2]/div/div[2]")
		private WebElement roleMessage;
		
		//This method will create a new Role inside the Project
				public Boolean newRole(String title)
				{
					this.rolesMgmntIcon.click();
					this.addNewRoleIcon.click();
					this.roleTitle.sendKeys(title);
					this.saveRoleButton.click();
					String mesg = this.roleMessage.getText();
					if(mesg.matches("The task has been created"))
						return true;
					else
						return false;
				}
		
		@FindBy(xpath="/html/body/div[1]/section/div/div[2]/div/section/div/div/div/div/a[2]")
		private WebElement assignRole;	
		
		@FindBy(xpath="//*[@id=\"assign_task_submit\"]")
		private WebElement assignSubmit;
		
		@FindBy(xpath="/html/body/div[1]/section/div/div[2]/div/div[2]")
		private WebElement submitMessage2;
		
		//This method will assign a new Role to a project member
				public Boolean assignRole()
				{
					this.assignRole.click();
					this.assignSubmit.click();
					System.out.println("Role Assigned - Submitted");
					String mesg = this.submitMessage2.getText();
					if(mesg.matches("The task has been assigned."))
						return true;
					else
						return false;
				}
		
		@FindBy(xpath="/html/body/div[1]/section/div/div[2]/div/div[1]/div/div/a[4]/img")
		private WebElement usersMgmntIcon;
		
		@FindBy(xpath="/html/body/div[1]/section/div/div[2]/div/div[2]")
		private WebElement registerMessage;
		
		//This method will subscribe/register a new user to project
				public Boolean userMgmt()
				{
					this.usersMgmntIcon.click();
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
					String mesg = this.registerMessage.getText();
					if(mesg.matches("The user has been registered"))
						return true;
					else
						return false;
				}
		
}
