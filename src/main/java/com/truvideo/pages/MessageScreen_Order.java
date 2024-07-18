package com.truvideo.pages;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Page.FillOptions;
import com.truvideo.utility.JavaUtility;

public class MessageScreen_Order extends JavaUtility {

	private Page page;

	public MessageScreen_Order(Page page) {

		this.page = page;
}
	
	private String messageIframe="#messages-iframe";
	private String message_profile = "//div[@class='profile__avatar']//div/div";
	private String createIcon  ="mat-icon.profile__action-fab-icon";

	
	public void validateFilters() {
		FrameLocator frame=page.frameLocator(messageIframe);
		frame.locator(createIcon).click();
		frame.locator("").fill("");
		
		page.fill(messageIframe, createIcon);
		page.fill(messageIframe, createIcon, new FillOptions().setTimeout(100));
		
		//page.type(messageIframe, createIcon, null);
		
		
	}
}
