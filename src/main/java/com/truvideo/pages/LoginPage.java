package com.truvideo.pages;

import com.microsoft.playwright.Page;
import com.truvideo.utility.JavaUtility;

public class LoginPage extends JavaUtility {
	private Page page;

	public LoginPage(Page page) {
		this.page = page;
	}

	private String username_Field = "input[name='j_username']";
	private String password_Field = "input[name='j_password']";
	private String logIn_Button = "input[value='Log In']";
	private String createAccount_ButtonLink = "#register-user";
	private String forgotPassword_ButtonLink = "#forgot-password";
	private String errorAlertMessage_Login = "div[class='alert alert-error']";

	public boolean checkAllElements_LoginPage() {
		if (page.isVisible(username_Field) && page.isVisible(password_Field) && page.isVisible(logIn_Button)
				&& page.isVisible(createAccount_ButtonLink) && page.isVisible(forgotPassword_ButtonLink)) {
			logger.info("All elements are visible on Login Page");
			return true;
		} else {
			return false;
		}
	}

	public String click_CreateAccount_Button() {
		navigateToSignUpPage();
		return page.title();
	}

	public String click_ForgotPassword_Button() {
		navigateToForgotPasswordPage();
		return page.title();
	}

	public String tryToLoginWithoutEnteringCredentials() {
		navigateToHomePage("", "");
		String errorMessage=page.textContent(errorAlertMessage_Login);
		return errorMessage;
	}

	public String loginToApplication(String username, String password) {
		navigateToHomePage(username, password);
		System.out.println("New Page title is : " + page.title());
		return page.title();
	}

	public HomePage navigateToHomePage(String username, String password) {
		page.fill(username_Field, username);
		page.fill(password_Field, password);
		page.click(logIn_Button);
		return new HomePage(page);
	}

	public SignUpPage navigateToSignUpPage() {
		page.click(createAccount_ButtonLink);
		return new SignUpPage(page);
	}

	public ForgotPasswordPage navigateToForgotPasswordPage() {
		page.click(forgotPassword_ButtonLink);
		return new ForgotPasswordPage(page);
	}
}
