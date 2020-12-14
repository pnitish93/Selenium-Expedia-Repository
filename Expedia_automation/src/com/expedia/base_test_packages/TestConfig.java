package com.expedia.base_test_packages;

import org.testng.annotations.Test;

import com.expedia.page_classes.Constants;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

public class TestConfig {
	protected WebDriver driver;
	private String baseUrl;

	@Test
	public void baseTest() {
	}

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "F:\\Selenium\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		baseUrl = Constants.URL;
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
