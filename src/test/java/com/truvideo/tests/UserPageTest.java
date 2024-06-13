package com.truvideo.tests;

import org.testng.annotations.*;

import com.truvideo.base.BaseTest;
import com.truvideo.pages.UserPage;

public class UserPageTest extends BaseTest {
	UserPage userPage;

	@BeforeClass
	public void init() {
		userPage = loginpage.navigateToHomePage(prop.getProperty("username"), prop.getProperty("password"))
				.navigateToUserspage();
	}

	@Test
	public void verifyRequiredFieldinAddUser() throws InterruptedException {
		userPage.addUser("Service Dashboard", "Kenility Store");
	}

	@Test
	public void sasasas() {
		userPage.searchUser("RahulTest");
	}
}
