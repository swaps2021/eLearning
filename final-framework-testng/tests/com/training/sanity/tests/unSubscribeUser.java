package com.training.sanity.tests;

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
import com.training.pom.UnSubscribeuserPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class unSubscribeUser {
	private WebDriver driver;
	private String baseUrl;
	private UnSubscribeuserPOM uScribePOM;
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
		uScribePOM = new UnSubscribeuserPOM(driver); 
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		driver.get(baseUrl);
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	@Test //This test will verify if a teacher can unsubscribe a registered user from course
	public void unSubscribeuser() throws InterruptedException {
		//Pass login credentials
		uScribePOM.sendUserName("swapna");
		uScribePOM.sendPassword("swapna");
		uScribePOM.clickLoginBtn(); 
		
		uScribePOM.courseUser();//Calling the method to view course registered users
		Thread.sleep(5000);
		screenShot.captureScreenShot("CourseUserSnapshot");
		String actual = uScribePOM.unSubscribeUser(); //Calling method to unsubscribe a registered user
		String uscribe = "User is now unsubscribed";
		assertEquals(uscribe,actual);//Verifying message after successfully unsubscribing a user
		Thread.sleep(5000);
		screenShot.captureScreenShot("User Unsubscribed");
		
	}
	
}
