package com.training.sanity.tests;

import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.NewCoursePOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class CreateNewCourse {
	private WebDriver driver;
	private String baseUrl;
	private NewCoursePOM loginPOM;
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
		loginPOM = new NewCoursePOM(driver); 
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
	public void newCourseAdd() throws InterruptedException {
		loginPOM.sendUserName("swapna");
		loginPOM.sendPassword("swapna");
		loginPOM.clickLoginBtn(); 
		//screenShot.captureScreenShot("TeacherHomePage");
		loginPOM.createNewCourse("Selenium","Projects","s03311");
		Thread.sleep(5000);
		screenShot.captureScreenShot("Create This Course Button Click");
		loginPOM.courseIntro("This is Selenium Course...");
		Thread.sleep(5000);
		screenShot.captureScreenShot("CourseIntroTextSaved");
		
	}
	
	  }

