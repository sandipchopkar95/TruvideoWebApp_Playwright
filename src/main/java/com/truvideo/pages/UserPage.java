package com.truvideo.pages;

import java.util.List;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
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

	public String getsearchedUser(String username) {
		String username1 = "#user-results tbody tr td:nth-child(3) p:text('" + username + "')";
		return username1;
	}
	public void searchUser(String usernametoSearch) {
	    // Fill the search box with the username to search
	    page.fill(userSearchbox, usernametoSearch);
	    logger.info("Entered user name to search");
	    
	    // Click the search button
	    page.click(searchButton);
	    logger.info("Clicked on search button - Associated users are displaying");
	    
	    // Locate the rows in the search results
	    Locator rows = page.locator("#user-results tbody tr[data-id*='1'], #user-results tbody tr[data-id*='2'], #user-results tbody tr[data-id*='3']");
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
		Locator rows = page.locator("#user-results tbody tr[data-id*='1'], #user-results tbody tr[data-id*='2'],#user-results tbody tr[data-id*='3']");
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
					logger.info("Firstname is : "+firstName);
					logger.info("Lastname is : "+lastname);
				}
			}
		}
	}
}
