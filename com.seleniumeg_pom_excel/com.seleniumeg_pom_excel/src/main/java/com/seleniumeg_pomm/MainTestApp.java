package com.seleniumeg_pomm;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MainTestApp {
	public static void main(String[] args) throws Exception {

		// Set the path for the ChromeDriver
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Administrator\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");

		// Create a new instance of the Chrome driver
		WebDriver driver = new ChromeDriver();

		// Open the HTML page (replace with your actual URL)
		driver.get(
				"file:///C:\\Users\\Administrator\\eclipse-workspace\\com.seleniumeg_pomm\\src\\main\\resources\\Home.html");

		// create homepage instance
		HomePage homePage = new HomePage(driver);

		// on homepage click aboutlink
		AboutPage aboutPage = homePage.gotoAboutPage();

		Thread.sleep(3000);

		ContactPage contactPage = homePage.gotoContactPage();

		Thread.sleep(3000);

		aboutPage = contactPage.gotoAboutPage();
		
		aboutPage.showMoreInfo();
		
		Thread.sleep(2000);
		
		aboutPage.validateShowMore();
		
		// from above aboutlink goback to homepage
		aboutPage.gotoHomePage();
		Thread.sleep(3000);
		contactPage = aboutPage.gotoContactPage();
		Thread.sleep(3000);

		contactPage.fillContactForm("abcd", "dadsafs@dsfsdf.com", "ksfjhsdkfjsdhkf");
		Thread.sleep(2000);
		contactPage.checkSubmission();
		Thread.sleep(1000);

		
		
		driver.quit();
	}
}
