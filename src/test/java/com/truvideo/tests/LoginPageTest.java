package com.truvideo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.truvideo.base.BaseTest;
import com.truvideo.constants.AppConstants;



public class LoginPageTest extends BaseTest{

	@Test
	public void loginToApplication() {
		String actualTitle = loginpage.loginToApplication(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(actualTitle, AppConstants.HOME_PAGE_TITLE);

	} 
}
