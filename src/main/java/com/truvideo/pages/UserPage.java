package com.truvideo.pages;

import java.util.ArrayList;
import java.util.List;

import org.testng.asserts.SoftAssert;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.sun.tools.sjavac.Log;
import com.truvideo.constants.AppConstants;
import com.truvideo.utility.JavaUtility;

public class UserPage extends JavaUtility {
	private Page page;

	public UserPage(Page page) {
		this.page = page;
	}

	private String addUser_button = "button:has-text('Add User')";
	private String selectRoles_option = "#s2id_autogen1";
	// private String selectRoles_option = "div[class='select2-result-label']";

	public String getRoles(String roles) {
		String element = "div[class='select2-result-label']:has-text('" + roles + "')";
		return element;
	}

	private String selectDealer_option = "#s2id_autogen2";

	public String getDealer(String dealer) {
		String element = "div[class='select2-result-label']:has-text('" + dealer + "')";
		return element;
	}

	private String firstName = "#firstName";
	private String lastName = "#lastName";
	private String title = "#title";
	private String emailAddress = "#emailAddress";
	private String phoneNumber = "#mobileNumber";
	private String mobileNumber = "#notificationMobileNumber";
	private String saveButton = "#page-title-save";
	private String saveandNew = "#page-title-save-and-new";
	private String saveandInvite = "#page-title-save";
	private String userSearchbox = "input[name='keyword']";
	private String searchButton = "button:has-text('Search')";
	private String activeFilter = "#ACTIVE_FILTER";
	private String pendingFilter = "#PENDING_FILTER";
	private String inactiveFilter = "#INACTIVE_FILTER";
	private String emptyRoleAlert = "small:has-text('At least one Group is required.')";
	private String emptyDealerAlert = "small:has-text('At least one Dealer is required.')";
	private String emptyFirstNameAlert = "small:has-text('First Name is a required field.')";
	private String emptyLastNameAlert = "small:has-text('Last Name is a required field.')";
	private String emptyEmailAddressAlert = "small:has-text('Email Address is a required field.')";
	private String alredyExistsEmail = "span small";
	private String topRightCornerNotification = "div.notifications";

	public String getsearchedUser(String username) {
		String username1 = "#user-results tbody tr td:nth-child(3) p:text('" + username + "')";
		return username1;
	}

	public void addUser(String roles, String dealer) throws InterruptedException {
		page.click(addUser_button);
		logger.info("Clicked on Add User Button");
		page.click(saveButton);
		logger.info("Clicked on Save button when no Roles or Dealers are selected");
		SoftAssert softAssert = new SoftAssert();
		List<Boolean> flags = new ArrayList<Boolean>();
		if (page.textContent(emptyRoleAlert).contains("At least one Group is required.")) {
			logger.info("Alert message for Role Displayed");
			flags.add(true);
		} else {
			logger.info("Alert message for Role Not Displayed");
			flags.add(false);
		}
		if (page.textContent(emptyDealerAlert).contains("At least one Dealer is required.")) {
			logger.info("Alert message for Dealer Displayed");
			flags.add(true);
		} else {
			logger.info("Alert message for Dealer Not Displayed");
			flags.add(false);
		}
		softAssert.assertTrue(!flags.contains(false), "Error Alert message when Role & Dealer is not entered");
		flags.clear();
		Thread.sleep(3000);
		page.click(selectRoles_option);
		page.fill(selectRoles_option, roles);
		page.click(getRoles(roles));

		Thread.sleep(5000);

		page.click(selectDealer_option);
		page.fill(selectDealer_option, dealer);
		page.click(getDealer(dealer));
		Thread.sleep(5000);

		if (page.textContent(emptyFirstNameAlert).contains("First Name is a required field..")) {
			logger.info("Alert message for Role Displayed");
			flags.add(true);
		} else {
			logger.info("Alert message for First Name is Not Displayed");
			flags.add(false);
		}
		if (page.textContent(emptyLastNameAlert).contains("Last Name is a required field.")) {
			logger.info("Alert message for Dealer Displayed");
			flags.add(true);
		} else {
			logger.info("Alert message for Last Name Not is Displayed");
			flags.add(false);
		}
		if (page.textContent(emptyEmailAddressAlert).contains("Email Address is a required field.")) {

			logger.info("Alert message for Dealer Displayed");
			flags.add(true);
		} else {
			logger.info("Alert message for Email Address Not is Displayed");
			flags.add(false);
		}
		softAssert.assertTrue(!flags.contains(false),
				"Error Alert message when Role,Dealer,First Name,Last Name and Email is not entered");

		Thread.sleep(2000);
		page.click(firstName);
		page.fill(firstName, "Test Automation");
		logger.info("First name is Added");
		page.click(lastName);
		page.fill(lastName, "User");
		logger.info("Last name is Added");
		page.click(emailAddress);
		page.fill(emailAddress, "testautomation@gmail.com");
		logger.info("Email address is Added");
		page.click(saveButton);
		logger.info("Clicked on Save button");
		Thread.sleep(10000);

		softAssert.assertTrue(page.textContent(alredyExistsEmail).contains("is already registered."),
				"Verify Existing users email error message");
		if (page.isVisible(alredyExistsEmail)) {
			page.fill(emailAddress, getRandomString(5) + "@gmail.com");
		}
		logger.info(page.inputValue(emailAddress));
		page.click(saveButton);
		Thread.sleep(10000);

		String topRightCornerNotificationPopup = page.textContent(topRightCornerNotification);
		logger.info(topRightCornerNotificationPopup);
		if (topRightCornerNotificationPopup.contains(AppConstants.USER_SAVED_MESSAGE)) {
			logger.info("New User saved Successfully");
		} else {
			logger.info("Getting error to add New User ");
		}

	}

	public void searchUser(String usernametoSearch) {
		// Fill the search box with the username to search
		page.fill(userSearchbox, usernametoSearch);
		logger.info("Entered user name to search");

		// Click the search button
		page.click(searchButton);
		logger.info("Clicked on search button - Associated users are displaying");

		// Locate the rows in the search results
		Locator rows = page.locator(
				"#user-results tbody tr[data-id*='1'], #user-results tbody tr[data-id*='2'], #user-results tbody tr[data-id*='3']");
		int rowCount = rows.count();

		// Iterate through each row
		for (int i = 0; i < rowCount; i++) {
			Locator row = rows.nth(i);

			// Check if the row's text content contains the username to search
			if (row.textContent().contains(usernametoSearch)) {
				String firstName = row.locator("td:nth-child(3)").textContent();
				String lastName = row.locator("td:nth-child(4)").textContent();
				logger.info("Firstname is: " + firstName);
				logger.info("Lastname is: " + lastName);
			}
		}
	}

	public void searchUser1(String usernametoSearch) {
		page.fill(userSearchbox, usernametoSearch);
		logger.info("Entered user name to search");
		page.click(searchButton);
		logger.info("Clicked on search button - Associated users are displaying");
		Locator rows = page.locator(
				"#user-results tbody tr[data-id*='1'], #user-results tbody tr[data-id*='2'],#user-results tbody tr[data-id*='3']");
		page.locator("test").textContent().equals(usernametoSearch);
		{
			page.locator("test").click();
		}
		int rowCount = rows.count();
		for (int i = 0; i < rowCount; i++) {
			Locator row = rows.nth(i);
			if (row.textContent().contains(usernametoSearch)) {
				for (int j = 0; j < i; j++) {
					String firstName = row.locator("td:nth-child(3)").textContent();
					String lastname = row.locator("td:nth-child(4)").textContent();
					logger.info("Firstname is : " + firstName);
				}
			}
		}
	}

}
