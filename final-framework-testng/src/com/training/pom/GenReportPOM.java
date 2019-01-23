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
		
		@FindBy(xpath="//div[@id='menuone']/ul/li[4]/a")
		private WebElement reportingTab;
		
		@FindBy(linkText="Followed students")
		private WebElement followedStu;
		
		@FindBy(xpath="//input[@id='search_user_keyword']")
		private WebElement studentSearch;
		
		@FindBy(xpath="//button[@id='search_user_submit']")
		private WebElement searchSubmit;
		
		@FindBy(xpath="/html/body/div[1]/section/div/div[3]/table[1]/tbody/tr[2]/td[1]")
		private WebElement stuName;
		
		@FindBy(xpath="/html/body/div[1]/section/div/div[3]/table[1]/tbody/tr[2]/td[5]/a/img")
		private WebElement stuDetails;
		
		//This method will navigate to the Reporting Tab and verify if keyword search works for followed Students. Then navigate to Learner Details page.
		public Boolean reportTab(String key) throws InterruptedException 
		{
			this.reportingTab.click();
			this.followedStu.click();
			this.studentSearch.sendKeys(key); // Send Student name in the Keyword TextBox
			this.searchSubmit.click();
			Thread.sleep(3000); // Wait for search results
			String name = this.stuName.getText();
			this.stuDetails.click();
			if (name.matches(key)) // checking if student name matches with the search keyword
				return true;
			else
				return false;
		}
		
		@FindBy(xpath="//*[@id=\\\"content-section\\\"]/div/div[4]/table")
		private WebElement coursesTable;
		
		@FindBy(xpath="/html/body/div[1]/section/div/div[4]/table/tbody/tr[4]/td[7]/a/img")
		private WebElement courseArrow;
		
		// This method will validate contents of the learner details page and Select a Course
		public Boolean learnerDetails() throws InterruptedException
		{
			js = (JavascriptExecutor) driver;
			Thread.sleep(5000);//Wait for page to Load
			List<WebElement> rows = this.coursesTable.findElements(By.tagName("tr"));
			System.out.println("Courses Subscribed Are as Below: "); //Extracting available course Names
			String cName;
			for (int i=1;i<=rows.size()-1;i++)
			{
				cName = this.coursesTable.findElement(By.xpath("/html/body/div[1]/section/div/div[4]/table/tbody/tr["+i+"]/td[1]/a")).getText();
				System.out.println(cName);
			}
			Boolean course = false;
			if(rows.size()!=0)
			{
				course = true;
				js.executeScript("arguments[0].scrollIntoView();", this.courseArrow); // Scroll down till the element is visible
			this.courseArrow.click();
			}
			return course;
		}
		
		@FindBy(xpath="/html/body/div[1]/section/div/div[1]/ul/li[4]")
		private WebElement lcdPageInfo;
		
		@FindBy(xpath="//*[@id=\\\"content-section\\\"]/div/div[4]")
		private WebElement div1;
		
		//This method will verify contents of "learner Course Details Page" and click on Test attempted
		public Boolean learnerCourseDetails() 
		{
			String pageInfo = this.lcdPageInfo.getText();
			System.out.println("We are on Page: "+pageInfo);
			WebElement table2 = this.div1.findElement(By.tagName("table"));
			js.executeScript("arguments[0].scrollIntoView();", table2);// Scroll down till element is visible
			String testName = table2.findElement(By.xpath("//*[@id=\"content-section\"]/div/div[4]/table/tbody/tr/td[1]")).getText();
			System.out.println("Clicking Test attempt for :"+testName);
			Boolean tName = true;
			if(testName.isEmpty())
			{
				tName = false;
			}
			else
			table2.findElement(By.xpath("//img[@alt='quiz.png']")).click(); // Clicking on Test Quiz Icon
			return tName;
		}
			
		@FindBy(xpath="/html/body/div[1]/section/div/div[2]/h2")
		private WebElement quizInfoPage;
		
		@FindBy(xpath="//form[@id='myform']/fieldset/div/div/label/input")
		private WebElement sendCheckBox;
		
		@FindBy(xpath="//*[@id=\"myform_submit\"]")
		private WebElement emailSubmit;
		
		@FindBy(xpath="//*[@id=\"content-section\"]/div/div[2]")
		private WebElement successMesg;
		
		//This method will verify the quizDetails page and send email
		public String quizDetailsPage() throws InterruptedException
		{
			String quizInfo = this.quizInfoPage.getText();
			System.out.println("We Are on Quiz Page: "+quizInfo);
			this.sendCheckBox.click();
			Thread.sleep(5000);//Wait for email editor to load
			this.emailSubmit.click();
			String mesg = this.successMesg.getText();
			return mesg;
		}
		
}
