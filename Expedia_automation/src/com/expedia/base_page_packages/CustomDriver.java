package com.expedia.base_page_packages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.expedia.utility.GeneralUtility;

public class CustomDriver {
	WebDriver driver;
	JavascriptExecutor jsExe;
	static Logger log = LogManager.getLogger(CustomDriver.class.getName());
	
	public CustomDriver(WebDriver driver) {
		this.driver = driver;
		jsExe = (JavascriptExecutor)driver;
	}
	
	/**
	 * Used to click an element and wait a specified time
	 * @param element - web element to be clicked on.
	 * @param waitTimeInMilliSecs - waiting time.
	 */
	public void clickAndWait(WebElement element, int waitTimeInMilliSecs) {
		try {
			element.click();
			if(waitTimeInMilliSecs != 0) {
				GeneralUtility.doHardWaitFor(waitTimeInMilliSecs);
			}
		}
		catch(Exception e) {
			log.error("Cannot click on the element.");
		}
		
	}
	
	/**
	 * Overloaded method - Used to click an without any wait
	 * @param element - web element to be clicked on.
	 */
	public void clickAndWait(WebElement element) {
		clickAndWait(element, 0);		
	}
	
	/**
	 * Used to send text to an element and clear it if required
	 * @param element - web element where text is to be entered.
	 * @param wantToClear - if true, then clears the field.
	 * @param text - text to be typed.
	 */
	public void sendTextToElement(WebElement element, boolean wantToClear, String text) {
		try {
			if(wantToClear) {
				element.clear();
			}
			element.sendKeys(text);
		}
		catch(Exception e) {
			log.error("Cannot type text in the element.");
		}
		
	}
	
	
	/**
	 * Scrolling down one time with the specified measure using java script executor
	 * @param measureScrollDown - amount by which to scroll down.
	 */
	public void scrollDownBy(long measureScrollDown) {
		long absValue = Math.abs(measureScrollDown);
		jsExe.executeScript("window.scrollBy(0"+measureScrollDown+");");
	}
	
	/**
	 * Scrolling up one time with the specified measure using java script executor
	 * @param measureScrollDown - amount by which to scroll up.
	 */
	public void scrollUpBy(long measureScrollUp) {
		long absValue = Math.abs(measureScrollUp);
		measureScrollUp = 0 - measureScrollUp;
		jsExe.executeScript("window.scrollBy(0"+measureScrollUp+");");
	}
	
	/**
	 * Sending special keystrokes like
	 * @param element
	 * @param keyStrokes
	 */
	public void sendSpecialKeyStrokes(WebElement element, Keys keyStrokes) {
		try {
			element.sendKeys(keyStrokes);
		}
		catch(Exception e) {
			log.error("Special keystrokes cannot be sent to the element");
		}
	}
}
