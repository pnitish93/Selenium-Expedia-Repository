package flights;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class SearchPageTest {
  WebDriver driver;
  String baseUrl;
  Flights_OneWay oneWaySearchPage;
  
  @Test(dataProvider="searchInputs", dataProviderClass=DataProviderClass.class)
  public void searchFlightsPositive(String from, String to, String date) throws InterruptedException {
	  oneWaySearchPage.clickFlightsTab();
	  oneWaySearchPage.clickOneWay();
	  oneWaySearchPage.provideOriginCity(from);
	  Thread.sleep(3000);
	  oneWaySearchPage.provideDestCity(to);
	  Thread.sleep(3000);
	  oneWaySearchPage.provideDepartDate(date);
	  oneWaySearchPage.clickSubmit();
	  try {
		  driver.findElement(By.xpath("//span[contains(text(), 'departure to')]"));
	  }
	  catch(Exception e) {
		  Assert.assertTrue(false);
	  }
  }
  @BeforeClass
  public void beforeClass() {
	  System.setProperty("webdriver.chrome.driver", "F:\\Selenium\\Drivers\\chromedriver.exe");
	  driver = new ChromeDriver();
	  baseUrl = "https://www.expedia.co.in/";
	  oneWaySearchPage = new Flights_OneWay(driver);
	  driver.get(baseUrl);
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
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }
  

}
