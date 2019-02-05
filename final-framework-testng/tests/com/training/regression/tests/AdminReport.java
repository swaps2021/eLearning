package com.training.regression.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.AdminReportPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class AdminReport {
	private WebDriver driver;
	private String baseUrl;
	private AdminReportPOM adminPOM;
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
		adminPOM = new AdminReportPOM(driver); 
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
		adminPOM.sendUserName("swapna");
		adminPOM.sendPassword("swapna");
		adminPOM.clickLoginBtn(); 
		
		//Enter course , Go to Tests and create new Test
		String actual1 = adminPOM.createTest("Online-Quiz2");
		String exp1 = "Exercise added";
		assertEquals(actual1,exp1);// Verify if course is successfully added
		
		//Add Multiple Choice Question1 to the Test
		String actual2 = adminPOM.addMultipleChoice1();
		String exp2 = "1 questions, for a total score (all questions) of 0.";
		assertEquals(actual2,exp2); //Verify if Question1 is added successfully
		
		//Add Multiple Choice Question1 to the Test
		String actual3 = adminPOM.addMultipleChoice1();
		String exp3 = "2 questions, for a total score (all questions) of 0.";
		assertEquals(actual3,exp3); //Verify if Question2 is added successfully
		
		//Preview Test
		Boolean actual4 = adminPOM.previewTest();
		Boolean exp4 = true;
		assertEquals(actual4,exp4);
		
		//Author Test
		Boolean actualT = adminPOM.teacherAuthorTest();
		assertTrue(actualT);
		
	}
	
	//This test will verify if the admin is able to send report of the quiz
	@Test (priority=2)
	public void adminReporting() throws InterruptedException{
		//Login
		adminPOM.sendUserName("admin");
		adminPOM.sendPassword("admin@123");
		adminPOM.clickLoginBtn(); 
		
		//Enter Reporting Tab and searching for learner
		Boolean actual1 = adminPOM.reportTab("Swapna");
		Boolean expected1 = true;
		assertEquals(actual1,expected1);
		System.out.println("reportTab() - PASS");
		
		//Enter Learner Details and click on CourseDetails arrow
		Boolean actual2 = adminPOM.learnerDetails();
		Boolean expected2 = true;
		assertEquals(actual2,expected2);
		System.out.println("learnerDetails() - PASS");
		
		//Enter Learner Course Details page and click on attempted Quiz
		Boolean actual3 = adminPOM.learnerCourseDetails();
		Boolean expected3 = true;
		assertEquals(actual3,expected3);
		System.out.println("learnerCourseDetails() - PASS");
		
		//Enter Quiz page and send report email
		String actual4 = adminPOM.quizDetailsPage();
		String expected4 = "Message Sent";
		assertEquals(actual4,expected4);
		System.out.println("quizDetailsPage() - PASS");
	}
}
