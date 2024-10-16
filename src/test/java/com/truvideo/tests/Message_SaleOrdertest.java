package com.truvideo.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.truvideo.base.BaseTest;
import com.truvideo.pages.MessageScreen_Prospect;

public class Message_SaleOrdertest extends BaseTest {

	MessageScreen_Prospect MessageScreen_prospect;

	@BeforeClass
	public void Message_salesorderlogin() {

		MessageScreen_prospect = loginpage
				.navigateToHomePage(prop.getProperty("username"), prop.getProperty("password"))
				.navigateToMessageScreen_Prospect();

	}

	@Test(priority = 1)
	public void VerifyAll_Elements() {
		Assert.assertTrue(MessageScreen_prospect.VerifyAll_Elements());
	}

	@Test(priority = 2)
	public void Verify_Profile_setting_button() {
		Assert.assertTrue(MessageScreen_prospect.Verify_Profile_setting_button("Suraj singh"));
	}

	@Test(priority = 3)
	public void Verify_message_Name() {
		Assert.assertTrue(MessageScreen_prospect.Verify_message_Name());
	}

	@Test(priority = 4)
	public void verify_Default_Filters() throws Exception {
		Assert.assertTrue(MessageScreen_prospect.verify_Default_Filters());

	}

	@Test(priority = 5)
	public void verifyMyFilter() {
		Assert.assertTrue(MessageScreen_prospect.verifyMyFilter());
	}

	@Test(priority = 6)
	public void verifyfilterbuttons() {
		Assert.assertTrue(MessageScreen_prospect.verifyfilterbuttons());
	}

	@Test(priority = 7)
	public void Verify_my_filterIsApplied() {
		Assert.assertTrue(MessageScreen_prospect.Verify_my_filterIsApplied());
	}

	@Test(priority = 8)
	public void verify_Whatsapp_filterBotton() {
		Assert.assertTrue(MessageScreen_prospect.verify_Whatsapp_filterBotton());
	}

	@Test(priority = 9)
	public void Verify_Sms_filterBotton() {
		Assert.assertTrue(MessageScreen_prospect.Verify_Sms_filterBotton());
	}
	@Test(priority = 10)
	public void Verify_channel_ownername() {
		Assert.assertTrue(MessageScreen_prospect.Verify_channel_ownername());
	}
	

}
