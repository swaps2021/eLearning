package com.training.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class QuizPOM {
	private WebDriver driver; 
	private JavascriptExecutor js;
	public QuizPOM(WebDriver driver) {
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
	
	@FindBy(linkText="Selenium-Quiz")
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
	
	@FindBy(xpath="//*[@id=\"content-section\"]/div/div[2]/div[2]/div/a")
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
		//this.descrInput.sendKeys("Online Quiz");
		driver.switchTo().window(driver.getWindowHandle());
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
		this.questionInputBox.sendKeys("Which course are you learning?");
		js.executeScript("scroll(0,300)");
				
		//Add Answer Option1
		driver.switchTo().frame(1);
		WebElement tEditor1 = driver.switchTo().activeElement();
		new Actions(driver).moveToElement(tEditor1).perform();
		this.ansText.sendKeys("Selenium");
		driver.switchTo().parentFrame();
		js.executeScript("scroll(0,100)");
		
		//Add Answer Option2
		driver.switchTo().frame(3);
		WebElement tEditor2 = driver.switchTo().activeElement();
		new Actions(driver).moveToElement(tEditor2).perform();
		this.ansText.sendKeys("Java");
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
		if(driver.findElement(By.xpath("//*[@id=\"content-section\"]/div/div[2]/div[2]/div/a")).isDisplayed())
		{
			success=true;
		}
			return success;
	}
	
	//Student Page
	
	@FindBy(linkText="My courses")
	private WebElement studCourses;
	
	@FindBy(linkText="Selenium-Quiz")
	private WebElement studCourseName;
	
	@FindBy(xpath="//img[@alt='Tests']")
	private WebElement studTestsIcon;
	
	/*@FindBy(xpath="//*[@id=\"exercise_list_233\"]/td[1]/a")
	private WebElement courseNameClick;*/
	
	@FindBy(linkText="Online-Quiz1")
	private WebElement courseNameClick;
		
	@FindBy(xpath="//*[@id=\"content-section\"]/div/div[2]/div[2]/div/a")
	private WebElement studStartTest;
	
	@FindBy(xpath="//*[@id=\"question_div_307\"]/div[1]/strong")
	private WebElement studQuest1;
	
	@FindBy(xpath="//*[@id=\"question_div_307\"]/div[2]")
	private WebElement questOptionsDiv;
	
	@FindBy(xpath="//*[@id=\"choice-307-1\"]")
	private WebElement option1Radio;
	
	@FindBy(xpath="//*[@id=\"question_div_307\"]/div[3]/button")
	private WebElement nextQuestionButton;
	
	@FindBy(xpath="//*[@id=\"choice-308-2\"]")
	private WebElement option2Radio;
	
	@FindBy(xpath="//*[@id=\"question_div_308\"]/div[3]/button[2]")
	private WebElement endTestButton;
	
	@FindBy(xpath="//*[@id=\\\"content-section\\\"]/div/div[3]/h2")
	private WebElement quizResultPage;
	
	
	public Boolean studentQuiz()
	{
		this.studCourses.click();
		this.studCourseName.click();
		this.studTestsIcon.click();
		this.courseNameClick.click();
		this.studStartTest.click();
		this.option1Radio.click();
		this.nextQuestionButton.click();
		this.option2Radio.click();
		this.endTestButton.click();
		Boolean mesg = (this.quizResultPage.getText()).contains("Result");
		return mesg;
	}
	
	@FindBy(css="img[alt=\"Results and feedback\"]")
	private WebElement rfIcon;
	
	@FindBy(name="send_notification")
	private WebElement sendCheckBox;
	
	@FindBy(xpath="//*[@id=\"notification_content\"]")
	private WebElement emailBody;
	
	@FindBy(xpath="//*[@id=\"myform_submit\"]")
	private WebElement emailSubmit;
	
	@FindBy(xpath="//*[@id=\"content-section\"]/div/div[2]")
	private WebElement sentMessage;

	public String validateTest() throws InterruptedException
	{
		this.myCourses.click();
		this.courseName.click();
		this.testsIcon.click();
		this.rfIcon.click();//Click on Results and feedback icon against the Test
		Thread.sleep(5000);
		
		List<WebElement> rows = driver.findElements(By.xpath("//*[@id=\"results\"]/tbody/tr"));
		Boolean status = false;
		String val = "Not validated";
		int i;
		for(i =2;i<=rows.size();i++)
		{
			status = val.matches(driver.findElement(By.xpath("/html/body/div[1]/section/div/div[3]/div[3]/div[3]/div/table/tbody/tr["+i+"]/td[10]/span")).getText());
			if(status)
			{
				driver.findElement(By.xpath("/html/body/div[1]/section/div/div[3]/div[3]/div[3]/div/table/tbody/tr["+i+"]/td[12]/div/a[1]/img")).click();
				break;
				
			}
		}
		Thread.sleep(3000);
		this.sendCheckBox.click();
		Thread.sleep(5000);
		this.emailSubmit.click();
		return this.sentMessage.getText();
		
	}
	
	
	}
