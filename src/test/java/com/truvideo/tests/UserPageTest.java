package com.truvideo.tests;

import org.testng.annotations.BeforeClass;

import com.truvideo.base.BaseTest;
import com.truvideo.pages.UserPage;

public class UserPageTest extends BaseTest {
	UserPage userPage;

	@BeforeClass
	public void init() {
		userPage = loginpage.navigateToHomePage(prop.getProperty("username"), prop.getProperty("password"))
				.navigateToUserspage();
	}
	
	
}
