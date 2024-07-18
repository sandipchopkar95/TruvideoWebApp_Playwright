package com.truvideo.pages;

import com.microsoft.playwright.Page;
import com.truvideo.utility.JavaUtility;

public class MessageScreen_Order extends JavaUtility {

	private Page page;

	public MessageScreen_Order(Page page) {

		this.page = page;
}
	
	private String message_btn = "a#service-messages-link";
	private String message_profile = "//div[@class='profile__avatar']//div/div";
	

}
