package com.truvideo.pages;

import com.microsoft.playwright.Page;

public class HomePage {
	private Page page;

	public HomePage(Page page) {
		this.page = page;
	}

	private String repairOrder_Header = "a[href='/crud/repair-order']";
	private String users_Header_Button = "a[href='/organization/tce-users/']";

	public String navigationToOrderList() {
		navigateToOrderList();
		return page.title();
	}

	public OrderListPage navigateToOrderList() {
		page.click(repairOrder_Header);
		return new OrderListPage(page);
	}
	
	public UserPage navigateToUserspage() {
		page.click(users_Header_Button);
		return new UserPage(page);
	}

}
