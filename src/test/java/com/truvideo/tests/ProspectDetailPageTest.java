package com.truvideo.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.truvideo.base.BaseTest;
import com.truvideo.pages.ProspectDetailPage;

public class ProspectDetailPageTest extends BaseTest {
	ProspectDetailPage prospectdetailpage;
	@BeforeClass
	public void salesOrderDetailPageSetup()
	{
		prospectdetailpage=loginpage.navigateToHomePage(prop.getProperty("username"), prop.getProperty("password")).navigateToProspectList().navigateToProspectDetails();
	}

	@Test(priority = 1)
	public void test1()
	{
		prospectdetailpage.addVideoToOrder();
	}
	
	//@Test(priority = 3)           //WhatsApp feature is not wokring in Sales side
	public void verifySendToCustomer_ForFirstVideo() {
		prospectdetailpage.sendVideoToCustomer("WhatsApp");
	}

	@Test(priority = 4)
	public void verifyAddMediaFunction_SecondVideo() {
		prospectdetailpage.addVideoToOrder();
	}

	@Test(priority = 5)
	public void verifySendToCustomer_ForSecondVideo() {
		prospectdetailpage.sendVideoToCustomer("SMS");
	}
	@Test(priority = 6)
	public void verifyViewedStatus() {
		prospectdetailpage.checkStatus_OnVideoWatch();
	}

}
