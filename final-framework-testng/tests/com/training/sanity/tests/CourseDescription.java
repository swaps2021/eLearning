package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.CourseDescPOM;
import com.training.pom.NewCoursePOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class CourseDescription {
	private WebDriver driver;
	private String baseUrl;
	private CourseDescPOM coursePOM;
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
		coursePOM = new CourseDescPOM(driver); 
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
	public void courseDescription() throws InterruptedException {
		coursePOM.sendUserName("swapna");
		coursePOM.sendPassword("swapna");
		coursePOM.clickLoginBtn(); 
		coursePOM.coursePage();
		screenShot.captureScreenShot("CoursePage");
		coursePOM.enterCourseDesc("Selenium-WebDriver","This is Selenium Webdriver Course");
		Thread.sleep(5000);
		screenShot.captureScreenShot("CourseDescriptionSaved");
		
	}
}
