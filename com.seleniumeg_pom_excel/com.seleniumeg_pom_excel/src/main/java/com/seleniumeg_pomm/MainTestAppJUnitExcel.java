package com.seleniumeg_pomm;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.excel.utils.ExcelReadUtils;
import com.excel.utils.ExcelWriteUtils;

public class MainTestAppJUnitExcel {
	private static WebDriver driver;
	private static WebDriverWait wait;

	@BeforeAll
	public static void setUp() {
		// set driver property
		// Set the path for the ChromeDriver
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Administrator\\Downloads\\chromedriver-win64\\chromedriver.exe");

		// Create a new instance of the Chrome driver
		driver = new ChromeDriver();

		// wait
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		System.out.println("setUp....1");
		ExcelReadUtils.init();
		ExcelWriteUtils.init();
	}

	// test cases
	// Home page to About Page
	@Disabled
	public void testNavigateHomeAbout() {
		// Open the HTML page (replace with your actual URL)
		driver.get(
				"file:///C:\\Users\\Administrator\\Downloads\\com.seleniumeg_pom_excel\\com.seleniumeg_pom_excel\\src\\main\\resources\\Home.html");

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
	@Disabled
	public void testNavigateHomeContact() {
		// Open the HTML page (replace with your actual URL)
		driver.get(
				"file:///C:\\Users\\Administrator\\Downloads\\com.seleniumeg_pom_excel\\com.seleniumeg_pom_excel\\src\\main\\resources\\Home.html");

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
	@Disabled
	public void testNavigateHomeAboutContact() {
		// Open the HTML page (replace with your actual URL)
		driver.get(
				"file:///C:\\Users\\Administrator\\Downloads\\com.seleniumeg_pom_excel\\com.seleniumeg_pom_excel\\src\\main\\resources\\Home.html");

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

	static Stream<Arguments> getContactFormData() {
		System.out.println("getContactFormData....1");
		// invoke ExcelReadUtils method
		Stream<Arguments> contactdata = ExcelReadUtils.readContactFormData();
		return contactdata;
	}

	// @Test
	// @MethodSource("getContactFormData")
	@ParameterizedTest
	@MethodSource("getContactFormData")
	void checkContactForm(String testcaseid, String name, String email, String details, String successMsg) {
		// void checkContactForm() {
		System.out.println("checkContactForm....1:");
		try {
			// Open the HTML page (replace with your actual URL)
			driver.get(
					"file:///C:\\Users\\Administrator\\Downloads\\com.seleniumeg_pom_excel\\com.seleniumeg_pom_excel\\src\\main\\resources\\Home.html");

			// create homepage instance
			HomePage homePage = new HomePage(driver);

			// on homepage click aboutlink
			ContactPage contactPage = homePage.gotoContactPage();

			// wait until About page is loaded
			wait.until(ExpectedConditions.titleContains("Contact"));

			contactPage.fillContactForm("name", "email", "details");
			Thread.sleep(2000);
			String message = contactPage.checkSubmission();
			assertTrue(message.contains(successMsg));
			ExcelWriteUtils.writeTCResult(testcaseid, "Pass", details,successMsg);
		} catch (AssertionError ae) {
			ExcelWriteUtils.writeTCResult(testcaseid, "Fail",details,successMsg );
			ae.printStackTrace();
			throw ae;
		}
		

		catch (Exception e) {
			ExcelWriteUtils.writeTCResult(testcaseid, "ERROR", "", successMsg);
			System.out.println("Exception ...." + e.getMessage());
			e.printStackTrace();
		}
	}

	@AfterAll
	public static void tearDown() {
		ExcelWriteUtils.generateExcel();
		System.out.println("tearDown");
		if (driver != null) {
			driver.quit();
		}
	}
}
