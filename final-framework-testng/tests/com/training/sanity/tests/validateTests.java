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
import com.training.pom.ValidateTestPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class validateTests {
	private WebDriver driver;
	private String baseUrl;
	private ValidateTestPOM vTestPOM;
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
		vTestPOM = new ValidateTestPOM(driver); 
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		driver.get(baseUrl);
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	
	
	@Test //This test will verify if a teacher can validate a test submitted by a learner
	public void validateTest() throws InterruptedException {
		//Pass login credentials
		vTestPOM.sendUserName("vinodd");
		vTestPOM.sendPassword("vinodd");
		vTestPOM.clickLoginBtn(); 
		
		vTestPOM.enterCourseTests();
		
		String actEmail = vTestPOM.validateTest();
		System.out.println(actEmail);
				
		String expMesg = "Message Sent";
		String actMesg = vTestPOM.sendEmail();
		assertEquals(expMesg,actMesg);
	}
}
