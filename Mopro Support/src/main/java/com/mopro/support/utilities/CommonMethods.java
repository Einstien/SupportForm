package com.mopro.support.utilities;

import org.openqa.selenium.NoAlertPresentException;

import com.mopro.support_base.Driver;

public class CommonMethods extends Driver {	
	
	
	public static boolean isAlertPresent() 
	{ 
	    try 
	    { 
	        driver.switchTo().alert(); 
	        return true; 
	    }    
	    catch (NoAlertPresentException Ex) 
	    { 
	        return false; 
	    }   
	}  

}
