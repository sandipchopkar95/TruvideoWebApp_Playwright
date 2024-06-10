package com.truvideo.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.truvideo.base.BaseTest;
import com.truvideo.pages.RepairOrderDetailPage;

public class RepairOrderDetailPageTest extends BaseTest {
	RepairOrderDetailPage repairOrderPage;

	@BeforeClass
	public void repairOrderDetailPageSetup() {
		repairOrderPage = loginpage.navigateToHomePage(prop.getProperty("username"), prop.getProperty("password"))
				.navigateToOrderList().navigateToOrderDetails();
	}

	@Test(priority = 1)
	public void verifyAllAvailableElementsOnOrderDetails() {
		Assert.assertTrue(repairOrderPage.checkAllMandatoryFields_ForNewRO());
	}
	
	@Test(priority = 2)
	public void verifySendToCustomerFunction() {
		repairOrderPage.sendVideoToCustomer();
	}
}
