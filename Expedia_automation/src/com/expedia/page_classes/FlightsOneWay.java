package com.expedia.page_classes;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.expedia.test_classes.SearchFlightsOneWay;
import com.expedia.utility.GeneralUtility;

/**
 * @author Nitish Panda
 * Automatin of some components of expedia website using Page Factory
 *
 */

public class FlightsOneWay {
	WebDriver driver;
	public static Logger log = LogManager.getLogger(SearchFlightsOneWay.class.getName());
	
	@FindBy(xpath = "//a[@aria-controls='wizard-flight-pwa']")
	private WebElement flightsTab;
	
	@FindBy(xpath = "//a[@aria-controls='wizard-flight-tab-oneway']")
	private WebElement oneWayButton;
	
	@FindBy(xpath = "//button[contains(@aria-label, 'Leaving from')]")
	private WebElement originCityButton;
	
	@FindBy(xpath = "//input[@placeholder='Where are you leaving from?']")
	private WebElement originCityField;
	
	@FindBy(xpath = "//button[contains(@aria-label, 'Going to')]")
	private WebElement destinationCityButton;
	
	@FindBy(xpath = "//input[@placeholder='Where are you going to?']")
	private WebElement destinationCityField;
	
	@FindBy(id = "d1-btn")
	private WebElement departDateButton;
	
	/*@FindBy(xpath="//a[@data-testid='travelers-field']")
	private WebElement travellers;
	
	@FindBy(id = "infant-input-0")
	private WebElement infantNos;
	
	@FindBy(id = "child-input-0")
	private WebElement childrenNos;
	
	@FindBy(id = "adult-input-0")
	private WebElement adultNos;
	
	@FindBy(xpath = "(//div[@class='uitk-flex uitk-flex-item uitk-step-input-controls'])[1]//button[2]")
	private WebElement adultIncrButton;
	
	@FindBy(xpath = "(//div[@class='uitk-flex uitk-flex-item uitk-step-input-controls'])[2]//button[2]")
	private WebElement childIncrButton;
	
	@FindBy(xpath = "(//div[@class='uitk-flex uitk-flex-item uitk-step-input-controls'])[2]//button[1]")
	private WebElement childDecrButton;
	
	@FindBy(xpath = "(//div[@class='uitk-flex uitk-flex-item uitk-step-input-controls'])[3]//button[2]")
	private WebElement infantIncrButton;
	
	@FindBy(xpath = "(//div[@class='uitk-flex uitk-flex-item uitk-step-input-controls'])[3]//button[1]")
	private WebElement infantDecrButton;
	
	@FindBy(xpath = "(//div[@class='uitk-flex uitk-flex-item uitk-step-input-controls'])[1]//button[1]")
	private WebElement adultDecrButton;
	
	@FindBy(xpath = "(//button[@type = 'button' and contains(@class, 'gcw-traveler-amount')])[position() = 2]")
	private WebElement travellersForHotel;
	
	@FindBy(xpath = "//button[text()='Done']")
	private WebElement submitTraveller;
	
	@FindBy(id = "flight-add-hotel-checkbox-hp-flight")
	private WebElement hotelCheckBox;
	
	@FindBy(xpath = "//input[@id='flight-hotel-checkin-hp-flight']")
	private WebElement checkInDate;
	
	@FindBy(xpath = "//input[@id='flight-hotel-checkout-hp-flight']")
	private WebElement checkOutDate;
	
	@FindBy(xpath = "//div[@class='hero-banner-box siteId-27 cf hideClassicDcol']")
	private WebElement outside;*/
	
	@FindBy(xpath = "//button[text()='Search']")
	private WebElement flightSearchButton;
	
