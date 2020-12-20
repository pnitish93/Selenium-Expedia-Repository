package com.expedia.utility;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GeneralUtility {
	/**
	 * Finds the best match from the autocomplete list of cities
	 * @return void
	 * @param cityValue
	 * @param cityStatus - origin or destination
	 */
	public static void findCity(String cityValue, String cityStatus, WebDriver driver) {
		String cityValueCapital = capitaliseCity(cityValue);
		String cityElement = "//strong[contains(text(),'"+cityValueCapital+"')]";
		driver.findElement(By.xpath(cityElement)).click();
		/*List<WebElement> cityList = null;
		if(cityStatus.equals("origin")) {
			cityList = driver.findElements(By.xpath("(//div[@class='autocomplete-dropdown'])[1]//li//a"));
		}
		if(cityStatus.equals("destination")){
			cityList = driver.findElements(By.xpath("(//div[@class='autocomplete-dropdown'])[2]//li//a"));
		}
		for(WebElement cityEntry:cityList) {
			if(cityEntry.getAttribute("data-value").contains(cityValueCapital)) {
				//System.out.println(cityEntry.getAttribute("data-value"));
				cityEntry.click();
				break;
			}
		}*/
	}
	
	public static void doHardWaitFor(long timeInMilliSecs) {
		try{
			Thread.sleep(timeInMilliSecs);
		}
		catch(InterruptedException e) {
			System.out.println("An exception occured. Please review your code");
			e.printStackTrace();
		}
	}
	
	/**
	 * Makes the first letter of each word capital for city name
	 * @param cityValue
	 * @return String
	 */
	public static String capitaliseCity(String cityValue) {
		char[] cityArray = cityValue.toCharArray();
		for(int i=0; i < cityArray.length; i++) {
			if(i == 0 && cityArray[i] != ' ' || cityArray[i] != ' ' && cityArray[i-1] == ' ') {
				if(cityArray[i] >= 'a' && cityArray[i] <= 'z') {
					cityArray[i] = (char)(cityArray[i]-('a'-'A'));
				}
			}
		}
		String capitalised = new String(cityArray);
		return capitalised;
	}
	
	/**
	 * Splits the string date into integers - year, month and date
	 * @param date
	 * @return
	 */
	public static int[] splitDate(String date) {
		String[] fragment = date.split("/");
		int [] dateFragments = new int[fragment.length];
		for(int i=0; i<fragment.length; i++) {
			dateFragments[i] = Integer.parseInt(fragment[i]);
		}
		return dateFragments;
	}
	
	/**
	 * Select date from the datepicker
	 * @param year
	 * @param month
	 * @param date
	 * @return
	 */
	public static WebElement pickDates(int year, int month, int date, WebDriver driver) {
		WebElement dateButton = null;
		int eleMonth = month-1;
		String commonXpath = "//button[@data-year='"+year+"' and @data-month='"+eleMonth+"']";
		List<WebElement> dateButtons = driver.findElements(By.xpath(commonXpath));
		while(dateButtons.size() == 0) {
			driver.findElement(By.xpath("//button[@class='datepicker-paging datepicker-next btn-paging btn-secondary next']")).click();
			dateButtons = driver.findElements(By.xpath(commonXpath));
		}
		for(WebElement dateBtn:dateButtons) {
			if(Integer.parseInt(dateBtn.getAttribute("data-day"))==date) {
				dateButton = dateBtn;
				break;
			}
		}
		return dateButton;
	}
	
	/**
	 * Splits the comma separated children and infants' ages
	 * @param ageList
	 * @return array of Strings
	 */
	public static String[] getAges(String ageList) {
		String[] ages = ageList.split(",");
		return ages;
	}
	
	public static void setAgesOfMinors(int children, int infants, String childrenAgeList, String infantAgeList) {
		if(children != 0) {
			
		}
	}
}
