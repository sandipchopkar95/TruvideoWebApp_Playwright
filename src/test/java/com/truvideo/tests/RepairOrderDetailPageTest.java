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
	public void verifyAddMediaFunction_FirstVideo() {
		repairOrderPage.addVideoToOrder();
	}

	@Test(priority = 3)
	public void verifySendToCustomer_ForFirstVideo() {
		repairOrderPage.sendVideoToCustomer("WhatsApp");
	}

	@Test(priority = 4)
	public void verifyAddMediaFunction_SecondVideo() {
		repairOrderPage.addVideoToOrder();
	}

	@Test(priority = 5)
	public void verifySendToCustomer_ForSecondVideo() {
		repairOrderPage.sendVideoToCustomer("SMS");
	}

	@Test(priority = 6)
	public void verifyViewedStatus() {
		repairOrderPage.checkStatus_OnVideoWatch();
	}

	@Test(priority = 7)
	public void verifyVariousActivityOfEstimate() {
		page.reload();
		repairOrderPage.activitiesOfCreateEstimateWindow();
	}

	@Test(priority = 8)
	public void verifySendEstimateFunction() {
		repairOrderPage.sendEstimate("SMS");
	}

	@Test(priority = 9)
	public void verifyResendEstimateFunction() {
		repairOrderPage.resendEstimate("WhatsApp");
	}

	@Test(priority = 10)
	public void verifyEstimateConfirmationFunction() {
		repairOrderPage.estimateConfirmation("WhatsApp");
	}

	@Test(priority = 11)
	public void verifyPaymentFunction() {
		page.reload();
		repairOrderPage.createPayment("WhatsApp");
	}

	@Test(priority = 12)
	public void verifyPaymentResendFunction() {
		repairOrderPage.resendPayment("SMS");
	}

	@Test(priority = 13)
	public void verifySubmitPayment_ProcessedPayment() {
		repairOrderPage.submitPayment();
	}
	
	

}
