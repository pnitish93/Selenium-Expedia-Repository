package flights;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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
  
  private static void flightSearchOperation(String from, String to, String date) throws InterruptedException {
	  oneWaySearchPage.clickFlightsTab();
	  log.info("clicking flights tab");
	  oneWaySearchPage.clickOneWay();
	  log.info("clicking 'one-way' in flights tab");
	  oneWaySearchPage.provideOriginCity(from);
	  log.info("providing origin city for one - way flights # "+from+" to "+to);
	  Thread.sleep(3000);
	  oneWaySearchPage.provideDestCity(to);
	  log.info("providing destination city for one - way flights # "+from+" to "+to);
	  Thread.sleep(3000);
	  oneWaySearchPage.provideDepartDate(date);
	  log.info("Providing departure date # "+from+" to "+to);
  }
  
  @Test(dataProvider="searchInputsOnlyFlights", dataProviderClass = DataProviderClassOneWay.class)
  public void searchFlightsOnlyPositive(String from, String to, String date) throws InterruptedException {
	  SearchFlightsOneWay.flightSearchOperation(from, to, date);
	  Thread.sleep(2000);
	  oneWaySearchPage.clickSubmit();
	  log.info("clicking on the 'submit' button # "+from+" to "+to);
	  /*try {
		  driver.findElement(By.xpath("//span[contains(text(), 'departure to')]"));
	  }
	  catch(Exception e) {
		  Assert.assertTrue(false);
	  }*/
  }
  @Test(dataProvider = "searchInputs", dataProviderClass = DataProviderClassOneWay.class)
  public void searchFlightsWithHotelPositive (String from, String to, String date, String checkInDt, String checkOutDt) throws InterruptedException {
	  flightSearchOperation(from, to, date);
	  oneWaySearchPage.checkHotelOption();
	  oneWaySearchPage.provideCheckInDate(checkInDt);
	  Thread.sleep(3000);
	  oneWaySearchPage.provideCheckOutDate(checkOutDt);
	  oneWaySearchPage.clickSubmit();
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
