package com.expedia.base_page_packages;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
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
	
	/***
	 * Returns an appropriate by type to find an element if required
	 * @param byType - type of locator to be used passed as String
	 * @param locator - locator value passed as String
	 * @return
	 */
	public By getByType(String byType, String locator) {
		By byValue = null;
		try {
			if(byType.equalsIgnoreCase("id")) {
				byValue = By.id(locator);
			}
			else if(byType.equalsIgnoreCase("xpath")) {
				byValue = By.xpath(locator);
			}
			else if(byType.equalsIgnoreCase("classname")) {
				byValue = By.className(locator);
			}
			else if(byType.equalsIgnoreCase("cssSelector")) {
				byValue = By.cssSelector(locator);
			}
			else if(byType.equalsIgnoreCase("linktext")) {
				byValue = By.linkText(locator);
			}
			else if(byType.equalsIgnoreCase("partialLinkText")) {
				byValue = By.partialLinkText(locator);
			}
			else if(byType.equalsIgnoreCase("tagname")) {
				byValue = By.tagName(locator);
			}
			else if(byType.equalsIgnoreCase("name")) {
				byValue = By.name(locator);
			}
			else {
				log.error("Not a valid locator.");
			}
		}
		catch(Exception e) {
			log.error(e.getMessage());
		}
		return byValue;
	}
	
	/**
	 * Finds webElement if required - combinedLocator sent in form of locatorType=>locator
	 * @param combinedLocator - locatorType=>locatorValue
	 * @return
	 */
	public WebElement getWebElement(String combinedLocator) {
		WebElement webEle = null;
		String[] strArr = combinedLocator.split("=>");
		String locatorType = strArr[0];
		String locator = strArr[1];
		try {
			webEle = driver.findElement(getByType(locatorType, locator));
		}
		catch(Exception e) {
			log.error(e.getMessage());
		}
		return webEle;
	}
	
	/**
	 * Finds list of webElements if required - combinedLocator sent in form of locatorType=>locator
	 * @param combinedLocator - locatorType=>locatorValue
	 * @return
	 */
	public List<WebElement> getWebElements(String combinedLocator) {
		List<WebElement> elements = null;
		String[] strArr = combinedLocator.split("=>");
		String locatorType = strArr[0];
		String locator = strArr[1];
		elements = driver.findElements(getByType(locatorType, locator));
		return elements;
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
	public void clickWithoutWait(WebElement element) {
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
		jsExe.executeScript("window.scrollBy(0, "+measureScrollDown+");");
	}
	
	/**
	 * Scrolling up one time with the specified measure using java script executor
	 * @param measureScrollDown - amount by which to scroll up.
	 */
	public void scrollUpBy(long measureScrollUp) {
		long absValue = Math.abs(measureScrollUp);
		measureScrollUp = 0 - measureScrollUp;
		jsExe.executeScript("window.scrollBy(0, "+measureScrollUp+");");
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
	
	/***
	 * Finds a single element for the date
	 * @param date - string specifying the date in 'dd mmm yyyy' format
	 * @return
	 */
	public WebElement findDate(String date) {
		WebElement ele = null;
		String xpath = "//button[@aria-label='"+date+"']";
		ele = getWebElement("xpath=>"+xpath);
		GeneralUtility.doHardWaitFor(1000);
		return ele;
	}
	
	/***
	 * Clicks on the date element and goes to next page if required
	 * @param date - string specifying the date in 'dd mmm yyyy' format
	 */
	public void clickDateElementIfExists(String date) {
		scrollDownBy(250);
		WebElement doneButton = getWebElement("xpath=>//span[text()='Done']");
		WebElement nextButton = getWebElement("xpath=>//button[contains(@class, 'uitk-button-paging')][position()=2]");
		try {
			WebElement dateEle = findDate(date);
			clickWithoutWait(dateEle);
			clickWithoutWait(doneButton);
		}
		catch(NoSuchElementException e) {
			List<WebElement> dateElements;
			do {
				clickWithoutWait(nextButton);
				dateElements = getWebElements("xpath=>//button[@aria-label='"+date+"']");
				GeneralUtility.doHardWaitFor(1000);
				if(dateElements.size() != 0) {
					clickWithoutWait(dateElements.get(0));
					clickWithoutWait(doneButton);
					break;
				}
			} while(dateElements.size() == 0);
		}
		log.info("Providing departure date");
	}
}
