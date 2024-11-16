package com.seleniumeg_pomm;

import org.openqa.selenium.*;
//11:17
public class AboutPage {
	private WebDriver driver;
	// create Locators
	private By homeLink = By.id("homeLink");
	private By contactLink = By.id("contactLink");
	private By showmoreButton = By.id("moreInfoButton");
	private By moreInfoDiv = By.id("moreInfo");
	
	//constructor
	public AboutPage(WebDriver driver) {
		System.out.println("Displaying About Page..."+driver.getTitle());
		this.driver = driver;
	}
	
	public void showMoreInfo() {
		driver.findElement(showmoreButton).click();
	}
	
	public void validateShowMore() {
		String moreinfo = driver.findElement(moreInfoDiv).getText();
		System.out.println("moreinfo:"+moreinfo);
	}
	
	public HomePage gotoHomePage() {
		WebElement homeLinkElement = driver.findElement(homeLink);
		homeLinkElement.click();
		return new HomePage(driver);
	}
	
	public ContactPage gotoContactPage() {
		WebElement contactLinkElement = driver.findElement(contactLink);
		contactLinkElement.click();
		return new ContactPage(driver);
	}
}
