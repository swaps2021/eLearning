package com.training.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class NewGroupPOM {
	private WebDriver driver;
			
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
		
		@FindBy(linkText="My courses")
		private WebElement myCourses;
		
		@FindBy(linkText="Selenium - Groups")
		private WebElement courseName;
		
		@FindBy(linkText="Groups")
		private WebElement groupsLink;
		
		//This method will navigate to Course - Groups Page
		public void enterCourse() throws InterruptedException 
		{
			this.myCourses.click();
			this.courseName.click();
			this.groupsLink.click();
			Thread.sleep(3000);
			System.out.println("Entering Groups Page");
		}
		
		@FindBy(xpath="/html/body/div[1]/section/div/div[2]/div/div[1]/a[1]/img")
		private WebElement createNewGroupIcon;
		
		@FindBy(xpath="//button[@id='create_groups_submit']")
		private WebElement proceedButton;
		
		@FindBy(xpath="//*[@id=\"create_groups_step2\"]/div/table/tbody/tr[2]/td[2]/input")
		private WebElement groupNameTextBox;
		
		@FindBy(id="create_groups_step2_submit")
		private WebElement createGroupButton;
		
		@FindBy(xpath="/html/body/div[1]/section/div/div[2]")
		private WebElement successMesg;
		
		//This method will create a new group with the name parameter passed
		public String createGroup(String name) throws InterruptedException
		{
			this.createNewGroupIcon.click();
			Thread.sleep(3000);
			this.proceedButton.click();
			this.groupNameTextBox.clear();
			this.groupNameTextBox.sendKeys(name);
			this.createGroupButton.click();
			String mesg = this.successMesg.getText();
			return mesg;
		}
		
		@FindBy(linkText="Groups")
		private WebElement groupsTab;
		
		//This method will verify is the newly created group is present in the groups page
		public Boolean groupExists(String name) throws InterruptedException
		{
			Thread.sleep(5000);
			WebElement gDiv = this.groupsTab.findElement(By.xpath("//*[@id=\"content-section\"]/div/div[4]"));
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
		
		@FindBy(id="group_members-f")
		private WebElement fromComboBox;
		
		@FindBy(id="group_members-t")
		private WebElement toComboBox;
		
		@FindBy(xpath="/html/body/div[1]/section/div/form/fieldset/div[1]/div[1]/div/div[2]/div/button[1]/em")
		private WebElement addFromButton;
		
		@FindBy(id="group_edit_submit")
		private WebElement submitButton;
		
		@FindBy(xpath="//*[@id=\"content-section\"]/div/div[2]")
		private WebElement addMemberSuccess;
		
		//This method will add group members to the new Group
		public Boolean addGroupMembers() throws InterruptedException
		{
			WebElement gDiv1 = this.groupsTab.findElement(By.xpath("//*[@id=\"content-section\"]/div/div[4]"));
			WebElement gForm1 = gDiv1.findElement(By.xpath("//*[@id=\"form_group_category_0_id\"]"));
			WebElement gTable1 = gForm1.findElement(By.tagName("table"));
			gTable1.findElement(By.cssSelector("img[alt=\"Group members\"]")).click(); //Clicking on groupMembers Icon against the group Name
			Thread.sleep(3000);
			Select fromCombo = new Select(this.fromComboBox);
			List<WebElement> optionsF = fromCombo.getOptions();
			optionsF.get(0).click();//Selection 1st member from the Combox
			this.addFromButton.click();//
			Select toCombo = new Select(this.toComboBox);
			List<WebElement> optionsT = toCombo.getOptions();//Getting data from toComboBox
			Boolean status = false;
			if(optionsT.size()!=0) // Checking if to ComboBox is empty
			{
			this.submitButton.click();
			String success = this.addMemberSuccess.getText();
			status = success.matches("Group settings modified");
			}
			return status;
		}
		
		@FindBy(css="img[alt=\"Edit settings\"]")
		private WebElement editSettingsIcon;
		
		@FindBy(xpath="//*[@id=\"group_category_submit\"]")
		private WebElement editSubmit;
		
		@FindBy(xpath="/html/body/div[1]/section/div/div[2]")
		private WebElement editSuccess;
		
		//This method will verify that group settings can be edited
		public Boolean editSettings()
		{
			this.editSettingsIcon.click();
			WebElement esForm = driver.findElement(By.xpath("//*[@id=\"group_category\"]"));
			WebElement esDiv = esForm.findElement(By.xpath("//*[@id=\"group_category\"]/fieldset/div[1]/div[1]/div[1]/div[1]/label"));
			esDiv.findElement(By.xpath("//form[@id='group_category']/fieldset/div/div/div/div/label/input")).click();
			esDiv.findElement(By.xpath("//form[@id='group_category']/fieldset/div/div/div/div[2]/label/input")).click();
			this.editSubmit.click();
			String editMesg = this.editSuccess.getText();
			Boolean success =false;
			success = editMesg.matches("Group settings have been modified");
			return success;
		}
		
}

