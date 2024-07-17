package com.truvideo.pages;

import java.util.ArrayList;
import java.util.List;

import org.testng.asserts.SoftAssert;

import com.microsoft.playwright.Keyboard;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.sun.tools.sjavac.Log;
import com.truvideo.constants.AppConstants;
import com.truvideo.factory.PlaywrightFactory;
import com.truvideo.utility.JavaUtility;

import net.bytebuddy.asm.Advice.Enter;

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
	String uniqueFirstname;
	private String editUser = ".approved-user-action a.edit-action";
	private String deactivateUserButton = "#deactivate-user-button";
	private String updateUserButton = "#update-password-button";
	private String saveandInviteUserButton = "#save-and-invite-button";
	private String updateUserText = "h4.ot3-update";
	private String backButton = "#temp-page-title-back";
	private String labelpasswordField = "label[for='credential.newPassword']";
	private String labelconfirmPasswordField = "label[for='credential.confirmPassword']";
	private String passwordField = "input[name='credential.newPassword']";
	private String confirmPasswordField = "input[name='credential.confirmPassword']";
	private String invalidPasswordicon = "#validate-password-not-ok";
	private String confirmInvalidPasswordicon = "#validate-confirm-password-ok";
	private String confirmInvalidPasswordtext = "span.help-inline";

	private String password_TextBox = "#newPassword";
	private String confirmNewPassword_TextBox = "#confirmPassword";
	private String submitButton = "#save-user";
	private String updatePasswordPageLabelText = "h4";
	private String updatePasswordRequiredLabel = ".alert-error";
	private String PasswordUpdatedLabelonLoginPage = "#login-status";
	private String userDeactivateStatus = ".deactivate-user";
	private String userActivateStatus = ".activate-user";
	private String activeStatusbutton = "label.btn:has-text('Active')";
	private String inActiveStatusButton = "label.btn:has-text('Inactive')";
	

	String userEmailID;
	String usernewDummyPassword;

	public String getsearchedUser(String username) {
		String username1 = "#user-results tbody tr td:nth-child(3) p:text('" + username + "')";
		return username1;
	}

	public void addNewUser(String roles, String dealer) throws InterruptedException {
		page.click(addUser_button);
		System.out.println("Sooraj101 test");
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
		page.waitForTimeout(3000);
		page.click(selectRoles_option);
		page.fill(selectRoles_option, roles);
		page.click(getRoles(roles));

		page.waitForTimeout(4000);

		page.click(selectDealer_option);
		page.fill(selectDealer_option, dealer);
		page.click(getDealer(dealer));
		page.waitForTimeout(5000);

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

		page.waitForTimeout(4000);
		page.click(firstName);
		page.fill(firstName, getRandomString(5) + "Test Automation");
		logger.info("First name is Added");
		page.click(lastName);
		page.fill(lastName, "User");
		logger.info("Last name is Added");
		page.click(emailAddress);
		page.fill(emailAddress, "testautomation@gmail.com");
		logger.info("Email address is Added");
		page.click(saveButton);
		logger.info("Clicked on Save button");
		page.waitForTimeout(9000);
		uniqueFirstname = page.inputValue(firstName);
		logger.info("Updated First Name " + uniqueFirstname);

		softAssert.assertTrue(page.textContent(alredyExistsEmail).contains("is already registered."),
				"Verify Existing users email error message");
		if (page.isVisible(alredyExistsEmail)) {
			page.fill(emailAddress, getRandomString(5) + "@gmail.com");
		}
		logger.info(page.inputValue(emailAddress));
		userEmailID = page.inputValue(emailAddress);
		page.click(saveButton);
		page.waitForTimeout(9000);

		String topRightCornerNotificationPopup = page.textContent(topRightCornerNotification);
		logger.info(topRightCornerNotificationPopup);
		if (topRightCornerNotificationPopup.contains(AppConstants.USER_SAVED_MESSAGE)) {
			logger.info("New User saved Successfully");
		} else {
			logger.info("Getting error to add New User ");
		}
//return uniqueFirstname;
	}

	public void updateUserPassword(String roles, String dealer, String password) throws InterruptedException {
		addNewUser(roles, dealer);
		page.fill(userSearchbox, uniqueFirstname);
		page.waitForTimeout(9000);
		logger.info("Searched with Latest user created");
		page.click(searchButton);
		page.waitForTimeout(9000);
		logger.info("Clicked on search button - Associated users are displaying");
		page.click(editUser);
		if (page.isVisible(updateUserText)) {
			logger.info("User is on Update userpage");
		} else {
			logger.info("Update userpage is not opened correctly");
		}

		page.waitForTimeout(6000);
		SoftAssert softAssert = new SoftAssert();
		if (firstName.contains(uniqueFirstname)) {
			logger.info("Successfully open newly created user for Editing/updating password");
		} else {
			logger.info("Mismatch between selected user and opened  user");
		}
		softAssert.assertTrue(firstName.contains(uniqueFirstname), "Successfully edited searched user");

		page.click(backButton);
		logger.info("Successfully navigated on userpage through back button");
		page.click(editUser);
		logger.info("Again Successfully edited searched user");
		page.isVisible(deactivateUserButton);
		logger.info("Deactivate User button is displayed");
		page.click(updateUserButton);
		logger.info("Clicked on Update User button");
		page.waitForTimeout(4000);
		page.click(passwordField);
		page.fill(passwordField, password);
		page.click(confirmPasswordField);
		page.fill(confirmPasswordField, "text22");
		if (passwordField.equals(confirmPasswordField)) {
			logger.info("Confirm password is matched with password field");
		} else {
			logger.info("Confirm password is NOT matched with password field");
		}
		softAssert.assertTrue(passwordField.equals(confirmPasswordField),
				"Password and Confirmed password both values are same");

		page.fill(confirmPasswordField, "");
		page.waitForTimeout(3000);
		page.fill(confirmPasswordField, password);
		usernewDummyPassword = page.inputValue(confirmPasswordField);
		logger.info(usernewDummyPassword + "Confirm password is matched with password field");
		logger.info("Confirm password is matched with password field");
		page.click(saveButton);
		logger.info("Password updated for new user and clicked on Save button");
		page.waitForTimeout(6000);

	}

	public void loginwithNewUser(String roles, String dealer, String password, String newpassword)
			throws InterruptedException {
		updateUserPassword(roles, dealer, password);
		page.waitForTimeout(6000);
		Page newBrowserPage = PlaywrightFactory.getBrowser().newContext().newPage();
		newBrowserPage.navigate("https://rc.truvideo.com/");
		logger.info("navigated to the url" + newBrowserPage.url());
		newBrowserPage.waitForTimeout(6000);
		LoginPage loginPage = new LoginPage(newBrowserPage);
		//HomePage homePage = new HomePage(newBrowserPage);
		loginPage.navigateToUpdatePassword(newBrowserPage, userEmailID, usernewDummyPassword);
		logger.info("navigated to the update password page after putting new username and password value");
		newBrowserPage.waitForTimeout(2000);
		newBrowserPage.click(submitButton);
		logger.info("Without adding password value clicked on Submit button");
		if (newBrowserPage.isVisible(updatePasswordRequiredLabel)) {
			logger.info("Without adding password value clicked on Submit button and its showing error message");
		} else {
			logger.info("Password required field label is not showing");
		}
		newBrowserPage.fill(password_TextBox, newpassword);
		logger.info("password value is filled");
		newBrowserPage.fill(confirmNewPassword_TextBox, newpassword);
		logger.info("Both Password and Confirm Password field is filled");
		newBrowserPage.click(submitButton);
		newBrowserPage.waitForTimeout(3000);
		if (newBrowserPage.isVisible(PasswordUpdatedLabelonLoginPage)) {
			logger.info("User is navigated on Login page and Pawword updated label is displayed");
		} else {
			logger.info("Password is not updated and User is unable to navigate on Login page");
		}
		loginPage.navigateToHomePage(userEmailID, newpassword);
		newBrowserPage.waitForTimeout(4000);
		logger.info("Login User is " + LoginPage.logInUsername);
		newBrowserPage.waitForTimeout(6000);
		newBrowserPage.close();
	}
	
	public void userStatus()
	{
		page.waitForTimeout(9000);
		page.fill(userSearchbox, "CzmyFTest Automation");
		page.waitForTimeout(9000);
		logger.info("Searched with Latest user created");
		page.click(searchButton);
		page.waitForTimeout(9000);
		page.click(userDeactivateStatus);
		logger.info("Successfully deactivated user");
		page.waitForTimeout(9000);
		page.waitForCondition(()->page.isVisible(inActiveStatusButton));
		page.click(inActiveStatusButton);
		logger.info("User is clicked on InActive status button");
		page.waitForTimeout(3000);
		page.click(userActivateStatus);
		logger.info("Again Successfully Activated user");
		page.waitForTimeout(2000);
		page.click(activeStatusbutton);
		logger.info("Again Clicked on Active status button and Verified user is present in Active filter");
		
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
