package flights;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Nitish Panda
 *
 */
/**
 * @author Lenovo
 *
 */
public class FlightsOneWay {
	WebDriver driver;
	
	@FindBy(id = "tab-flight-tab-hp")
	WebElement flightsTab;
	
	@FindBy(id = "flight-type-one-way-label-hp-flight")
	WebElement oneWayButton;
	
	@FindBy(xpath = "//input[@id='flight-origin-hp-flight']")
	WebElement originCityField;
	
	@FindBy(xpath = "//input[@id='flight-destination-hp-flight']")
	WebElement destinationCityField;
	
	@FindBy(xpath = "//input[@id='flight-departing-single-hp-flight']")
	WebElement departDate;
	
	@FindBy(xpath="(//button[@data-gcw-component=\"gcw-traveler-amount-select\"])[position()=1]")
	WebElement travellers;
	
	@FindBy(xpath="(//button[contains(@class,'action gcw-submit')])[1]")
	WebElement submitBtn;
	
	@FindBy(id="flight-add-hotel-checkbox-hp-flight")
	WebElement hotelCheckBox;
	
	@FindBy(xpath="//input[@id='flight-hotel-checkin-hp-flight']")
	WebElement checkInDate;
	
	@FindBy(xpath="//input[@id='flight-hotel-checkout-hp-flight']")
	WebElement checkOutDate;
	
	@FindBy(xpath="//div[@class='flight-hotel-room-data']//button[@data-gcw-component='gcw-traveler-amount-select']")
	WebElement visitorsAndRoom;
	
	@FindBy(xpath="//div[@class='hero-banner-box siteId-27 cf hideClassicDcol']")
	WebElement outside;
	
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
	}
	
	/**
	 * Clicks on one way button
	 * @return void
	 * @param void
	 */
	public void clickOneWay() {
		oneWayButton.click();
	}
	
	/**
	 * sends origin city
	 * @return void
	 * @param cityValue
	 */
	public void provideOriginCity(String cityValue) {
		originCityField.clear();
		originCityField.sendKeys(cityValue);
		GeneralUtility.findCity(cityValue, "origin", driver);
	}
	
	/**
	 * sends destination city
	 * @return void
	 * @param cityValue
	 */
	public void provideDestCity(String cityValue) {
		destinationCityField.clear();
		destinationCityField.sendKeys(cityValue);
		GeneralUtility.findCity(cityValue, "destination", driver);
	}
	
	/**
	 * Sends the departure date
	 * @return void
	 * @param date
	 * @throws InterruptedException 
	 */
	public void provideDepartDate(String date) throws InterruptedException {
		departDate.clear();
		int[] dateArr = GeneralUtility.splitDate(date);
		Thread.sleep(2000);
		departDate.click();
		WebElement dateButton = GeneralUtility.pickDates(dateArr[2], dateArr[1], dateArr[0], driver);
		dateButton.click();
	}
	
	/**
	 * Checks the 'Add a hotel' option
	 */
	public void checkHotelOption() {
		if(hotelCheckBox.isSelected()) {
			hotelCheckBox.click();
		}
		hotelCheckBox.click();
	}
	
	/**
	 * Provides the hotel check in date
	 * @param date
	 * @throws InterruptedException 
	 */
	public void provideCheckInDate(String date) throws InterruptedException {
		checkInDate.clear();
		int[] dateArr = GeneralUtility.splitDate(date);
		Thread.sleep(2000);
		checkInDate.click();
		WebElement dateButton = GeneralUtility.pickDates(dateArr[2], dateArr[1], dateArr[0], driver);
		dateButton.click();
	}
	
	/**
	 * Provides the hotel check out date
	 * @param date
	 * @throws InterruptedException 
	 */
	public void provideCheckOutDate(String date) throws InterruptedException {
		checkOutDate.clear();
		int[] dateArr = GeneralUtility.splitDate(date);
		Thread.sleep(2000);
		checkOutDate.click();
		WebElement dateButton = GeneralUtility.pickDates(dateArr[2], dateArr[1], dateArr[0], driver);
		dateButton.click();
	}
	
	/**
	 * Clicks on submit button after entering all the necessary details 
	 * @return void
	 */
	public void clickSubmit() {
		submitBtn.click();
	}
}
