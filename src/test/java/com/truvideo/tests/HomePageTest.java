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

	@Test(priority = 7)
	public void verify_User_HeaderTab_Working() {
		String actualTitle = homepage.clickOn_User_Header();
		Assert.assertEquals(actualTitle, AppConstants.USER_PAGE_TITLE);
		page.goBack();
	}

	@Test(priority = 8)
	public void verify_Contact_HeaderTab_Working() {
		String actualTitle = homepage.clickOn_ContactList_Header();
		Assert.assertEquals(actualTitle, AppConstants.CONTACT_LIST_PAGE_TITLE);
		page.goBack();
	}

	@Test(priority = 9)
	public void verifyDealerTab_UnderOrgainizationHeader_Working() {
		String actualTitle = homepage.clickOnDealersHeaderTab();
		Assert.assertEquals(actualTitle, AppConstants.DEALERS_PAGE_TITLE);
		page.goBack();
	}

	@Test(priority = 10)
	public void verifyDealerGroupTab_UnderOrgainizationHeader_Working() {
		String actualTitle = homepage.clickOnDealerGroupHeaderTab();
		Assert.assertEquals(actualTitle, AppConstants.DEALER_GROUP_PAGE_TITLE);
		page.goBack();
	}

	@Test(priority = 11)
	public void verifyUserGroupTab_UnderOrgainizationHeader_Working() {
		String actualTitle = homepage.clickOnUserGroupHeaderTab();
		Assert.assertEquals(actualTitle, AppConstants.USER_GROUP_PAGE_TITLE);
		page.goBack();
	}

	@Test(priority = 12)
	public void verifySavedVideoLibraryTab_UnderOrgainizationHeader_Working() {
		String actualTitle = homepage.clickOnSAvedVideoLibraryHeaderTab();
		Assert.assertEquals(actualTitle, AppConstants.SAVED_VIDEO_PAGE_TITLE);
		page.goBack();
	}

	@Test(priority = 13)
	public void verifyDevicesTab_UnderSystemHeader_Working() {
		String actualTitle = homepage.clickOnDevicesHeaderTab();
		Assert.assertEquals(actualTitle, AppConstants.DEVICES_PAGE_TITLE);
		page.goBack();
	}

	@Test(priority = 14)
	public void verify_Open_Close_OnAdvanceSearchWindow() {
		Assert.assertTrue(homepage.openAndCloseAdvanceSearchWindow());
	}

	@Test(priority = 15)
	public void verify_AllAvailableCheckBox_Filters_OnAdvanceSearchWindow() {
		homepage.checkVariousCheckBoxFilters();
	}

	@Test(priority = 16)
	public void verify_ThisWeek_RadioFilter_OnAdvanceSearchWindow() {
		Assert.assertTrue(homepage.checkThisWeeksRepairOrders());
	}

	@Test(priority = 17)
	public void verify_ThisMonth_RadioFilter_OnAdvanceSearchWindow() {
		Assert.assertTrue(homepage.checkThisMonthRepairOrders());
	}

	@Test(priority = 18)
	public void verify_DateRangeFilter_OnAdvanceSearchWindow() {
		Assert.assertTrue(homepage.checkRepairOrdersWithinDateRange());
	}

	@Test(priority = 19)
	public void verify_SearchFilter_OnAdvanceSearchWindow() {
		Assert.assertTrue(homepage.listAsPerTheTextSearch());
	}

	@Test(priority = 20)
	public void verify_Notification_BellIconWorking() {
		Assert.assertTrue(homepage.checkBellIcon());
	}

	@Test(priority = 21)
	public void verify_DealerCodeButtonIsWorking() {
		Assert.assertTrue(homepage.checkDealerCodeButton());
	}

	@Test(priority = 22)
	public void verify_Chat_ButtonIsWorking() {
		String actualTitle = homepage.clickOnChatButton();
		Assert.assertEquals(actualTitle, AppConstants.CHAT_PAGE_TITLE);
		page.goBack();
	}

	@Test(priority = 23)
	public void verify_BackAway_ButtonIsWorking() {
		Assert.assertTrue(homepage.clickOn_Back_Away_Button());
	}

	@Test(priority = 24)
	public void verify_UserAccountDropdown_And_Options() {
		Assert.assertTrue(homepage.openUserAccountDropdown());
	}

	@Test(priority = 25)
	public void verify_AccountSetting_TextButtonIsClickable() {
		String actualTitle = homepage.clickOnAccountSettingTextButton();
		Assert.assertEquals(actualTitle, AppConstants.ACCOUNT_SETTING_PAGE_TITLE);
		page.goBack();
		page.reload();
	}

	@Test(priority = 26)
	public void verify_SwitchDealerFunction() {
		Assert.assertTrue(homepage.switchDealer());
	}

	@Test(priority = 27)
	public void verifyHelpPageOpenedInAnotherTab() {
		String actualTitle = homepage.clickOnHelpPage_TextButton();
		Assert.assertEquals(actualTitle, AppConstants.HELP_PAGE_TITLE);
	}

	@Test(priority = 28)
	public void verify_Self_ForReview_RO_Badge() {
		Assert.assertTrue(homepage.clickOn_Own_ForReview_RO_Badge());
	}

	@Test(priority = 29)
	public void verify_All_ForReview_RO_Badge() {
		Assert.assertTrue(homepage.clickOn_All_ForReview_RO_Badge());
	}

	@Test(priority = 30)
	public void verify_Self_ForReview_SO_Badge() {
		Assert.assertTrue(homepage.clickOn_Own_ForReview_SO_Badge());
	}

	@Test(priority = 31)
	public void verify_All_ForReview_SO_Badge() {
		Assert.assertTrue(homepage.clickOn_All_ForReview_SO_Badge());
	}

	@Test(priority = 32)
	public void verify_Self_Messages_RO_Badge() {
		Assert.assertTrue(homepage.clickOn_Own_Messages_RO_Badge());
	}

	@Test(priority = 33)
	public void verify_All_Messages_RO_Badge() {
		Assert.assertTrue(homepage.clickOn_All_Messages_RO_Badge());
	}

	@Test(priority = 34)
	public void verify_Self_Messages_SO_Badge() {
		Assert.assertTrue(homepage.clickOn_Own_Messages_SO_Badge());
	}

	@Test(priority = 35)
	public void verify_All_Messages_SO_Badge() {
		Assert.assertTrue(homepage.clickOn_All_Messages_SO_Badge());
	}

	@Test(priority = 36)
	public void verify_Self_Reminder_Badge() {
		Assert.assertTrue(homepage.clickOn_Own_Reminder_Badge());
	}

	@Test(priority = 37)
	public void verify_All_Reminder_Badge() {
		Assert.assertTrue(homepage.clickOn_All_Reminder_Badge());
	}

}