	/**
	 * Constructor
	 * @param driver
	 */
	public FlightsOneWay(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * Clicks on flight tab
	 * @return void
	 * @param void
	 */
	public void clickFlightsTab() {
		flightsTab.click();
		log.info("clicking flights tab");
	}
	
	/**
	 * Clicks on one way button
	 * @return void
	 * @param void
	 */
	public void clickOneWay() {
		oneWayButton.click();
		log.info("clicking 'one-way' in flights tab");
	}
	
	/**
	 * sends origin city
	 * @return void
	 * @param cityValue
	 */
	public void provideOriginCity(String cityValue) {
		originCityButton.click();
		originCityField.clear();
		originCityField.sendKeys(cityValue);
		originCityField.sendKeys(Keys.ENTER);
		log.info("providing origin city for one - way flights # " + cityValue);
		//GeneralUtility.findCity(cityValue, "origin", driver);
	}
	
	/**
	 * sends destination city
	 * @return void
	 * @param cityValue
	 */
	public void provideDestCity(String cityValue) {
		destinationCityButton.click();
		destinationCityField.clear();
		destinationCityField.sendKeys(cityValue);
		destinationCityField.sendKeys(Keys.ENTER);
		log.info("providing destination city for one - way flights as " + cityValue);
		//GeneralUtility.findCity(cityValue, "destination", driver);
	}
	
	/**
	 * Sends the departure date
	 * @return void
	 * @param date
	 * @throws InterruptedException 
	 */
	public void provideDepartDate(String date) {
		departDateButton.click();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement calElement = driver.findElement(By.xpath("//div[@class = 'uitk-new-date-picker date-picker-menu']"));
		js.executeScript("window.scrollBy(0, 250);");
		String dateElement = "//button[@aria-label='"+date+"']";
		WebElement doneButton = driver.findElement(By.xpath("//span[text()='Done']"));
		WebElement nextButton = driver.findElement(By.xpath("//button[contains(@class, 'uitk-button-paging')][position()=2]"));
		try {
			WebElement dateEle = driver.findElement(By.xpath(dateElement));
			GeneralUtility.doHardWaitFor(1000);
			dateEle.click();
			doneButton.click();
		}
		catch(NoSuchElementException e) {
			List<WebElement> dateElementFind;
			do {
				nextButton.click();
				dateElementFind = driver.findElements(By.xpath(dateElement));
				GeneralUtility.doHardWaitFor(1000);
				if(dateElementFind.size() != 0) {
					dateElementFind.get(0).click();
					doneButton.click();
					break;
				}
			} while(dateElementFind.size() == 0);
		}
		log.info("Providing departure date");
		
		/*int[] dateArr = GeneralUtility.splitDate(date);
		Thread.sleep(2000);
		departDateButton.click();
		WebElement dateButton = GeneralUtility.pickDates(dateArr[2], dateArr[1], dateArr[0], driver);
		dateButton.click();*/
	}
	
	/**
	 * Checks the 'Add a hotel' option
	 */
	/*public void checkHotelOption() {
		if(hotelCheckBox.isSelected()) {
			hotelCheckBox.click();
		}
		hotelCheckBox.click();
	}*/
	
	/**
	 * Provides the hotel check in date
	 * @param date
	 * @throws InterruptedException 
	 */
	/*public void provideCheckInDate(String date) throws InterruptedException {
		checkInDate.clear();
		int[] dateArr = GeneralUtility.splitDate(date);
		Thread.sleep(2000);
		checkInDate.click();
		WebElement dateButton = GeneralUtility.pickDates(dateArr[2], dateArr[1], dateArr[0], driver);
		dateButton.click();
	}*/
	
	/**
	 * Provides the hotel check out date
	 * @param date
	 * @throws InterruptedException 
	 */
	/*public void provideCheckOutDate(String date) throws InterruptedException {
		checkOutDate.clear();
		int[] dateArr = GeneralUtility.splitDate(date);
		Thread.sleep(2000);
		checkOutDate.click();
		WebElement dateButton = GeneralUtility.pickDates(dateArr[2], dateArr[1], dateArr[0], driver);
		dateButton.click();
	}*/
	
	/**
	 * Clicks on submit button after entering all the necessary details 
	 * @return void
	 */
	public FlightsResultPage searchFlights() {
		flightSearchButton.click();
		log.info("Searching flights using Search button");
		GeneralUtility.doHardWaitFor(3000);
		return new FlightsResultPage(driver);
	}
	
	/*public void selectTravellers(int adults, int children, int infants) {
		travellers.click();
		int NumberOfAdults = Integer.parseInt(adultNos.getAttribute("value"));
		int NumberOfChildren = Integer.parseInt(childrenNos.getAttribute("value"));
		int NumberOfInfants = Integer.parseInt(infantNos.getAttribute("value"));
		if(NumberOfAdults < adults) {
			for(int i=NumberOfAdults; i<adults; i++) {
				adultIncrButton.click();
			}
		}
		else if(NumberOfAdults > adults) {
			for(int i=NumberOfAdults; i>adults; i--) {
				adultDecrButton.click();
			}
		}
		if(NumberOfChildren < children) {
			for(int i=NumberOfChildren; i<children; i++) {
				childIncrButton.click();
			}
		}
		else if(NumberOfChildren > children) {
			for(int i=NumberOfChildren; i>children; i--) {
				childDecrButton.click();
			}
		}
		if(NumberOfInfants < infants) {
			for(int i=NumberOfInfants; i<infants; i++) {
				infantIncrButton.click();
			}
		}
		else if(NumberOfInfants > infants) {
			for(int i=NumberOfInfants; i>infants; i--) {
				infantDecrButton.click();
			}
		}
		submitTraveller.click();
	//Xpath for the list of age dropdown elements for children in one way only flights
	//div[contains(@class,'children-data') and @data-gcw-field-available-for-sub-nav-option='roundtrip,oneway,multi']//label[not(contains(@class, 'gcw-disabled'))]//select
	}
	
	public void selectAgeOfMinors(String childrenAges, String infantAges) {
		String[] childAgeList = GeneralUtility.getAges(childrenAges);
		String[] infantAgeList = GeneralUtility.getAges(infantAges);
		WebElement childAgeSection = driver.findElement(By.className("cols-nested children-data gcw-toggles-fields available-for-flights"));
		List<WebElement> childAgeDropdowns = childAgeSection.findElements(By.xpath("//div[contains(@class,'children-data') and @data-gcw-field-available-for-sub-nav-option='roundtrip,oneway,multi']//label[not(contains(@class, 'gcw-disabled'))]//select"));
	}*/
	
	public void clearPopulatedCities() throws InterruptedException {
		Thread.sleep(1000);
		originCityButton.click();
		originCityField.clear();
		destinationCityButton.click();
		destinationCityField.clear();
	}
}
