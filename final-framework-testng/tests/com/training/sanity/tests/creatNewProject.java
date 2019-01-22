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
import com.training.pom.NewProjectPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class creatNewProject {
	private WebDriver driver;
	private String baseUrl;
	private NewProjectPOM newPrjPOM;
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
		newPrjPOM = new NewProjectPOM(driver); 
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
	public void projectNew() throws InterruptedException{
		//Login
		newPrjPOM.sendUserName("vinodd");
		newPrjPOM.sendPassword("vinodd");
		newPrjPOM.clickLoginBtn(); 
		
		//Enter the course Page and create a new Project
		Boolean actual1 = newPrjPOM.createProj("e-Learning3", "This is New Project");
		Boolean expected1 = true;
		assertEquals(actual1,expected1); 
		System.out.println("Project Created");
		
		//Verify is Welcome message is present on the Project Page
		Boolean actual2 = newPrjPOM.welcomeMessage();
		Boolean expected2 = true;
		assertEquals(actual2,expected2); 
		System.out.println("Welcome Message verified");
		
		//Verify if new task is created successfully
		Boolean actual3 = newPrjPOM.newTask("Design2");
		Boolean expected3 = true;
		assertEquals(actual3,expected3); 
		System.out.println("New Task Created");
		
		//Verify if new Role is created successfully
		Boolean actual4 = newPrjPOM.newRole("Developer2");
		Boolean expected4 = true;
		assertEquals(actual4,expected4); 
		System.out.println("New Role Created");
		
		//Verify if new Role is assigned successfully
		Boolean actual5 = newPrjPOM.assignRole();
		Boolean expected5 = true;
		assertEquals(actual5,expected5);
		System.out.println("Role Assigned Created");
		
		//Verify if new user is registered successfully
		Boolean actual6 = newPrjPOM.userMgmt();
		Boolean expected6 = true;
		assertEquals(actual6,expected6);
		System.out.println("User Registered");
		
		
	}
}
