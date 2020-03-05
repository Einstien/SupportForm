package com.mopro.support_base;

import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class Driver {

	public static WebDriver driver;
	public static Actions act;
	protected static JavascriptExecutor js;
	public static WebDriverWait wait;
	public static DataFormatter format = new DataFormatter();

	public static void intiatedriver() {

		driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		act = new Actions(driver);
		js = (JavascriptExecutor)driver;
		wait=new WebDriverWait(driver, 30);
	}

}
