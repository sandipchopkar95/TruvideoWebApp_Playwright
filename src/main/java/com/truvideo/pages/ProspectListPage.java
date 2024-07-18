package com.truvideo.pages;

import java.util.ArrayList;
import java.util.List;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.truvideo.utility.JavaUtility;

public class ProspectListPage extends JavaUtility {
	private Page page;

	public ProspectListPage(Page page) {
		this.page=page;
	}
	
	private String myProspects_FilterButton = "#LBL_MY_RO";
	private String allOpen_FilterButton = "#LBL_ALL_OPEN";
	private String ForReview_FilterButton = "#LBL_FOR_REVIEW";
	private String allClosed_FilterButton = "#LBL_ALL_CLOSED";
	private String closeSalesProspect_Button = "#sales-order-close";
	private String addSalesProspect_Button = "#sales-order-add";
	//Add Sales Prospect
	private String salesProspect_Label = "#sales-order-page-title-text";
	private String lastName_Field = "#customer.lastName";
	private String firstName_Field = "#customer.firstName";
	private String lastNameErrorMessage = "//small[text()='Last Name is a required field.']";
	private String firstNameErrorMessage = "//small[text()='First Name is a required field.']";
	private String phoneNumber_Field = "#phoneNumberCreate";
	private String invalidPhoneNumberErrorMessage = "//small[text()=' Phone number is not valid']";
	private String validatePhoneNumber_Field = "#validate-mobile-number";
	private String emailId_Field = "#customer.email";
	private String stockNo_Field = "#vehicle.stockNo";
	private String vehicleMake_Field = "#vehicle.make";
	private String vehicleModel_Field = "#vehicle.model";
	private String vehicleYear_Field = "#vehicle.year";
	private String savePropsectButton = "(//input[@value='Save'])[1]";
	private String saveAndNewPropsectButton = "(//input[@value='Save and New'])[1]";
	private String cancelPropsectButton = "//button[@type='button'][@class='btn'][text()='Cancel']";
	
	private String tableRows = "table#sales-order-results tr";
	
	
	public boolean checkAllAvailableElements_SOListPage() {
		page.waitForLoadState();
		if (page.isVisible(myProspects_FilterButton) && page.isVisible(allOpen_FilterButton)
				&& page.isVisible(ForReview_FilterButton) && page.isVisible(allClosed_FilterButton)
				&& page.isVisible(salesProspect_Label)) {
			logger.info("All elements are visible on Prospect List page");
			return true;
		} else {
			logger.info("Some Elements are missing on Prospect List page");
			return false;
		}
	}
	
	public boolean clickOn_MySOs_Filter() {
		page.click(myProspects_FilterButton);
		logger.info("Clicked on My SO's Filter button");
		page.waitForURL(url -> url.contains("MY_RO"));
		Locator tableRow = page.locator(tableRows);
		int rowCount = tableRow.count();
		boolean flag = false;
		for (int i = 0; i < rowCount - 1; i++) {
			Locator salesAgent = tableRow.locator("td:nth-child(4)").nth(i);
			String salesAgentName = salesAgent.textContent().trim();
			if (salesAgentName.equals(LoginPage.logInUsername)) {
				logger.info("Match found in Row " + (i + 1) + ": SalesAgent: " + salesAgentName );
				flag = true;
			}
		}
		if (flag == false) {
			logger.info("Something went wrong during SO loding on My SO's filter with login user as : "
					+ LoginPage.logInUsername);
		}
		return flag;
	}
	
	public boolean clickOn_AllOpen_Filter() {
		page.click(allOpen_FilterButton);
		logger.info("Clicked on All Open filter");
		page.waitForURL(url -> url.contains("ALL_OPEN"));
		Locator tableRow = page.locator(tableRows);
		int rowCount = tableRow.count();
		List<Boolean> flags = new ArrayList<Boolean>();
		for (int i = 0; i < rowCount - 1; i++) {
			Locator soNames = tableRow.locator("td:nth-child(6)").nth(i);
			Locator statuses = tableRow.locator("td:nth-child(9)").nth(i);
			String soNAMES = soNames.textContent().trim();
			String status = statuses.innerText().replaceAll("\\s+", " ");
			if (!status.contains("Closed")) {
				logger.info("The SO :" + soNAMES + " is open & Statuses is: " + status);
				flags.add(true);
			} else {
				logger.info("The SO :" + soNAMES + " is open & Statuses is: " + status);
				flags.add(false);
			}
		}
		return !flags.contains(false);
	}
	
	public boolean clickOn_ForReview_Filter() {
		page.click(ForReview_FilterButton);
		logger.info("Clicked on For Review filter");
		page.waitForURL(url -> url.contains("FOR_REVIEW"));
		Locator tableRow = page.locator(tableRows);
		int rowCount = tableRow.count();
		List<Boolean> flags = new ArrayList<Boolean>();
		for (int i = 0; i < rowCount - 1; i++) {
			Locator soNames = tableRow.locator("td:nth-child(6)").nth(i);
			Locator statuses = tableRow.locator("td:nth-child(9)").nth(i);
			String soNAMES = soNames.textContent().trim();
			String status = statuses.innerText().replaceAll("\\s+", " ");
			if (status.contains("For Review")) {
				logger.info("The Status of SO :" + soNAMES + " Contains For Review and  : " + status);
				flags.add(true);
			} else {
				logger.info("The Status of SO :" + soNAMES + " Not Contains For Review  : " + status);
				flags.add(false);
			}
		}
		return !flags.contains(false);
	}
	
	public boolean clickOn_AllClosed_Filter() {
		page.click(allClosed_FilterButton);
		logger.info("Clicked on All Closed filter");
		page.waitForURL(url -> url.contains("ALL_CLOSED"));
		Locator tableRow = page.locator(tableRows);
		int rowCount = tableRow.count();
		List<Boolean> flags = new ArrayList<Boolean>();
		for (int i = 0; i < rowCount - 1; i++) {
			Locator soNames = tableRow.locator("td:nth-child(6)").nth(i);
			Locator statuses = tableRow.locator("td:nth-child(9)").nth(i);
			String roNAMES = soNames.textContent().trim();
			String status = statuses.innerText().replaceAll("\\s+", " ");
			if (status.contains("Closed")) {
				logger.info(
						"The Status of SO :" + roNAMES + " under All Closed filter is closed & Contains: " + status);
				flags.add(true);
			} else {
				logger.info("The Status of SO :" + roNAMES + " under All Closed filter is Not closed & Contains: "
						+ status);
				flags.add(false);
			}
		}
		return !flags.contains(false);
	}
	
	
}
