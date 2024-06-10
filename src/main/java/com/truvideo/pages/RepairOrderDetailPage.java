package com.truvideo.pages;

import java.util.ArrayList;
import java.util.List;

import org.testng.asserts.SoftAssert;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.truvideo.utility.JavaUtility;

public class RepairOrderDetailPage extends JavaUtility {
	private Page page;
	// FrameLocator frame;

	public RepairOrderDetailPage(Page page) {
		this.page = page;
		// frame = page.frameLocator(orderDetailsIFrame);
	}

	private String orderDetailsIFrame = "#order-details-iframe";
	private String nextRO_Button = ".order-navigation:has-text('Next RO')";
	private String roNumber = "h1.orders-detail-menu__ro-number";
	private String roStatusBar = "div.orders-detail-menu__action";
	private String addMedia = "div.orders-detail-menu__media-add";
	private String payment_Button = ".orders-detail-menu__payments";
	private String estimate_Button = ".orders-detail-menu__estimates";

	private String operations_Heading = ".operations__container__title";
	private String details_Heading = "span.title--details";

	private String activity_Tab = "div[role='tab'] span:has-text('Activity')";
	private String activities = "app-activity div.detail__activity  p.detail__activity-title";

	public boolean checkAllMandatoryFields_ForNewRO() {
		page.waitForURL(url -> url.contains("order/service/view"));
		List<Boolean> flags = new ArrayList<Boolean>();
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		if (frame.locator(roStatusBar).textContent().contains("New")) {
			logger.info("RO Status is: " + frame.locator(roStatusBar).textContent());
			flags.add(true);
		} else {
			logger.info("RO Status is: " + frame.locator(roStatusBar).textContent());
			flags.add(false);
		}
		frame.locator(activity_Tab).first().click();
		if (frame.locator(activities).first().textContent().contains("Created new repair order.")) {
			logger.info("RO activity is: " + frame.locator(activities).first().textContent());
			flags.add(true);
		} else {
			logger.info("RO activity is: " + frame.locator(activities).first().textContent());
			flags.add(false);
		}
		return !flags.contains(false);
	}

	private String addVideo_Title = "div.video-library__title";
	private String operations_Buttons = ".menu-options";

	private String sendToCustomer_Button = "button span:has-text('Send to customer')";
	private String viewWithCustomer_Button = "div span:has-text('View with customer')";

	public String selectButton(String buttonText) {
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		Locator buttons = frame.locator(operations_Buttons);
		String className = null;
		for (ElementHandle locator : buttons.elementHandles()) {
			String textContent = locator.innerText();
			if (textContent != null && textContent.contains(buttonText)) {
				className = locator.getAttribute("class");
				break;
			}
		}
		return className;
	}

	public void sendVideoToCustomer() {
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		page.waitForURL(url -> url.contains("order/service/view"));
		List<Boolean> flags = new ArrayList<Boolean>();
		SoftAssert softAssert = new SoftAssert();
		String sendToCustomerClass=selectButton("Send to customer");
		String viewWithCustomerClass=selectButton("View with customer");
		if (sendToCustomerClass == null || viewWithCustomerClass == null) {
	        logger.info("'Send to customer' or 'View with customer' button is not found");
	        flags.add(false);
	    } else if (sendToCustomerClass.contains("disabled") && viewWithCustomerClass.contains("disabled")) {
	        logger.info("Both 'Send to customer' & 'View with customer' button is disabled");
	        flags.add(true);
	    } else {
	        logger.info("'Send to customer' or 'View with customer' button is not disabled");
	        flags.add(false);
	    }
		softAssert.assertTrue(!flags.contains(false),
				"Verify 'Send to customer' & 'View with customer' button is disabled");
		flags.clear();
		frame.locator(addMedia).click();	
		if (frame.locator(addVideo_Title).textContent().equals("Add video")) {
			logger.info("Multimedia Screen opened: " + frame.locator(addVideo_Title).textContent());
			flags.add(true);
		} else {
			logger.info("Multimedia Screen not opened");
			flags.add(false);
		}
		softAssert.assertTrue(!flags.contains(false), "Verify Add Media button is clickable");
		softAssert.assertAll();
	}

}
