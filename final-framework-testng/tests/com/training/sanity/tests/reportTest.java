package com.training.sanity.tests;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.ReportTestPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class reportTest {
	private WebDriver driver;
	private String baseUrl;
	private ReportTestPOM rTestPOM;
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
		rTestPOM = new ReportTestPOM(driver); 
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
	public void reportTests() throws InterruptedException {
		
		rTestPOM.sendUserName("vinodd");
		rTestPOM.sendPassword("vinodd");
		rTestPOM.clickLoginBtn(); 
		rTestPOM.enterCourse(); // Enter Course Details Page and Select Reporting icon
	    rTestPOM.reportPage();	//Select learner from Reporting Page
		String pageTitle = rTestPOM.learnerDetails();
		System.out.println(pageTitle);
		//assertEquals(pageTitle,"Selenium-Quiz : Result");
	
		String mesg = rTestPOM.sendEmail();
		assertEquals(mesg,"Message Sent");
	
		String cPage = rTestPOM.coursePage();
		assertEquals(cPage,"eLearning - eLearning - Selenium - Tests");
	}
  }
