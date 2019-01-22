package com.training.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GenReportPOM {
	private WebDriver driver;
	private JavascriptExecutor js;
		
		public GenReportPOM(WebDriver driver) {
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
		
		//This method will navigate to the Reporting Tab and verify if keyword search works for followed Students
		public Boolean reportTab(String key) throws InterruptedException 
		{
			driver.findElement(By.xpath("//div[@id='menuone']/ul/li[4]/a")).click();
			driver.findElement(By.linkText("Followed students")).click();
			driver.findElement(By.xpath("//input[@id='search_user_keyword']")).sendKeys(key);
			driver.findElement(By.xpath("//button[@id='search_user_submit']")).click();
			Thread.sleep(3000);
			String name = driver.findElement(By.xpath("/html/body/div[1]/section/div/div[3]/table[1]/tbody/tr[2]/td[1]")).getText();
			driver.findElement(By.xpath("/html/body/div[1]/section/div/div[3]/table[1]/tbody/tr[2]/td[5]/a/img")).click();
			if (name.matches(key))
				return true;
			else
				return false;
		}
		
		// This method will validate contents of the learner details page and Select a Course
		public Boolean learnerDetails() throws InterruptedException
		{
			js = (JavascriptExecutor) driver;
			Thread.sleep(5000);
			WebElement table = driver.findElement(By.xpath("//*[@id=\"content-section\"]/div/div[4]/table"));
			List<WebElement> rows = table.findElements(By.tagName("tr"));
			System.out.println("Courses Subscribed Are as Below: ");
			String cName;
			for (int i=1;i<=rows.size()-1;i++)
			{
				cName = table.findElement(By.xpath("/html/body/div[1]/section/div/div[4]/table/tbody/tr["+i+"]/td[1]/a")).getText();
				System.out.println(cName);
			}
			Boolean course = false;
			if(rows.size()!=0)
			{
				course = true;
			WebElement arrow = driver.findElement(By.xpath("/html/body/div[1]/section/div/div[4]/table/tbody/tr[4]/td[7]/a/img"));
			js.executeScript("arguments[0].scrollIntoView();", arrow);
			arrow.click();
			}
			return course;
		}
		
		//This method will verify contents of learner Course Details Page and click on Test attempted
		public Boolean learnerCourseDetails() 
		{
			String pageInfo = driver.findElement(By.xpath("/html/body/div[1]/section/div/div[1]/ul/li[4]")).getText();
			System.out.println("We are on Page: "+pageInfo);
			WebElement div1 = driver.findElement(By.xpath("//*[@id=\"content-section\"]/div/div[4]"));
			WebElement table2 = div1.findElement(By.tagName("table"));
			js.executeScript("arguments[0].scrollIntoView();", table2);
			String testName = table2.findElement(By.xpath("//*[@id=\"content-section\"]/div/div[4]/table/tbody/tr/td[1]")).getText();
			System.out.println("Clicking Test attempt for :"+testName);
			Boolean tName = true;
			if(testName.isEmpty())
			{
				tName = false;
			}
			else
			table2.findElement(By.xpath("//img[@alt='quiz.png']")).click();
			return tName;
		}
				
		//This method will verify the quizDetails page and send email
		public String quizDetailsPage() throws InterruptedException
		{
			String quizInfo = driver.findElement(By.xpath("/html/body/div[1]/section/div/div[2]/h2")).getText();
			System.out.println("We Are on Quiz Page: "+quizInfo);
			driver.findElement(By.xpath("//form[@id='myform']/fieldset/div/div/label/input")).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id=\"myform_submit\"]")).click();
			String mesg = driver.findElement(By.xpath("//*[@id=\"content-section\"]/div/div[2]")).getText();
			return mesg;
		}
		
}
