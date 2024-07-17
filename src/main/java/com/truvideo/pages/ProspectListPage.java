package com.truvideo.pages;

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
	private String addSalesProspect_Label = "#add-order-page-title-text";
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
	
	
	public boolean checkAllAvailableElements_SOListPage() {
		page.waitForLoadState();
		if (page.isVisible(myProspects_FilterButton) && page.isVisible(allOpen_FilterButton)
				&& page.isVisible(ForReview_FilterButton) && page.isVisible(allClosed_FilterButton)
				&& page.isVisible(addSalesProspect_Label)) {
			logger.info("All elements are visible on Prospect List page");
			return true;
		} else {
			logger.info("Some Elements are missing on Prospect List page");
			return false;
		}
	}
	
	
	
}
