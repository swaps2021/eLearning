package com.training.sanity.tests;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.GenReportPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class genReport {
	private WebDriver driver;
	private String baseUrl;
	private GenReportPOM gReportPOM;
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
		gReportPOM = new GenReportPOM(driver); 
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		driver.get(baseUrl);
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	
	@Test 
	public void generateReport() throws InterruptedException{
		//Login
		gReportPOM.sendUserName("vinodd");
		gReportPOM.sendPassword("vinodd");
		gReportPOM.clickLoginBtn(); 
		
		//Enter Reporting Tab and searching for learner
		Boolean actual1 = gReportPOM.reportTab("Chiku");
		Boolean expected1 = true;
		assertEquals(actual1,expected1);
		System.out.println("reportTab() - PASS");
		
		//Enter Learner Details and click on CourseDetails arrow
		Boolean actual2 = gReportPOM.learnerDetails();
		Boolean expected2 = true;
		assertEquals(actual2,expected2);
		System.out.println("learnerDetails() - PASS");
		
		//Enter Learner Course Details page and click on attempted Quiz
		Boolean actual3 = gReportPOM.learnerCourseDetails();
		Boolean expected3 = true;
		assertEquals(actual3,expected3);
		System.out.println("learnerCourseDetails() - PASS");
		
		//Enter Quiz page and send report email
		String actual4 = gReportPOM.quizDetailsPage();
		String expected4 = "Message Sent";
		assertEquals(actual4,expected4);
		System.out.println("quizDetailsPage() - PASS");
	}
}
