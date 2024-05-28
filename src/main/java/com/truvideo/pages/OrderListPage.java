package com.truvideo.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.truvideo.factory.AppiumFactory;
import com.truvideo.mobilepages.DealerCodePage;
import com.truvideo.mobilepages.UserListPage;
import com.truvideo.utility.JavaUtility;
import io.appium.java_client.AppiumDriver;

public class OrderListPage extends JavaUtility{
	private Page page;
	private AppiumDriver driver;

	public OrderListPage(Page page) {
		this.page = page;
	}

	private String addRepairOrder_Button = "#repair-order-add";
	private String repairOrderNumber_Field = "(//input[@id='jobServiceNumber'])[1]";
	private String firstName_Field = "(//input[@id='customer.firstName'])[1]";
	private String lastName_Field = "(//input[@id='customer.lastName'])[1]";
	private String phoneNumber_Field = "//input[@id='phoneNumberCreate']";
	private String emailId_Field = "(//input[@id='customer.email'])[1]";
	private String save_Button = "#add-repair-order-save";

	private String repairOrder_Heading = "//span[text()='Repair Order']";

	// table
	private String tableRow = "table#repair-order-results tr";

	public static String roNumber;

	public String addRepairOrder() {
		page.click(addRepairOrder_Button);
		roNumber = "WEB" + getRandomString(5);
		page.fill(repairOrderNumber_Field, roNumber);
		page.fill(firstName_Field, "First" + getRandomString(8));
		page.fill(lastName_Field, "Last" + getRandomString(8));
		page.click(phoneNumber_Field);
		page.fill(phoneNumber_Field, "781205" + getRandomNumber(4));
		page.click(emailId_Field);
		page.fill(emailId_Field,getRandomString(8) + "@gmail.com");
		page.click(save_Button);
		// Locator row = page.locator(tableRow);
		// row.locator(":scope", new
		// Locator.LocatorOptions().setHasText(roNumber)).click();
		// page.waitForTimeout(10000);
		// return page.title();
		return roNumber;
	}

	public String openCreatedRO() {
		Locator row = page.locator(tableRow);
		row.locator(":scope", new Locator.LocatorOptions().setHasText(roNumber)).click();
		page.waitForTimeout(30000);
		return page.title();
	}

	public String verifyCreatedRO_OnMobile() throws Exception {
		driver = AppiumFactory.launchApp();
		DealerCodePage dp = new DealerCodePage(driver);
		dp.dealerLogin_ValidCredentials();
		UserListPage up = new UserListPage(driver);
		return up.login_verify_created_RO(prop.getProperty("MobileUserLogin"));
	}
}
