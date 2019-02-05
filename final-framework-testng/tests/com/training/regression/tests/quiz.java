package com.training.regression.tests;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.QuizPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class quiz {
	private WebDriver driver;
	private String baseUrl;
	private QuizPOM quizPOM;
	private static Properties properties;
	private ScreenShot screenShot;

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		quizPOM = new QuizPOM(driver); 
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		driver.get(baseUrl);
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	
	@Test(priority=1) //This test will verify that a teacher is able to create a quiz-test with multiple choice questions.
	public void teacherCreateQuiz() throws InterruptedException{
		//Login
		quizPOM.sendUserName("swapna");
		quizPOM.sendPassword("swapna");
		quizPOM.clickLoginBtn(); 
		
		//Enter course , Go to Tests and create new Test
		String actual1 = quizPOM.createTest("Online-Quiz1");
		String exp1 = "Exercise added";
		assertEquals(actual1,exp1);// Verify if course is successfully added
		
		//Add Multiple Choice Question1 to the Test
		String actual2 = quizPOM.addMultipleChoice1();
		String exp2 = "1 questions, for a total score (all questions) of 0.";
		assertEquals(actual2,exp2); //Verify if Question1 is added successfully
		
		//Add Multiple Choice Question1 to the Test
		String actual3 = quizPOM.addMultipleChoice1();
		String exp3 = "2 questions, for a total score (all questions) of 0.";
		assertEquals(actual3,exp3); //Verify if Question2 is added successfully
		
		//Preview Test
		Boolean actual4 = quizPOM.previewTest();
		Boolean exp4 = true;
		assertEquals(actual4,exp4);
	}
	
	@Test(priority=2)//This test will verify id a registered student is able to take quiz for the subscribed course
	public void studentQuiz()
	{
		quizPOM.sendUserName("chikuv");
		quizPOM.sendPassword("chikuv");
		quizPOM.clickLoginBtn(); 
		Boolean actual5 = quizPOM.studentQuiz();
		Boolean exp5 = true;
		assertEquals(actual5,exp5);
	}
	
	@Test(priority=3)//This test will verify if teacher is able to grade test submitted by student
	public void gradeTest() throws InterruptedException
	{
		quizPOM.sendUserName("swapna");
		quizPOM.sendPassword("swapna");
		quizPOM.clickLoginBtn(); 
		String actual5 = quizPOM.validateTest();
		String exp5 = "Message Sent";
		assertEquals(actual5,exp5);
	}
}
