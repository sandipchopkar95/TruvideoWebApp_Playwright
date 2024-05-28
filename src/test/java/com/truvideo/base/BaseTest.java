package com.truvideo.base;

import java.util.Properties;
import org.testng.annotations.*;
import com.microsoft.playwright.Page;
import com.truvideo.factory.PlaywrightFactory;
import com.truvideo.pages.LoginPage;

public class BaseTest {
	protected PlaywrightFactory pf;
	public Page page;
	protected Properties prop;
	protected LoginPage loginpage;

	@BeforeTest
	public void loginPageSetup() {
		pf = new PlaywrightFactory();
		prop = pf.init_prop(); // will call config file
		page = pf.initBrowser(prop);
		loginpage = new LoginPage(page); // Initialize LoginPage with the Page instance
	}

	@AfterTest
	public void tearDown() {
		page.context().browser().close();
	}

	

}
