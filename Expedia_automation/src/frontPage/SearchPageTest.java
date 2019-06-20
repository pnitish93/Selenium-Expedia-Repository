package frontPage;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

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
  
  @DataProvider(name="searchInputs")
  public Object[][] sendInputs(){
	  Object[][] objectList = new Object[][]{{"Bangalore", "Kolkata", "21/07/2019"},{"Mumbai", "Delhi", "06/07/2019"},{"Hyderabad", "Guwahati", "29/06/2019"}};
	  return objectList;
  }
  
  @Test(dataProvider="searchInputs")
  public void search(String from, String to, String date) throws InterruptedException {
	  oneWaySearchPage.clickFlightsTab();
	  oneWaySearchPage.clickOneWay();
	  oneWaySearchPage.provideOriginCity(from);
	  Thread.sleep(2000);
	  oneWaySearchPage.provideDestCity(to);
	  Thread.sleep(2000);
	  oneWaySearchPage.provideDepartDate(date);
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
