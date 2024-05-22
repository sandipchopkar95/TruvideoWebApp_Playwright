package com.truvideo.pages;

import com.microsoft.playwright.Page;

public class LoginPage {
	private Page page;
	
	// 1. page constructor
		public LoginPage(Page page) {
			this.page = page;
		}

	// 2. String Locator
	private String username_Field = "input[name='j_username']";
	private String password_Field = "input[name='j_password']";
	private String logIn_Button = "input[value='Log In']";

	// 3. page actions/methods
	public String loginToApplication(String username,String password) {
		navigateToHomePage(username, password);
		System.out.println("New Page title is : "+ page.title());
		return page.title();
	} 
	
	public HomePage navigateToHomePage(String username,String password) {
		page.fill(username_Field, username);
		page.fill(password_Field, password);
		page.click(logIn_Button);
		return new HomePage(page);
	}
}
