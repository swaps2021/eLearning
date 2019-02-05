package com.training.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminReportPOM {
	private WebDriver driver; 
	private JavascriptExecutor js;
	public AdminReportPOM(WebDriver driver) {
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
	
	@FindBy(linkText="Selenium2021")
	private WebElement courseName;
	
	@FindBy(xpath="//img[@alt='Tests']")
	private WebElement testsIcon;
	
	@FindBy(xpath="//*[@id=\"content-section\"]/div/div[2]/a[1]/img")
	private WebElement newTestIcon;
	
	@FindBy(id="exercise_title")
	private WebElement testName;
	
	@FindBy(xpath="//*[@id=\"advanced_params\"]")
	private WebElement advParamsButton;
	
	@FindBy(xpath="//*[@id=\"cke_1_contents\"]/iframe")
	private WebElement descrFrame;
	
	@FindBy(css=".cke_editable")
	private WebElement descrInput;
	
	@FindBy(xpath="//*[@id=\"exerciseType_0\"]")
	private WebElement endOfTestRadio;
	
	@FindBy(xpath="//*[@id=\"qf_a21a92\"]")
	private WebElement enableStartTime;
	
	@FindBy(xpath="//*[@id=\"start_date_div\"]/div/div[1]/div/span[1]/img")
	private WebElement startDatePicker;
	
	@FindBy(xpath="//*[@id=\"ui-datepicker-div\"]")
	private WebElement startDatePickerDiv;
	
	@FindBy(xpath="//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[5]/td[3]/a")
	private WebElement startDateClick;
	
	@FindBy(xpath="//*[@id=\"ui-datepicker-div\"]/div[3]/button[2]")
	private WebElement startDateDoneButton;
	
	@FindBy(xpath="//*[@id=\"pass_percentage\"]")
	private WebElement passPercentage;
	
	@FindBy(xpath="//*[@id=\"exercise_admin_submitExercise\"]")
	private WebElement proceedToQuestions;
	
	@FindBy(xpath="//*[@id=\"content-section\"]/div/div[2]")
	private WebElement exerciseAddedMesg;
	
	@FindBy(xpath="//*[@id=\"content-section\"]/div/div[5]/ul/li[1]/div/a/img")
	private WebElement multipleChoiceIcon;
	
	@FindBy(xpath="//*[@id=\"question_admin_form_questionName\"]")
	private WebElement questionInputBox;
	
	@FindBy(xpath="/html/body")
	private WebElement ansText;
	
	@FindBy(xpath="//*[@id=\"qf_10e5ae\"]")
	private WebElement ans1radio;
	
	@FindBy(xpath="//*[@id=\"qf_d22bd1\"]")
	private WebElement ans2radio;
	
	@FindBy(xpath="//*[@id=\"qf_011a96\"]")
	private WebElement ans3radio;
	
	@FindBy(xpath="//*[@id=\"qf_3c2f18\"]")
	private WebElement ans4radio;
	
	@FindBy(xpath="//*[@id=\"submit-question\"]")
	private WebElement submitQuestionButton;
	
	@FindBy(xpath="//*[@id=\"content-section\"]/div/div[4]")
	private WebElement questAddedMesg;
	
	@FindBy(xpath="//*[@id=\"content-section\"]/div/div[3]/a[2]/img")
	private WebElement previewIcon;
	
	@FindBy(xpath="//a[@class='btn btn-success btn-large']")
	private WebElement startTestButton;
		
	//This method will create a new Test with the passed Test Name inside the course
	public String createTest(String test) throws InterruptedException
	{
		js = (JavascriptExecutor) driver;
		this.myCourses.click();
		this.courseName.click();
		this.testsIcon.click();
		this.newTestIcon.click();
		this.testName.sendKeys(test);
		this.advParamsButton.click();
		Thread.sleep(3000);
		WebElement div1 = driver.findElement(By.xpath("//*[@id=\"cke_exerciseDescription\"]"));
		WebElement div2 = div1.findElement(By.xpath("//*[@id=\"cke_1_contents\"]"));
		driver.switchTo().frame(0);
		WebElement tEditor = driver.switchTo().activeElement();
		new Actions(driver).moveToElement(tEditor).perform();
		driver.findElement(By.xpath("/html/body")).sendKeys("This is Selenium Quiz");
		driver.switchTo().defaultContent();
		this.endOfTestRadio.click();
		WebElement chkDiv = driver.findElement(By.xpath("//*[@id=\"advanced_params_options\"]/div[12]/div[1]"));
		js.executeScript("arguments[0].scrollIntoView();", chkDiv);
		WebElement chkDiv2 = chkDiv.findElement(By.xpath("//*[@id=\"advanced_params_options\"]/div[12]/div[1]/div"));
		chkDiv2.findElement(By.name("activate_start_date_check")).click();
		Thread.sleep(5000);
		this.passPercentage.sendKeys("50");
		this.proceedToQuestions.click();
		return this.exerciseAddedMesg.getText();
	}
	
	public String addMultipleChoice1() throws InterruptedException
	{
		js = (JavascriptExecutor) driver;
		this.multipleChoiceIcon.click();
		this.questionInputBox.sendKeys("Selenium is used to test?");
		js.executeScript("scroll(0,300)");
				
		//Add Answer Option1
		driver.switchTo().frame(1);
		WebElement tEditor1 = driver.switchTo().activeElement();
		new Actions(driver).moveToElement(tEditor1).perform();
		this.ansText.sendKeys("Web Applications");
		driver.switchTo().parentFrame();
		js.executeScript("scroll(0,100)");
		
		//Add Answer Option2
		driver.switchTo().frame(3);
		WebElement tEditor2 = driver.switchTo().activeElement();
		new Actions(driver).moveToElement(tEditor2).perform();
		this.ansText.sendKeys("Desktop Applications");
		driver.switchTo().parentFrame();
				
		//Add Answer Option3
		driver.switchTo().frame(5);
		WebElement tEditor3 = driver.switchTo().activeElement();
		new Actions(driver).moveToElement(tEditor3).perform();
		this.ansText.sendKeys("Mainframe Applications");
		driver.switchTo().parentFrame();
		
		
		//Add Answer Option4
		driver.switchTo().frame(7);
		WebElement tEditor4 = driver.switchTo().activeElement();
		new Actions(driver).moveToElement(tEditor4).perform();
		this.ansText.sendKeys("All Java applications");
		driver.switchTo().window(driver.getWindowHandle());
			
		//Click the Right answer Radio Button
		driver.findElement(By.xpath("//form[@id='question_admin_form']/fieldset/table/tbody/tr/td[2]/input")).click();
		this.submitQuestionButton.click();
		Thread.sleep(5000);
		return this.questAddedMesg.getText();
	}
	
	public String addMultipleChoice2() throws InterruptedException
	{
		js = (JavascriptExecutor) driver;
		this.multipleChoiceIcon.click();
		this.questionInputBox.sendKeys("Which language are you using in Selenium?");
		js.executeScript("scroll(0,300)");
				
		//Add Answer Option1
		driver.switchTo().frame(1);
		WebElement tEditor1 = driver.switchTo().activeElement();
		new Actions(driver).moveToElement(tEditor1).perform();
		this.ansText.sendKeys("Python");
		driver.switchTo().parentFrame();
		js.executeScript("scroll(0,100)");
		
		//Add Answer Option2
		driver.switchTo().frame(3);
		WebElement tEditor2 = driver.switchTo().activeElement();
		new Actions(driver).moveToElement(tEditor2).perform();
		this.ansText.sendKeys("Selenium");
		driver.switchTo().parentFrame();
				
		//Add Answer Option3
		driver.switchTo().frame(5);
		WebElement tEditor3 = driver.switchTo().activeElement();
		new Actions(driver).moveToElement(tEditor3).perform();
		this.ansText.sendKeys("C");
		driver.switchTo().parentFrame();
		
		
		//Add Answer Option4
		driver.switchTo().frame(7);
		WebElement tEditor4 = driver.switchTo().activeElement();
		new Actions(driver).moveToElement(tEditor4).perform();
		this.ansText.sendKeys("C#");
		driver.switchTo().window(driver.getWindowHandle());
			
		//Click the Right answer Radio Button
		driver.findElement(By.xpath("//form[@id='question_admin_form']/fieldset/table/tbody/tr[2]/td[2]/input")).click();
		this.submitQuestionButton.click();
		Thread.sleep(5000);
		return this.questAddedMesg.getText();
	}
	
	//Preview the created test
	public Boolean previewTest()
	{
		this.previewIcon.click();
		Boolean success=false;
		if(driver.findElement(By.xpath("//a[@class='btn btn-success btn-large']")).isDisplayed())
		{
			success=true;
		}
			return success;
		
	}
	
	@FindBy(xpath="//div[@class='question_options']")
	private WebElement optionsDiv;
	
	@FindBy(xpath="//div[1]//div[3]//form[1]//div[3]//div[2]//label[1]//input[1]")
	private WebElement option1Radio;
	
	@FindBy(xpath="//div[@class='form-actions']/button")
	private WebElement nextQuestionButton;
	
	@FindBy(xpath="//*[@id=\"choice-330-1\"]")
	private WebElement option2Radio;
	
	@FindBy(xpath="//div[3]//form//div[3]//div[3]//button[2]")
	private WebElement endTestButton;
	
	@FindBy(xpath="//*[@id=\"content-section\"]/div/div[4]/h2")
	private WebElement quizResultPage;
	
	
	public Boolean teacherAuthorTest() throws InterruptedException
	{
		this.startTestButton.click();
		Thread.sleep(3000);
		this.option1Radio.click();
		this.nextQuestionButton.click();
		Thread.sleep(5000);
		this.option1Radio.click();
		this.endTestButton.click();
		Boolean mesg = (this.quizResultPage.getText()).contains("Result");
		return mesg;
	}
	
	@FindBy(linkText="Reporting")
	private WebElement adminReportingTab;
	
	@FindBy(linkText="Followed teachers")
	private WebElement followedTeachersLink;
	
	@FindBy(xpath="//*[@id=\"search_user_keyword\"]")
	private WebElement searchTeacher;
	
	@FindBy(xpath="//*[@id=\"search_user_submit\"]")
	private WebElement searchSubmitButton;
	
	@FindBy(xpath="//td[contains(text(),'Swapna')]")
	private WebElement teacherName;
	
	@FindBy(xpath="//tr[@class='row_even']//td[5]/a/img")
	private WebElement teacherDetails;
	
	public Boolean reportTab(String key) throws InterruptedException 
	{
		this.adminReportingTab.click();
		this.followedTeachersLink.click();
		this.searchTeacher.sendKeys(key); // Send teacher name in the Keyword TextBox
		this.searchSubmitButton.click();
		Thread.sleep(3000); // Wait for search results
		String name = this.teacherName.getText();
		this.teacherDetails.click();
		if (name.matches(key)) // checking if teacher name matches with the search keyword
			return true;
		else
			return false;
	}
	
	@FindBy(xpath="//table[@class='table table-striped table-hover courses-tracking']")
	private WebElement coursesTable;
	
	@FindBy(xpath="//a[contains(text(),'Selenium2021')]")
	private WebElement courseNme;
	
	@FindBy(xpath="//tbody//tr[29]//td[7]/a/img")
	private WebElement courseArrow;
	
	// This method will validate contents of the learner details page and Select a Course
	public Boolean learnerDetails() throws InterruptedException
	{
		js = (JavascriptExecutor) driver;
		Thread.sleep(5000);//Wait for page to Load
		List<WebElement> rows = this.coursesTable.findElements(By.tagName("tr"));
		Boolean course = false;
		if(rows.size()!=0)
		{
			course = true;
			js.executeScript("arguments[0].scrollIntoView();", this.courseArrow); // Scroll down till the element is visible
		this.courseArrow.click();
		}
		
		return course;
	}	
	
	@FindBy(xpath="//*[@id=\"page-breadcrumb\"]/ul/li[4]")
	private WebElement lcdPageInfo;
	
	@FindBy(xpath="//img[@title='quiz.png']")
	private WebElement quizImg;
	
	//This method will verify contents of "learner Course Details Page" and click on Test attempted
	public Boolean learnerCourseDetails() 
	{
		String pageInfo = this.lcdPageInfo.getText();
		System.out.println("We are on Page: "+pageInfo);
		String testName = driver.findElement(By.xpath("//div[4]//table[1]//tbody[1]//tr[1]//td[1]")).getText();
		System.out.println("Clicking Test attempt for :"+testName);
		Boolean tName = true;
		if(testName.isEmpty())
		{
			tName = false;
		}
		else
		this.quizImg.click(); // Clicking on Test Quiz Icon
		return tName;
	}
	
	@FindBy(xpath="//*[@id=\"content-section\"]/div/div[2]/h2")
	private WebElement quizInfoPage;
	
	@FindBy(xpath="//div[1]//form[1]//fieldset[1]//div[1]//div[1]//label[1]//input[1]")
	private WebElement sendCheckBox;
	
	@FindBy(xpath="//button[@id='myform_submit']")
	private WebElement emailSubmit;
	
	@FindBy(xpath="//div[@class='alert alert-info']")
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
