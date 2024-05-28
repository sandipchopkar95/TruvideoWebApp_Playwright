package com.truvideo.pages;

import com.microsoft.playwright.Page;
import com.truvideo.constants.AppConstants;
import com.truvideo.utility.JavaUtility;

public class HomePage extends JavaUtility {
	private Page page;

	public HomePage(Page page) {
		this.page = page;
	}

	private String repairOrder_Header = "a[href='/crud/repair-order']";
	private String users_Header = "a[href='/organization/tce-users/']";
	private String orderMessage_Header = "#service-messages-link";
	private String prospect_Header = "a[href='/crud/sales']";
	private String prospectMessage_Header = "#sales-messages-link";
	private String reminder_Header = "a[href='/reminder?filterBy=MY_REMINDERS']";
	private String trainings_Header = "a[href='/trainings']";
	private String reports_Header = "a[href='#']:has-text('Reports')";
	private String organization_Header = "a[href='#']:has-text('Organization')";
	private String system_Header = "a[href='#']:has-text('System')";
	private String contactList_Header = "a[href='/contact-list']:has-text('Contact List')";
	private String other_Header = "a[href='#']:has-text('Other')";

	public String clickOn_RepairOrder_Header() {
		navigateToOrderList();
		logger.info("Clicked on Repair Order Header Tab");
		return page.title();
	}

	public OrderListPage navigateToOrderList() {
		page.click(repairOrder_Header);
		return new OrderListPage(page);
	}
	
	public UserPage navigateToUserspage() {
		page.click(users_Header);
		return new UserPage(page);
	}

	public boolean clickOn_Order_MessagesHeader() {
		navigateToMessageScreen_Order();
		logger.info("Clicked on Orders Message Screen Header Tab");
		if (page.title().equals(AppConstants.MESSAGES_PAGE_TITLE)
				&& page.url().equals(AppConstants.ORDER_MESSAGES_PAGE_URL)) {
			logger.info("Title matched & Title is : " + page.title());
			logger.info("URL matched & opened url is : " + page.url());
			return true;
		} else {
			logger.info("Title not matched & Title is : " + page.title());
			logger.info("URL not matched & opened url is : " + page.url());
			return false;
		}
	}

	public MessageScreen_Order navigateToMessageScreen_Order() {
		page.click(orderMessage_Header);
		return new MessageScreen_Order(page);
	}

	public String clickOn_Prospect_Header() {
		navigateToProspectList();
		logger.info("Clicked on Prospect Header Tab");
		return page.title();
	}

	public ProspectListPage navigateToProspectList() {
		page.click(prospect_Header);
		return new ProspectListPage(page);
	}

	public boolean clickOn_Prospect_MessagesHeader() {
		navigateToMessageScreen_Prospect();
		logger.info("Clicked on Prospects Message Header Tab");
		if (page.title().equals(AppConstants.MESSAGES_PAGE_TITLE)
				&& page.url().equals(AppConstants.PROSPECT_MESSAGES_PAGE_URL)) {
			logger.info("Title matched & Title is : " + page.title());
			logger.info("URL matched & opened url is : " + page.url());
			return true;
		} else {
			logger.info("Title not matched & Title is : " + page.title());
			logger.info("URL not matched & opened url is : " + page.url());
			return false;
		}
	}

	public MessageScreen_Prospect navigateToMessageScreen_Prospect() {
		page.click(prospectMessage_Header);
		return new MessageScreen_Prospect(page);
	}

	public String clickOn_Reminder_Header() {
		navigateToReminder();
		logger.info("Clicked on Reminder Header Tab");
		return page.url();
	}

	public ReminderPage navigateToReminder() {
		if (!page.isVisible(reminder_Header)) {
			page.click(other_Header);
		}
		page.click(reminder_Header);
		return new ReminderPage(page);
	}

	public String clickOn_Training_Header() {
		navigateToTraining();
		logger.info("Clicked on Training Header Tab");
		return page.title();
	}

	public TrainingPage navigateToTraining() {
		if (!page.isVisible(trainings_Header)) {
			page.click(other_Header);
		}
		page.click(trainings_Header);
		return new TrainingPage(page);
	}

	public String clickOn_ContactList_Header() {
		navigateToContactList();
		logger.info("Clicked on Contacts List Header Tab");
		return page.title();
	}

	public ContactList navigateToContactList() { 
		if (!page.isVisible(contactList_Header)) {
			page.click(other_Header);
		}
		page.click(contactList_Header);
		return new ContactList(page);
	}

}
