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

	@Test
	public void verifyNavigationToOrderList() {
		String actualTitle = homepage.navigationToOrderList();
		Assert.assertEquals(actualTitle, AppConstants.REPAIR_ORDERS_PAGE_TITLE);
	}
}
