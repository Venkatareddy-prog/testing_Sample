package com.seleniumeg_pomm;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.jupiter.api.Assertions.*;

public class MainTestAppJUnit {
	private static WebDriver driver;
	private static WebDriverWait wait;

	@BeforeAll
	public static void setUp() {
		// set driver property
		// Set the path for the ChromeDriver
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Administrator\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");

		// Create a new instance of the Chrome driver
		driver = new ChromeDriver();

		// wait
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// test cases
	// Home page to About Page
	@Test
	public void testNavigateHomeAbout() {
		// Open the HTML page (replace with your actual URL)
		driver.get(
				"file:///C:\\Users\\Administrator\\eclipse-workspace\\com.seleniumeg_pomm\\src\\main\\resources\\Home.html");

		// create homepage instance
		HomePage homePage = new HomePage(driver);

		// on homepage click aboutlink
		AboutPage aboutPage = homePage.gotoAboutPage();

		// wait until About page is loaded
		wait.until(ExpectedConditions.titleContains("About"));

		// assert with page title
		assertTrue(driver.getTitle().contains("About"));
	}

	// Home Page to Contact Page
	@Test
	public void testNavigateHomeContact() {
		// Open the HTML page (replace with your actual URL)
		driver.get(
				"file:///C:\\Users\\Administrator\\eclipse-workspace\\com.seleniumeg_pomm\\src\\main\\resources\\Home.html");

		// create homepage instance
		HomePage homePage = new HomePage(driver);

		// on homepage click aboutlink
		ContactPage contactPage = homePage.gotoContactPage();

		// wait until About page is loaded
		wait.until(ExpectedConditions.titleContains("Contact"));

		// assert with page title
		assertTrue(driver.getTitle().contains("Contact"));
	}

	// Test Home to About, further to Contact page
	@Test
	public void testNavigateHomeAboutContact() {
		// Open the HTML page (replace with your actual URL)
		driver.get(
				"file:///C:\\Users\\Administrator\\eclipse-workspace\\com.seleniumeg_pomm\\src\\main\\resources\\Home.html");

		// create homepage instance
		HomePage homePage = new HomePage(driver);

		// on homepage click aboutlink
		AboutPage aboutPage = homePage.gotoAboutPage();

		// wait until About page is loaded
		wait.until(ExpectedConditions.titleContains("About"));

		// navigate from About to Contact
		ContactPage contactPage = aboutPage.gotoContactPage();

		wait.until(ExpectedConditions.titleContains("Contact"));

		// assert with page title
		assertTrue(driver.getTitle().contains("Contact"));
	}

	@Test
	public void checkContactForm() {
		try {
			// Open the HTML page (replace with your actual URL)
			driver.get(
					"file:///C:\\Users\\Administrator\\eclipse-workspace\\com.seleniumeg_pomm\\src\\main\\resources\\Home.html");

			// create homepage instance
			HomePage homePage = new HomePage(driver);

			// on homepage click aboutlink
			ContactPage contactPage = homePage.gotoContactPage();

			// wait until About page is loaded
			wait.until(ExpectedConditions.titleContains("Contact"));

			contactPage.fillContactForm("abcd", "dadsafs@dsfsdf.com", "ksfjhsdkfjsdhkf");
			Thread.sleep(2000);
			String message = contactPage.checkSubmission();
			assertTrue(message.contains("Mail Sent Successfully"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterAll
	public static void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
