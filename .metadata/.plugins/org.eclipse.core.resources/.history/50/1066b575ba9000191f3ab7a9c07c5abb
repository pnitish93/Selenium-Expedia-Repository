package frontPage;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class SearchPageTest {
  WebDriver driver;
  String baseUrl;
  FlightsPageFactoryClass oneWaySearchPage;
  @Test
  public void search() throws InterruptedException {
	  oneWaySearchPage.clickFlightsTab();
	  oneWaySearchPage.clickOneWay();
	  oneWaySearchPage.provideOriginCity("Ahmedabad");
	  Thread.sleep(2000);
	  oneWaySearchPage.provideDestCity("Delhi");
	  Thread.sleep(2000);
	  oneWaySearchPage.provideDepartDate("04/08/2019");
	  oneWaySearchPage.clickSubmit();
  }
  @BeforeClass
  public void beforeClass() {
	  System.setProperty("webdriver.chrome.driver", "F:\\Selenium\\Drivers\\chromedriver.exe");
	  driver = new ChromeDriver();
	  baseUrl = "https://www.expedia.co.in/";
	  oneWaySearchPage = new FlightsPageFactoryClass(driver);
	  driver.get(baseUrl);
  }
  @BeforeMethod
  public void beforeMethod() {
	 driver.manage().window().maximize();
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
