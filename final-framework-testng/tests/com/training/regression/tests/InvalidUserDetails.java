package com.training.regression.tests;

import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.dataproviders.AdminDataProviders;
import com.training.generics.ScreenShot;
import com.training.pom.InvalidDetailsPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class InvalidUserDetails {
	private WebDriver driver;
	private String baseUrl;
	private InvalidDetailsPOM adminPOM;
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
		adminPOM = new InvalidDetailsPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		// open the browser
		driver.get(baseUrl);
	}

	@AfterMethod
	public void tearDown() throws Exception {
		driver.quit();
	}

	//This test will verify if user data is pulled from excel file and proper error message is displayed for he invalid data.
	@Test(dataProvider = "excel-inputs1", dataProviderClass = AdminDataProviders.class)
	public void addNewUser(String fName, String lName, String email, String phone,String uName, String pwd) throws InterruptedException 
	{
		adminPOM.sendUserName("admin");
		adminPOM.sendPassword("admin@123");
		adminPOM.clickLoginBtn();
		String actual = adminPOM.addNewUser(fName,lName,email,phone,uName, pwd);//Storing the return value of error message from application
		String expected="";
		String emailReg = "^[a-zA-Z0-9_+&*-]+(?:\\."+  //regular expression for validating email
                "[a-zA-Z0-9_+&*-]+)*@" + 
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                "A-Z]{2,7}$"; 
		String[] unameList = {"manzoor","alex","mark"}; // list of existing user-names in the application
		Pattern em = Pattern.compile(emailReg);
		if((email==null)||(em.matcher(email).matches()==false)) //checking if email field is null or not matching the email validation
		{
			expected = "The email address is not complete or contains some invalid characters";
			assertTrue(actual.matches(expected));
		}
		else if ((uName.matches(unameList[0]))||(uName.matches(unameList[1]))||(uName.matches(unameList[2])))//validating if user-name already exists
		{
			expected = "This login is already in use";
			assertTrue(actual.matches(expected));
		}
			
		screenShot.captureScreenShot("Add User Error");
	}

	
}
