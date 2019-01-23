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
import com.training.pom.NewGroupPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class newGroup {
	private WebDriver driver;
	private String baseUrl;
	private NewGroupPOM groupPOM;
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
		groupPOM = new NewGroupPOM(driver); 
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
		//Login
		groupPOM.sendUserName("vinodd");
		groupPOM.sendPassword("vinodd");
		groupPOM.clickLoginBtn(); 
		
		//Navigate to Course - Groups Page
		groupPOM.enterCourse();
		System.out.println("enterCourse() - PASS");
		//verify if new Group can be created
		String createGrp = groupPOM.createGroup("Rock6");
		assertEquals(createGrp,"group(s) has (have) been added");
		System.out.println("createGroup()- PASS");
		
		//Verify if the new group created is visible in the Groups Page
		Boolean grpExists = groupPOM.groupExists("Rock3");
		Boolean expected1 = true;
		assertEquals(grpExists,expected1);
		System.out.println("groupExists() - PASS");
		
		//Verify if the group members page is displayed after clicking Group Members Icon
		/*String grpMember = groupPOM.groupMembersCheck();
		assertEquals(grpMember,"Group members");
		System.out.println("groupMembersCheck() - PASS");*/
		
		//Verify if group members can be added successfully
		Boolean addMembers = groupPOM.addGroupMembers();
		Boolean expected2 = true;
		assertEquals(addMembers,expected2);
		System.out.println("addGroupMembers - PASS");
		
		//Verify edit Settings functionality is working as expected
		Boolean editSett = groupPOM.editSettings();
		Boolean expected3 = true;
		assertEquals(editSett,expected3);
		System.out.println("editSettings() - PASS");
	}
}
