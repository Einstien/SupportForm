package com.mopro.support_base;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Driver {

	public static WebDriver driver;
	public static Actions act;
	protected static JavascriptExecutor js;
	public static WebDriverWait wait;
	public static DataFormatter format = new DataFormatter();
	static DesiredCapabilities cap;

	public static void intiatedriver() throws MalformedURLException {
		
		//System.setProperty("webdriver.gecko.driver","G:\\Software Files\\Driver\\geckodriver.exe");

		cap = DesiredCapabilities.chrome();
		cap.setPlatform(Platform.WINDOWS);
		
		URL url = new URL("http://localhost:4444/wd/hub");

		//driver = new ChromeDriver();
		
		driver = new RemoteWebDriver(url, cap);

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		act = new Actions(driver);
		js = (JavascriptExecutor) driver;
		wait = new WebDriverWait(driver, 30);
	}

}
