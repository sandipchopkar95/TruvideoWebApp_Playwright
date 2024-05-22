package com.truvideo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.truvideo.base.BaseTest;
import com.truvideo.constants.AppConstants;

public class LoginPageTest extends BaseTest {

	@Test(priority = 1)
	public void verifyAllElementsOfLoginPage() {
		Assert.assertTrue(loginpage.checkAllElements_LoginPage());
	}

	@Test(priority = 2)
	public void verifyIsCreateUserButtonWorking() {
		String actualPageTitle = loginpage.click_CreateAccount_Button();
		Assert.assertEquals(actualPageTitle, AppConstants.SIGN_UP_PAGE_TITLE);
		page.goBack();
	}

	@Test(priority = 3)
	public void verifyIsForgotPasswordButtonWorking() {
		String actualPageTitle = loginpage.click_ForgotPassword_Button();
		Assert.assertEquals(actualPageTitle, AppConstants.FORGOT_PASSWORD_PAGE_TITLE);
		page.goBack();
	}
	
	@Test(priority = 4)
	public void loginToApplication_InvalidUser() {
		String actualTitle = loginpage.loginToApplication(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(actualTitle, AppConstants.HOME_PAGE_TITLE);
	}

	@Test(priority = 4)
	public void loginToApplication_ValidUser() {
		String actualTitle = loginpage.loginToApplication(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(actualTitle, AppConstants.HOME_PAGE_TITLE);
	}
}
