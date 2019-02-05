package com.training.regression.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.dataproviders.AdminDataProviders;
import com.training.generics.ScreenShot;
import com.training.pom.AdminAddUserPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class adminAddUser {
	private WebDriver driver;
	private String baseUrl;
	private AdminAddUserPOM adminPOM;
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
		adminPOM = new AdminAddUserPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		// open the browser
		driver.get(baseUrl);
	}

	@AfterMethod
	public void tearDown() throws Exception {
		driver.quit();
	}
	
	//To verify if user-details are taking inputs from the excel file
	@Test(dataProvider = "excel-inputs", dataProviderClass = AdminDataProviders.class)
	public void addNewUser(String fName, String lName, String email, String uName, String pwd) throws InterruptedException 
	{
		adminPOM.sendUserName("admin");
		adminPOM.sendPassword("admin@123");
		adminPOM.clickLoginBtn();
		adminPOM.addNewUser(fName,lName,email,uName, pwd);
		screenShot.captureScreenShot("User Added Table");
	}

}
