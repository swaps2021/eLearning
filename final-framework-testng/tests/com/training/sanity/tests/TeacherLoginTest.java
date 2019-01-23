package com.training.sanity.tests;

import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.TeachLoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

public class TeacherLoginTest {
	private WebDriver driver;
	private String baseUrl;
	private TeachLoginPOM loginPOM;
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
		loginPOM = new TeachLoginPOM(driver); 
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	@Test //This test will verify the welcome text after user-Teacher logs-in
	public void validLoginTest() {
		//Pass Login credentials
		loginPOM.sendUserName("tester1");
		loginPOM.sendPassword("tester1");
		loginPOM.clickLoginBtn(); 
		screenShot.captureScreenShot("TeacherHomePage");
		Boolean actual = loginPOM.checkWelcomeText();// Verifying the welcome text
		Boolean expected = true;
		assertEquals(actual,expected);
		
	}
	
}

