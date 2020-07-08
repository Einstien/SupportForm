package com.support.test;

import java.net.MalformedURLException;

import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.mopro.support.utilities.Excel;
import com.mopro.support_base.Driver;
import com.mopro.support_pages.SupportForm;

public class SupportFormTest extends Driver {

	SupportForm support;
	String url = "http://create.qa.cmlmediasoft.com/177637/support";

	@BeforeMethod
	public void setup() throws MalformedURLException {
		intiatedriver();
		support = new SupportForm();
	}
	
	@Test
	public void checkURL() {		
		driver.get(url);
		Assert.assertEquals(driver.getTitle(), "Support");		
	}
	

	@Test
	public void supportFormTestExcel() throws InterruptedException {

		String sheetName = "SupportForm";		
		String ctaXpath = "//a[@id='ctl01_rptSpan_ctl01_anchorlement']";
		Sheet sh = Excel.readExcel(sheetName);		

		for (int i = 1; i <= sh.getLastRowNum(); i++) {

			exceData: {

				driver.get(url);
				js.executeScript("return document.readyState").equals("complete");
				Thread.sleep(1000);

				driver.findElement(By.xpath(ctaXpath)).click();
				
				String catName = Excel.getValues(sheetName, i, 0).toString();
				if (catName.length() == 0)
					break exceData;
				support.selectCat(catName);

				String dropDown = Excel.getValues(sheetName, i, 1).toString();
				String firstName = Excel.getValues(sheetName, i, 2).toString();
				String lastName = Excel.getValues(sheetName, i, 3).toString();
				String emailID = Excel.getValues(sheetName, i, 4).toString();
				String webURL = Excel.getValues(sheetName, i, 5).toString();
				String descr = Excel.getValues(sheetName, i, 6).toString();
				String files = Excel.getValues(sheetName, i, 7).toString();

				support.updateForm(dropDown, firstName, lastName, emailID, webURL, descr, files);				
				
				WebElement success = driver.findElement(By.xpath("//div[@class='note thanks-note']"));								
				Assert.assertTrue(success.isDisplayed());
				System.out.println(success.getText());	

			}

		}

	}

	@AfterTest
	public void closeDriver() {
		driver.quit();
	}
}
