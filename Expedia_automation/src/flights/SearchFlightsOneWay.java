package flights;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class SearchFlightsOneWay {
	WebDriver driver;
	String baseUrl;
	static FlightsOneWay oneWaySearchPage;
	public static Logger log = LogManager.getLogger(SearchFlightsOneWay.class.getName());

	/*
	 * Method for all the common operations for both the test methods
	 */
	private static void flightSearchOperation(String from, String to, String date) throws InterruptedException {
		oneWaySearchPage.clickFlightsTab();
		log.info("clicking flights tab");
		oneWaySearchPage.clickOneWay();
		log.info("clicking 'one-way' in flights tab");
		oneWaySearchPage.provideOriginCity(from);
		log.info("providing origin city for one - way flights # " + from + " to " + to);
		Thread.sleep(3000);
		oneWaySearchPage.provideDestCity(to);
		log.info("providing destination city for one - way flights # " + from + " to " + to);
		Thread.sleep(3000);
		oneWaySearchPage.provideDepartDate(date);
		log.info("Providing departure date # " + from + " to " + to);
	}

	public void scrollDownResults(int count) throws InterruptedException {
		if (count <= 10) {
			JavascriptExecutor jsExe = (JavascriptExecutor) driver;
			long screenHeight = (Long) jsExe.executeScript("return window.innerHeight;");
			for (int i = 0; i < count; i++) {
				jsExe.executeScript("window.scrollBy(0, arguments[0]);", screenHeight);
				Thread.sleep(1000);
			}
		} else if(count <= 0) {
			log.info("Please enter a value between 1 and 10 (Both inclusive).");
		}
		else {
			log.info("Value provided exceeds 10, which is not allowed");
		}
	}

	public void scrollToEnd() {
		JavascriptExecutor jsExe = (JavascriptExecutor) driver;
		WebElement verizonLink = driver.findElement(By.xpath("//img[contains(@alt, 'Verizon Cybertrust Security')]"));
		jsExe.executeScript("arguments[0].scrollIntoView(true);", verizonLink);
	}

	/**
	 * Method to search positive cases of one way flights only
	 * 
	 * @param from
	 * @param to
	 * @param date
	 * @throws InterruptedException
	 */
	@Test(dataProvider = "searchInputsOnlyFlights", dataProviderClass = DataProviderClassOneWay.class)
	public void searchFlightsOnlyPositive(String from, String to, String date) throws InterruptedException {
		SearchFlightsOneWay.flightSearchOperation(from, to, date);
		Thread.sleep(2000);
		oneWaySearchPage.clickSubmit();
		log.info("clicking on the 'submit' button # " + from + " to " + to);
		// Explicit Wait waits for the appearance of result section
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement resultText = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//span[contains(text(), 'Select your departure to')]")));
		//Uncomment the following method call to scroll through results within 10 times (specified in argument)
		//scrollDownResults(5);
		//Uncomment the following method call to scroll till the end
		//scrollToEnd();
	}

	/**
	 * Method to search positive cases of one way flights with hotels
	 * 
	 * @param from
	 * @param to
	 * @param date
	 * @param checkInDt
	 * @param checkOutDt
	 * @throws InterruptedException
	 */
	@Test(dataProvider = "searchInputs", dataProviderClass = DataProviderClassOneWay.class)
	public void searchFlightsWithHotelPositive(String from, String to, String date, String checkInDt, String checkOutDt)
			throws InterruptedException {
		flightSearchOperation(from, to, date);
		oneWaySearchPage.checkHotelOption();
		oneWaySearchPage.provideCheckInDate(checkInDt);
		Thread.sleep(3000);
		oneWaySearchPage.provideCheckOutDate(checkOutDt);
		oneWaySearchPage.clickSubmit();
		// Explicit Wait waits for the appearance of result section
		WebDriverWait hotelWait = new WebDriverWait(driver, 15);
		WebElement resultIndicator = hotelWait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//h1[contains(text(), 'Start by choosing your hotel')]")));
		//Uncomment the following method call to scroll through results within 10 times (specified in argument)
		//scrollDownResults(5);
		//Uncomment the following method call to scroll till the end
		//scrollToEnd();
	}

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "F:\\Selenium\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		oneWaySearchPage = new FlightsOneWay(driver);
		baseUrl = Constants.URL;
		driver.get(baseUrl);
		log.info("Launching website in Chrome");
		driver.manage().window().maximize();
	}

	@BeforeMethod
	public void beforeMethod() {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}

	@AfterMethod
	public void afterMethod() throws InterruptedException {
		Thread.sleep(4000);
		driver.navigate().back();
	}

	@AfterClass(alwaysRun = false)
	public void afterClass() {
		driver.quit();
	}

}
