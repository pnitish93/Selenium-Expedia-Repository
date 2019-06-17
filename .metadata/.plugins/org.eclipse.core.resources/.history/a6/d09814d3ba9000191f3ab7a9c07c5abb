package frontPage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FlightsPageFactoryClass {
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
	
	@FindBy(xpath="(//button[contains(@class,'action gcw-submit')])[1]")
	WebElement submitBtn;
	
	public FlightsPageFactoryClass(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickFlightsTab() {
		flightsTab.click();
	}
	
	public void clickOneWay() {
		oneWayButton.click();
	}
	
	public void provideOriginCity(String cityValue) {
		originCityField.sendKeys(cityValue);
		List<WebElement> cityList = driver.findElements(By.xpath("//div[@class='autocomplete-dropdown']//b"));
		for(WebElement cityEntry:cityList) {
			if(cityEntry.getText().equalsIgnoreCase(cityValue)) {
				cityEntry.click();
				break;
			}
		}
	}
	
	public void provideDestCity(String cityValue) {
		destinationCityField.sendKeys(cityValue);
		List<WebElement> cityList = driver.findElements(By.xpath("//div[@class='autocomplete-dropdown']//b"));
		for(WebElement cityEntry:cityList) {
			if(cityEntry.getText().equalsIgnoreCase(cityValue)) {
				cityEntry.click();
				break;
			}
		}
	}
	
	public void provideDepartDate(String date) {
		departDate.sendKeys(date);
		driver.findElement(By.xpath("//div[@class='hero-banner-box siteId-27 cf hideClassicDcol']")).click();
	}
	
	public void clickSubmit() {
		submitBtn.click();
	}
}
