package com.truvideo.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;
import com.truvideo.base.BaseTest;
import com.truvideo.constants.AppConstants;
import com.truvideo.pages.HomePage;

public class HomePageTest extends BaseTest {
	HomePage homepage;

	@BeforeClass
	public void homePageSetup() {
		homepage = loginpage.navigateToHomePage(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 1)
	public void verify_RepairOrder_HeaderTab_Working() {
		String actualTitle = homepage.clickOn_RepairOrder_Header();
		Assert.assertEquals(actualTitle, AppConstants.REPAIR_ORDERS_PAGE_TITLE);
		page.goBack();
	}

	@Test(priority = 2)
	public void verify_OrdersMessage_HeaderTab_Working() {
		Assert.assertTrue(homepage.clickOn_Order_MessagesHeader());
		page.goBack();
	}

	@Test(priority = 3)
	public void verify_Prospect_HeaderTab_Working() {
		String actualTitle = homepage.clickOn_Prospect_Header();
		Assert.assertEquals(actualTitle, AppConstants.PROSPECT_PAGE_TITLE);
		page.goBack();
	}

	@Test(priority = 4)
	public void verify_ProspectsMessage_HeaderTab_Working() {
		Assert.assertTrue(homepage.clickOn_Prospect_MessagesHeader());
		page.goBack();
	}
	
	@Test(priority = 5)
	public void verify_Reminder_HeaderTab_Working() {
		String actualTitle = homepage.clickOn_Reminder_Header();
		Assert.assertEquals(actualTitle, AppConstants.REMINDER_PAGE_URL);
		page.goBack();
	}
	
	@Test(priority = 6)
	public void verify_Training_HeaderTab_Working() {
		String actualTitle = homepage.clickOn_Training_Header();
		Assert.assertEquals(actualTitle, AppConstants.TRAINING_PAGE_TITLE);
		page.goBack();
	}
	
	@Test(priority = 6)
	public void verify_User_HeaderTab_Working() {
		String actualTitle = homepage.clickOn_User_Header();
		Assert.assertEquals(actualTitle, AppConstants.USER_PAGE_TITLE);
		page.goBack();
	}
	
	@Test(priority = 7)
	public void verify_Contact_HeaderTab_Working() {
		String actualTitle = homepage.clickOn_ContactList_Header();
		Assert.assertEquals(actualTitle, AppConstants.CONTACT_LIST_PAGE_TITLE);
		page.goBack();
	}
	
	
}
