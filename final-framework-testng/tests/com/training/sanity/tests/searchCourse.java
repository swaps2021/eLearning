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
import com.training.pom.SearchCoursePOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class searchCourse {
	private WebDriver driver;
	private String baseUrl;
	private SearchCoursePOM searchPOM;
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
		searchPOM = new SearchCoursePOM(driver); 
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		driver.get(baseUrl);
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	@Test //This test will verify if the user-Teacher can search for the desired course
	public void searchCourses() throws InterruptedException {
		searchPOM.sendUserName("swapna");
		searchPOM.sendPassword("swapna");
		searchPOM.clickLoginBtn(); 
		searchPOM.searchCourse();
		Thread.sleep(5000);
		screenShot.captureScreenShot("Course Search Result");
		
		
	}
	
}
