package com.truvideo.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.truvideo.base.BaseTest;
import com.truvideo.pages.ProspectListPage;

public class ProspectListPageTest extends BaseTest {

	ProspectListPage prospectListPage;

	@BeforeClass
	public void prospectListPageSetup() {
		prospectListPage=loginpage.navigateToHomePage(prop.getProperty("username"), prop.getProperty("password"))
				.navigateToProspectList();
	}
	
	@Test(priority = 1)
	public void verifyAllAvailableElementsOnROListPage() {
		Assert.assertTrue(prospectListPage.checkAllAvailableElements_SOListPage());
	}

}
