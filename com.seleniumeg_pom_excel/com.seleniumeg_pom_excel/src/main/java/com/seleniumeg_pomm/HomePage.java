package com.seleniumeg_pomm;

import org.openqa.selenium.*;

public class HomePage {

	private WebDriver driver;

	// create Locators
	private By aboutLink = By.id("aboutLink");
	private By contactLink = By.id("contactLink");

	// constructor expects WebDriver as parameter
	public HomePage(WebDriver driver) {
		System.out.println("Displaying Home Page..." + driver.getTitle());
		this.driver = driver;
	}

	// methods to perform actions on above WebElements
	public AboutPage gotoAboutPage() {
		WebElement aboutLinkElement = driver.findElement(aboutLink);
		aboutLinkElement.click();
		return new AboutPage(driver);
	}

	public ContactPage gotoContactPage() {
		WebElement contactLinkElement = driver.findElement(contactLink);
		contactLinkElement.click();
		return new ContactPage(driver);
	}

	// more action methods:TBD
}

/*
 * private By contactLink = By.id("contactLink");
 * 
 * // subscribeform private By subscribeForm = By.id("subscribeForm");
 * 
 * // email field private By email = By.id("email");
 * 
 * // subscribe button private By subscribeButton = By.id("subscribeButton");
 * 
 * // accept terms private By acceptTerms = By.id("subscribeButton");
 */
