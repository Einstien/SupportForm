package com.mopro.support_pages;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.mopro.support.utilities.CommonMethods;
import com.mopro.support_base.Driver;

public class SupportForm extends Driver {

	@FindBy(how = How.ID, using = "txtFirstName")
	WebElement firstNameField;

	@FindBy(how = How.ID, using = "txtLastName")
	WebElement lastNameField;

	@FindBy(how = How.ID, using = "txtEmail")
	WebElement emailField;

	@FindBy(how = How.ID, using = "txtWebsiteUrl")
	WebElement webURL;

	@FindBy(how = How.XPATH, using = "//select[@class='minimalect']")
	WebElement topicDropdown;

	@FindBy(how = How.XPATH, using = "//input[@multiple='multiple']")
	WebElement uploadFile;

	@FindBy(how = How.ID, using = "txtDesc")
	WebElement descField;

	@FindBy(how = How.XPATH, using = "//a[@class='btn btn-large']")
	WebElement submitBtn;

	public SupportForm() {
		PageFactory.initElements(driver, this);
	}

	public void selectCat(String catName) {

		String xpathValu = catName;

		if (xpathValu.isBlank()) {
			System.out.println("No Category Name to Select");
			return;
		} else {
			By elem = By.xpath("//h5[contains(text(), '" + xpathValu + "')]");
			wait.until(ExpectedConditions.visibilityOfElementLocated(elem));
			WebElement categories = driver.findElement(elem);
			act.moveToElement(categories);
			act.click().build().perform();
		}
	}

	public void updateForm(String selectDropdown, String firstName, String lastName, String emailId, String websiteURL,
			String description, String uploadFolderPath) throws InterruptedException {

		update: {

			Thread.sleep(100);

			String a = firstName;
			String b = lastName;
			String c = emailId;
			String d = websiteURL;
			String e = selectDropdown;
			String f = description;
			String g = uploadFolderPath;

			firstNameField.sendKeys(a);
			lastNameField.sendKeys(b);

			if (c.isBlank()) {
				System.out.println("No Email ID");
				return;
			} else {
				emailField.sendKeys(c);
			}

			webURL.sendKeys(d);
			js.executeScript("var vis = document.getElementsByClassName('minimalect')[0]; vis.style.display='block';");

			Boolean isPresent = topicDropdown.isDisplayed();

			if (isPresent) {
				Select sel = new Select(topicDropdown);
				if (e.isBlank()) {
					System.out.println("Need Dropdown Value, which Dropdown to Select");
					return;
				} else {
					List<WebElement> options = sel.getOptions();
					List<String> items = new ArrayList<String>();
					for (WebElement ele : options) {
						items.add(ele.getText());						
					}
					if (items.contains(e)) {
						sel.selectByVisibleText(e);
						System.out.println("Dropdown values Matched");
					} else {
						System.out.println("Given Dropdown Value not matched with dropdown option ");
						break update;
					}
				}

			} 
				descField.sendKeys(f);
				File folder = new File(g);
				File[] files = folder.listFiles();
				String filesList = "";

				for (int i = 0; i < files.length; i++) {
					filesList = files[i].getAbsolutePath();
					uploadFile.sendKeys(filesList);
				}
				
				Thread.sleep(30000);
				
				if (CommonMethods.isAlertPresent()) {					
					System.out.println(driver.switchTo().alert().getText());
					driver.switchTo().alert().accept();					
				}
				
				act.moveToElement(submitBtn).click().build().perform();			
				
				
		}
	}
}
