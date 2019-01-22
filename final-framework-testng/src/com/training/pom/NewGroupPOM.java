package com.training.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class NewGroupPOM {
	private WebDriver driver;
	private JavascriptExecutor js;
		
		public NewGroupPOM(WebDriver driver) {
			this.driver = driver; 
			PageFactory.initElements(driver, this);
		}
		
		@FindBy(id="login")
		private WebElement userName; 
		
		@FindBy(id="password")
		private WebElement password;
		
		@FindBy(id="formLogin_submitAuth")
		private WebElement loginBtn; 
		
		public void sendUserName(String userName) {
			this.userName.clear();
			this.userName.sendKeys(userName);
		}
		
		public void sendPassword(String password) {
			this.password.clear(); 
			this.password.sendKeys(password); 
		}
		
		public void clickLoginBtn() {
			this.loginBtn.click(); 
		}
		
		//This method will navigate to Course - Groups Page
		public void enterCourse() throws InterruptedException 
		{
			driver.findElement(By.linkText("My courses")).click();
			driver.findElement(By.linkText("Selenium - Groups")).click();
			driver.findElement(By.linkText("Groups")).click();
			Thread.sleep(5000);
			System.out.println("Entering Groups Page");
		}
		
		public String createGroup(String name) throws InterruptedException
		{
			driver.findElement(By.xpath("/html/body/div[1]/section/div/div[2]/div/div[1]/a[1]/img")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//button[@id='create_groups_submit']")).click();
			WebElement grpName = driver.findElement(By.xpath("//*[@id=\"create_groups_step2\"]/div/table/tbody/tr[2]/td[2]/input"));
			grpName.clear();
			grpName.sendKeys(name);
			driver.findElement(By.id("create_groups_step2_submit")).click();
			String mesg = driver.findElement(By.xpath("/html/body/div[1]/section/div/div[2]")).getText();
			return mesg;
		}
		
		public Boolean groupExists(String name) throws InterruptedException
		{
			Thread.sleep(5000);
			WebElement groupTab = driver.findElement(By.linkText("Groups"));
			WebElement gDiv = groupTab.findElement(By.xpath("//*[@id=\"content-section\"]/div/div[4]"));
			WebElement gForm = gDiv.findElement(By.xpath("//*[@id=\"form_group_category_0_id\"]"));
			WebElement gTable = gForm.findElement(By.tagName("table"));
			List<WebElement> rows = gTable.findElements(By.tagName("tr"));
			//System.out.println("Rows = "+rows.size());
			Boolean status = false;
			String gName;
			for(int i=2;i<=rows.size();i++)
			{
				gName = gTable.findElement(By.xpath("/html/body/div[1]/section/div/div[4]/form/table[1]/tbody/tr["+i+"]/td[2]")).getText();
				if (gName.matches(name))
				{
					status = true;
					break;
				}
			}
			return status;
		}
		
		/*public String groupMembersCheck()
		{
			WebElement groupMemTab = driver.findElement(By.linkText("Group members"));
			
			String groupMesg = driver.findElement(By.xpath("/html/body/div[1]/section/div/form/fieldset/div[1]/label")).getText();
			return groupMesg;
		}*/
		
		public Boolean addGroupMembers() throws InterruptedException
		{
			WebElement groupTab1 = driver.findElement(By.linkText("Groups"));
			WebElement gDiv1 = groupTab1.findElement(By.xpath("//*[@id=\"content-section\"]/div/div[4]"));
			WebElement gForm1 = gDiv1.findElement(By.xpath("//*[@id=\"form_group_category_0_id\"]"));
			WebElement gTable1 = gForm1.findElement(By.tagName("table"));
			gTable1.findElement(By.cssSelector("img[alt=\"Group members\"]")).click();
			Thread.sleep(3000);
			Select fromCombo = new Select(driver.findElement(By.id("group_members-f")));
			List<WebElement> optionsF = fromCombo.getOptions();
			String m1 = optionsF.get(0).getText();
			optionsF.get(0).click();
			driver.findElement(By.xpath("/html/body/div[1]/section/div/form/fieldset/div[1]/div[1]/div/div[2]/div/button[1]/em")).click();
			Select toCombo = new Select(driver.findElement(By.id("group_members-t")));
			List<WebElement> optionsT = toCombo.getOptions();
			String m2 = optionsT.get(0).getText();
			Boolean status = false;
			if(optionsT.size()!=0)
			{
			driver.findElement(By.id("group_edit_submit")).click();
			String success = driver.findElement(By.xpath("//*[@id=\"content-section\"]/div/div[2]")).getText();
			status = success.matches("Group settings modified");
			}
			return status;
		}
		
		public Boolean editSettings()
		{
			driver.findElement(By.cssSelector("img[alt=\"Edit settings\"]")).click();
			WebElement esForm = driver.findElement(By.xpath("//*[@id=\"group_category\"]"));
			WebElement esDiv = esForm.findElement(By.xpath("//*[@id=\"group_category\"]/fieldset/div[1]/div[1]/div[1]/div[1]/label"));
			esDiv.findElement(By.xpath("//form[@id='group_category']/fieldset/div/div/div/div/label/input")).click();
			esDiv.findElement(By.xpath("//form[@id='group_category']/fieldset/div/div/div/div[2]/label/input")).click();
			driver.findElement(By.xpath("//*[@id=\"group_category_submit\"]")).click();
			String editMesg = driver.findElement(By.xpath("/html/body/div[1]/section/div/div[2]")).getText();
			Boolean success =false;
			success = editMesg.matches("Group settings have been modified");
			return success;
		}
		
}

