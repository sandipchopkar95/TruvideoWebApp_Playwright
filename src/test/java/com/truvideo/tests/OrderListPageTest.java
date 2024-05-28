package com.truvideo.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import com.truvideo.base.BaseTest;
import com.truvideo.pages.OrderListPage;
import com.truvideo.constants.AppConstants;

public class OrderListPageTest extends BaseTest {
	OrderListPage orderlistpage;

	@BeforeClass
	public void orderListPageSetup() {
		orderlistpage = loginpage.navigateToHomePage(prop.getProperty("username"), prop.getProperty("password"))
				.navigateToOrderList();
	}

	@Test(priority = 1)
	public void verifyAddRepairOrder() throws Exception {
		String createdRo_Dashboard = orderlistpage.addRepairOrder();
		String validatedRO_Mobile = orderlistpage.verifyCreatedRO_OnMobile();
		Assert.assertTrue(validatedRO_Mobile.contains(createdRo_Dashboard));
	}

	@Test(priority = 2)
	public void openCreatedRO() {
		String actualTitle = orderlistpage.openCreatedRO();
		Assert.assertEquals(actualTitle, AppConstants.ORDER_DETAILS_PAGE_TITLE);
	}

}
