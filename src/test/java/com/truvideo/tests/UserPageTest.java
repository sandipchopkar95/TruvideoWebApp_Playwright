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
		userPage.addNewUser("Service Dashboard", "Kenility Store");
	}

	@Test
	public void verifyNewTechnicianUser() throws InterruptedException {
		userPage.addNewTechnician("Kenility Store", "Service App");
	}

	@Test
	public void updatePasswordNewUser() throws InterruptedException {
		userPage.updateUserPassword("Service Dashboard", "Kenility Store", "Test123");
	}

	@Test(priority = 2)
	public void loginwithNewUser() throws InterruptedException {
		userPage.loginwithNewUser("Service Dashboard", "Kenility Store", "Test123", "updatedpassword");
	}

	@Test
	public void verifyUserStatus() throws InterruptedException {
		userPage.userStatus();
	}

	@Test
	public void verifyBulkUserCreation() throws InterruptedException {
		userPage.bulkCreateUser();
	}
	@Test
	public void verifyselectActionsonUser() throws InterruptedException {
		userPage.actionsOnUsers();
	}
}
