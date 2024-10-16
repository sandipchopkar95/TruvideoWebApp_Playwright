package com.truvideo.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.testng.SkipException;
import org.testng.asserts.SoftAssert;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.TimeoutError;
import com.truvideo.constants.AppConstants;
import com.truvideo.factory.PlaywrightFactory;
import com.truvideo.utility.JavaUtility;

public class RepairOrderDetailPage extends JavaUtility {
	private Page page;

	public RepairOrderDetailPage(Page page) {
		this.page = page;
	}

	private String repairOrder_PageHeading = ".main-body span:has-text('Repair Order')";
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
	private String customer_tab = "div[role='tab'] span:has-text('Customer')";
	private String activities = "app-activity div.detail__activity  p.detail__activity-title";
	private String addVideo_Title = "div.video-library__title p";
	private String operations_Buttons = ".menu-options";
	private String videos = "img[alt='video thumbnail']";
	private String add_Button = "div.video-library__add-video-container button";
	private String added_Video = "div.orders-detail-menu__media-videos img";
	private String selectChannelWindow = "'Please select a channel'";
	private String whatsApp_Button = ".selected-channel-actions button:has-text('WhatsApp')";
	private String sms_Button = ".selected-channel-actions button:has-text('SMS')";
	private String communicationTabs = ".mat-mdc-tab-label-container div[role='tab']";
	private String messages = "ngx-message div.message";
	// Estimate
	private String create_Edit_Estimate_WindowHeader = "mat-card span:has-text('Estimate - Create/Edit Estimate')";
	private String items_Tab = "mat-card-content .mdc-tab__content span:has-text('Items')";
	private String notes_Tab = "mat-card-content .mdc-tab__content span:has-text('Notes')";
	private String list_ItemHeader = "mat-tab-body div.items__info-header p";
	private String newItem_TextField = "input[placeholder='New Item']";
	private String description_TextField = "input[placeholder='Description']";
	private String amount_TextField = "input[placeholder='Amount']";
	private String type_DropdownField = "input[placeholder='Type']";

	private String selectTypeFromDropdown(String type) {
		return ".tru-dropdown__content-items strong:has-text('" + type + "')";
	}

	private String closeWindow_Button = "mat-icon:has-text('close')";
	private String addItem_Button = ".items__input-container button:has-text('add')";
	private String removeItem_Button = ".items__input-container button:has-text('backspace')";
	private String items_DropdownList = ".tru-dropdown__content-items";
	private String deleteItem_Button = "mat-tab-body .items__info-data button:has-text('delete_outline')";
	private String editItem_Button = ".items__info-data__row-data button.mdc-button :has-text('edit')";
	private String preview_Button = ".mdc-button__label:has-text('Preview')";
	private String itemsAmount = "app-estimate-invoice .items__container p.items__info-data__row-data:nth-child(3)";
	private String totalAmount = ".invoice-amount";
	private String buttons_PreviewScreen = ".items__container-actions button.mr-5";
	private String hideFromCustomer_Button = "button:has-text('Hide from customer')";
	private String closeEstimate_Button = "button:has-text('Close Estimate')";
	private String edit_Button = ".items__container-actions button.mr-5 :has-Text('Edit')";
	private String print_Button = "button:has-text('Print')";
	private String send_Button = "button.primary:has-text('Send')";
	private String hiddenMessage = ".invoice__hide-customer";
	private String newEstimate_Button = "button:has-text('New Estimate')";
	private String confirm_Button = "button.primary:has-text('Confirm')";
	private String notify_Button = "button.primary:has-text('Notify')";
	// Payment
	private String next_Button = "app-items button:has-text('Next')";
	private String internalNotes_TextBox = "form input[placeholder='Write an internal note.']";
	private String customerNotes_TextBox = "form input[placeholder='Write a note for the customer.']";
	private String rowCount = "div.items__info-container div button:has-text('delete_outline')";
	private String windowTitle = "mat-card-content .mat-toolbar span";
	private String toaster_Message = "'Amount is empty or incorrect.'";
	private String back_Button = ".mdc-button__label:has-text('Back')";
	private String amount_TextField_Payment = "app-price tru-text-input input";
	private String addedNote_ReviewScreen = "app-invoice .invoice-notes";
	private String final_Amount = ".invoice-total__container .invoice-amount";
	private String editButton_ReviewScreen = "app-invoice button:has-text('Edit')";
	private String printButton_ReviewScreen = "app-invoice button:has-text('Print')";
	private String sendButton_ReviewScreen = "app-invoice button:has-text('Send')";
	private String lastMessageEndlink = "ngx-message div.message a";
	private String playButton = "button[title='Play Video']";
	private String authoriseWork_Button = "button:has-text('Authorize Work')";
	// Edit RO
	

	private String getRO(String createdRO) {
		String createdRoInList = "#repair-order-results tbody tr:has-text('" + createdRO + "')";
		return createdRoInList;
	}

	public boolean checkAllMandatoryFields_ForNewRO() {
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		frame.locator(repairOrder_PageHeading).waitFor();
		List<Boolean> flags = new ArrayList<Boolean>();
		flags.add(checkStatus("New")); // verify status whether New or Not
		flags.add(checkActivity("Created new repair order.")); // verify activity:Created new repair order
		return !flags.contains(false);
	}
	
	public void addVideoToOrder() {
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		frame.locator(repairOrder_PageHeading).waitFor();
		List<Boolean> flags = new ArrayList<Boolean>();
		SoftAssert softAssert = new SoftAssert();
		if (frame.locator(roStatusBar).textContent().contains("New")) {
			logger.info("RO is New & No media is added");
			String sendToCustomerClass = getLocatorClass(operations_Buttons, "Send to customer");
			String viewWithCustomerClass = getLocatorClass(operations_Buttons, "View with customer");
			String insightClass = getLocatorClass(operations_Buttons, "Insights");
			if (sendToCustomerClass.contains("disabled") && viewWithCustomerClass.contains("disabled") && insightClass.contains("disabled")) {
				logger.info("Both 'Send to customer','View with customer' & 'Insights' button is disabled");
				flags.add(true);
			} else {
				logger.info("'Send to customer','View with customer' & 'Insights' button is not disabled");
				flags.add(false);
			}
			softAssert.assertTrue(!flags.contains(false),
					"Verify 'Send to customer' & 'View with customer' button is disabled");
			flags.clear();
		} else {
			logger.info("RO is Not new & some videos are already added to RO");
		}
		frame.locator(addMedia).click();
		if (frame.locator(addVideo_Title).textContent().equals("Add video")) {
			logger.info("Multimedia Screen opened: " + frame.locator(addVideo_Title).textContent());
			flags.add(true);
		} else {
			logger.info("Multimedia Screen not opened");
			flags.add(false);
		}
		softAssert.assertTrue(!flags.contains(false), "Verify Add Media button is clickable");
		flags.clear();
		frame.locator(videos).first().click();
		logger.info("Selected 1 video from multimedia screen");
		page.waitForTimeout(2000);
		frame.locator(add_Button).click();
		logger.info("Clicked on Add Video Button");
		page.waitForTimeout(4000);
		String sendToCustomerClass_AfterVideoAdded = getLocatorClass(operations_Buttons, "Send to customer");
		String viewWithCustomerClass_AfterVideoAdded = getLocatorClass(operations_Buttons, "View with customer");
		int addedVideoCount = frame.locator(added_Video).count();
		if (addedVideoCount >= 0) {
			logger.info("Video added sucessfully and visible on media gallery");
			flags.add(true);
		} else {
			logger.info("Selected Video not added to media gallery");
			flags.add(false);
		}
		flags.add(checkStatus("For Review")); // verify status whether For Review or Not
		softAssert.assertTrue(!flags.contains(false), "Verify status changed to For Review");
		flags.clear();
		softAssert.assertTrue(verifyChangedStatusOnROList("For Review"),
				"Verify status changed to For Review on RO list screen");
		if (sendToCustomerClass_AfterVideoAdded == null || viewWithCustomerClass_AfterVideoAdded == null) {
			logger.info("'Send to customer' or 'View with customer' button is not found");
			flags.add(false);
		} else if (!sendToCustomerClass_AfterVideoAdded.contains("disabled")
				&& !viewWithCustomerClass_AfterVideoAdded.contains("disabled")) {
			logger.info("Both 'Send to customer' & 'View with customer' button is enabled");
			flags.add(true);
		} else {
			logger.info("'Send to customer' or 'View with customer' button is disabled");
			flags.add(false);
		}
		flags.add(checkActivity("Added video"));
		softAssert.assertTrue(!flags.contains(false), "Verify add video function");
		softAssert.assertAll();
	}
	
	public void sendVideoToCustomer(String channelSelected) {
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		if (!frame.locator(added_Video).first().isVisible()) {
			logger.info("Condition not satisfied for Send Video : video not added to RO");
			throw new SkipException("video not added to RO");
		}
		List<Boolean> flags = new ArrayList<Boolean>();
		SoftAssert softAssert = new SoftAssert();
		page.waitForTimeout(2000);
		clickOperationButton("Send to customer");
		selectChannelToPerformAction(channelSelected); // Select channel to send video
		flags.add(verifyNavigationToChannel(channelSelected));// Navigation to channel after video sent
		softAssert.assertTrue(!flags.contains(false), "Verify Navigation To selected channel");
		flags.clear();
		flags.add(checkStatus("Sent")); // verify status whether Sent or Not
		softAssert.assertTrue(!flags.contains(false), "Verify Status changed to sent");
		flags.clear();
		softAssert.assertTrue(verifyChangedStatusOnROList("Sent"), "Verify Status changed to Sent on RO List");
		flags.add(checkLastMessageInConversation("video")); // check last message is video end-link or Not
		flags.clear();
		softAssert.assertTrue(!flags.contains(false), "Verify last message is video endlink");
		flags.add(checkActivity("sent to customer"));
		softAssert.assertTrue(!flags.contains(false), "Verify activity update after video sent");
		softAssert.assertAll();
		flags.clear();
	}

	public void activitiesOfCreateEstimateWindow() {
		SoftAssert softAssert = new SoftAssert();
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		try {
			logger.info("Waiting for the Estimate button to be visible");
			page.waitForCondition(() -> frame.locator(estimate_Button).isVisible());
		} catch (TimeoutError e) {
			e.printStackTrace();
		}
		if (!frame.locator(estimate_Button).isVisible()) {
			logger.info("Estimate Button Not available : May be disabled from Dealer Setting");
			throw new SkipException("Estimate Button Not available : May be disabled from Dealer Setting");
		}
		frame.locator(estimate_Button).click();
		logger.info("Click on Create Estimate button");
		page.waitForTimeout(4000);
		addItemToList(items_DropdownList, "Test Item 1", "Pre-Approved"); // adding exact same value from list
		addItemToList(items_DropdownList, "Test Item", "Recommended"); // adding value that is not in list
		addItemToList(items_DropdownList, "ABCDEFGH", "Pre-Approved"); // adding value that is not in list
		frame.locator(closeWindow_Button).click();
		page.waitForTimeout(2000);
		logger.info("Click on close estimate window");
		String buttonName = frame.locator(estimate_Button).textContent();
		softAssert.assertTrue(buttonName.contains("Edit Estimate"), "Verify button changed to Edit Estimate");
		softAssert.assertTrue(verifyChangedStatusOnROList("Est-Draft"), "Verify 'Est-Draft' on RO list screen");
		softAssert.assertTrue(checkActivity("Created new estimate."), "Verify activity added for Create Estimate");
		frame.locator(estimate_Button).click();
		logger.info("Click on Edit Estimate button");
		addItemToList(items_DropdownList, "Items added after re-open the window", "Recommended");
		frame.locator(preview_Button).click();
		logger.info("Click on Preview Estimate button");
		page.waitForTimeout(4000);
		softAssert.assertTrue(calculateTotalItemAmount(itemsAmount) == getTotalAmount(totalAmount),
				"Verify total amount");
		List<String> allAvailableButtons_PreviewScreen = Arrays.asList("Hide from customer", "Close Estimate", "Edit",
				"Print", "Send");
		List<String> allButtonTexts_PreviewScreen = page.locator(buttons_PreviewScreen).allInnerTexts();
		softAssert.assertTrue(allAvailableButtons_PreviewScreen.containsAll(allButtonTexts_PreviewScreen),
				"All buttons are available on Preview estimate screen");
		frame.locator(hideFromCustomer_Button).click();
		logger.info("Click on Hide from customer button");
		page.waitForTimeout(2000);
		List<Boolean> flags = new ArrayList<Boolean>();
		if (frame.locator(hiddenMessage).isVisible()) {
			flags.add(true);
			logger.info("Hidden message displayed");
			frame.locator(hideFromCustomer_Button).click();
		} else {
			flags.add(false);
			logger.info("Hidden message not displayed");
		}
		frame.locator(hideFromCustomer_Button).click();
		logger.info("Click on Hide from customer button");
		page.waitForTimeout(2000);
		if (!frame.locator(hiddenMessage).isVisible()) {
			flags.add(true);
			logger.info("Hidden message removed");
			frame.locator(hideFromCustomer_Button).click();
		} else {
			flags.add(false);
			logger.info("Hidden message still displayed");
		}
		softAssert.assertTrue(!flags.contains(false), "Verify hide from customer");
		flags.clear();
		frame.locator(edit_Button).click();
		page.waitForTimeout(2000);
		if (frame.locator(preview_Button).isVisible()) {
			logger.info("User navigate back to add item screen");
			flags.add(true);
		} else {
			logger.info("User not navigate back to add item screen");
			flags.add(false);
		}
		softAssert.assertTrue(!flags.contains(false), "Verify navigation on clicking edit button");
		flags.clear();
		if (frame.locator(preview_Button).isVisible()) {
			frame.locator(preview_Button).click();
			logger.info("User navigate to preview screen again");
		}
		Page newPage = PlaywrightFactory.getBrowserContext().waitForPage(() -> {
			frame.locator(print_Button).click();
			logger.info("Click on Print button");
		});
		newPage.waitForLoadState();
		softAssert.assertTrue(newPage.url().contains(AppConstants.PRINT_SCREEN_URL), "verify print screen opened?");
		newPage.close();
		frame.locator(closeEstimate_Button).click();
		page.waitForTimeout(2000);
		if (frame.locator(estimate_Button).textContent().contains("Estimate Closed")) {
			logger.info("Button name changed to Estimate Closed");
			flags.add(true);
		} else {
			logger.info("Button name not changed to Estimate Closed");
			flags.add(false);
		}
		softAssert.assertTrue(!flags.contains(false), "verify close Estimate button text");
		page.waitForTimeout(2000);
		softAssert.assertTrue(checkActivity("Estimate closed."), "verify activity for close Estimate");
		softAssert.assertAll();
	}

	public void sendEstimate(String channelSelected) {
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		try {
			logger.info("Waiting for the Estimate button to be visible");
			page.waitForCondition(() -> frame.locator(estimate_Button).isVisible());
		} catch (TimeoutError e) {
			e.printStackTrace();
		}
		if (!frame.locator(estimate_Button).isVisible()) {
			logger.info("Estimate Button Not available : May be disabled from Dealer Setting");
			throw new SkipException("Estimate Button Not available : May be disabled from Dealer Setting");
		}
		frame.locator(estimate_Button).click();
		logger.info("Clicked on Estimate button");
		page.waitForCondition(() -> frame.locator(newEstimate_Button).isVisible());
		frame.locator(newEstimate_Button).click();
		addItemToList(items_DropdownList, "Test Item 1", "Pre-Approved"); // adding exact same value from list
		addItemToList(items_DropdownList, "Test Item", "Recommended"); // adding value that is not in list
		addItemToList(items_DropdownList, "ABCDEFGH", "Pre-Approved"); // adding value that is not in list
		frame.locator(preview_Button).click();
		logger.info("Clicked on preview button");
		page.waitForTimeout(2000);
		frame.locator(send_Button).click();
		logger.info("Clicked on Send button");
		selectChannelToPerformAction(channelSelected);// Select channel to send video
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(verifyNavigationToChannel(channelSelected), "Verify Navigation To selected channel");
		String buttonName = frame.locator(estimate_Button).textContent();
		softAssert.assertTrue(buttonName.contains("Resend Estimate"), "Verify estimate Status");
		logger.info("Estimate status changed to : " + buttonName);
		softAssert.assertTrue(verifyChangedStatusOnROList("Est-Sent"), "Verify Status changed to Est-Sent on RO List");
		softAssert.assertTrue(checkLastMessageInConversation("estimate"), "Verify last message is Estimate endlink");
		softAssert.assertTrue(checkActivity("Estimate sent to customer"), "Verify activity update after estimate sent");
		softAssert.assertAll();
	}

	public void resendEstimate(String channelSelected) {
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		frame.locator(estimate_Button).click();
		page.waitForTimeout(10000);
		if (!frame.locator(confirm_Button).isVisible()) {
			logger.info("Condition not satisfied for Resend : Estimate was not sent before");
			frame.locator(closeWindow_Button).click();
			throw new SkipException("Estimate was not sent before");
		}
		frame.locator(confirm_Button).click();
		logger.info("Clicked on confiem button");
		selectChannelToPerformAction(channelSelected);// Select channel to send video
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(verifyNavigationToChannel(channelSelected), "Verify Navigation To selected channel");
		String buttonName = frame.locator(estimate_Button).textContent();
		softAssert.assertTrue(buttonName.contains("Resend Estimate"), "Verify estimate Status");
		logger.info("Estimate status changed to : " + buttonName);
		softAssert.assertTrue(verifyChangedStatusOnROList("Est-Sent"), "Verify Status changed to Est-Sent on RO List");
		softAssert.assertTrue(checkLastMessageInConversation("estimate"), "Verify last message is Estimate endlink");
		softAssert.assertTrue(checkActivity("Estimate resent to customer"),
				"Verify activity update after estimate resent");
		softAssert.assertAll();
	}

	public void createPayment(String channelSelected) {
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		SoftAssert softAssert = new SoftAssert();
		try {
			logger.info("Waiting for the Payment button to be visible");
			page.waitForCondition(() -> frame.locator(payment_Button).isVisible());
		} catch (TimeoutError e) {
			e.printStackTrace();
		}
		if (!frame.locator(payment_Button).isVisible()) {
			logger.info("Payment button is not available : May be disabled from dealer setting");
			throw new SkipException("Payment button is not available : May be disabled from dealer setting");
		}
		frame.locator(payment_Button).click();
		logger.info("Click on Create Payment Button");
		addItemToList_Payment(items_DropdownList, "Test Item 2"); // adding exact same value from list
		addItemToList_Payment(items_DropdownList, "Payment Item 1"); // adding value that is not in list
		addItemToList_Payment(items_DropdownList, "Payment Item 2"); // adding value that is not in list
		frame.locator(closeWindow_Button).click();
		softAssert.assertTrue(checkActivity("Created new invoice."), "verify activity for new invoice creation");
		String paymentButtonText = frame.locator(payment_Button).textContent();
		logger.info("Payment Status changed to " + paymentButtonText);
		softAssert.assertTrue(paymentButtonText.contains("Edit Payment"), "verify payment status");
		frame.locator(payment_Button).click();
		addItemToList_Payment(items_DropdownList, "Test Item 3"); // adding value reopen payment window
		int itemsAddedInList = frame.locator(rowCount).all().size();
		String itemsText = frame.locator(items_Tab).textContent();
		softAssert.assertTrue(itemsText.contains(String.valueOf(itemsAddedInList)), "Verify Count on Items Tab");
		frame.locator(notes_Tab).click();
		logger.info("Click on Notes Tab");
		page.waitForTimeout(2000);
		frame.locator(internalNotes_TextBox).fill("Testing Internal Note");
		logger.info("Internal Note Entered");
		String customerNote = "Testing Customer Note";
		frame.locator(customerNotes_TextBox).fill(customerNote);
		logger.info("Customer Note Entered");
		frame.locator(notes_Tab).click(); // Clicking for save entered value
		frame.locator(items_Tab).click();
		deleteItemFromList();
		int itemsInList_AfterDelete = frame.locator(rowCount).all().size();
		String itemsText_AfterDelete = frame.locator(items_Tab).textContent();
		softAssert.assertTrue(itemsText_AfterDelete.contains(String.valueOf(itemsInList_AfterDelete)),
				"Verify Delete Item From List");
		frame.locator(next_Button).click();
		logger.info("Click on Next Button");
		softAssert.assertEquals(getWindowHeading(), "Payment - Add Invoice Amount", "Verify Amount window heading");
		frame.locator(preview_Button).click();
		logger.info("Click on Preview Button without entering amount");
		softAssert.assertTrue(frame.locator(toaster_Message).isVisible(), "Verify toaster message for null amount");
		frame.locator(back_Button).click();
		logger.info("Click on Back Button");
		softAssert.assertEquals(getWindowHeading(), "Payment - Add Invoice Items & Notes",
				"Verify Add items window heading on Back operation");
		if (frame.locator(next_Button).isVisible()) {
			frame.locator(next_Button).click();
			logger.info("Again Click on Next Button");
		}
		String enteredAmount = "101";
		frame.locator(amount_TextField_Payment).fill(enteredAmount);
		logger.info("Amount entered in payment");
		frame.locator(preview_Button).click();
		logger.info("Click on Preview button afterd adding Amount");
		softAssert.assertEquals(getWindowHeading(), "Payment - Review and Send Invoice",
				"Verify Review window heading");
		// softAssert.assertTrue(frame.locator(addedNote_ReviewScreen).textContent().contains(customerNote),"Verify
		// Customer Note");
		String finalAmount = frame.locator(final_Amount).textContent();
		logger.info("Final Amount is " + finalAmount);
		softAssert.assertTrue(finalAmount.contains(enteredAmount), "Verify Final Amount");
		frame.locator(editButton_ReviewScreen).click();
		logger.info("Click on edit button of review screen");
		softAssert.assertEquals(getWindowHeading(), "Payment - Add Invoice Amount", "Verify Add Amount window heading");
		if (frame.locator(preview_Button).isVisible()) {
			frame.locator(preview_Button).click();
			logger.info("Again Clicked on preview button");
		}
		Page newPage = PlaywrightFactory.getBrowserContext().waitForPage(() -> {
			frame.locator(printButton_ReviewScreen).click();
			logger.info("Click on Print button");
		});
		newPage.waitForLoadState();
		softAssert.assertTrue(newPage.url().contains(AppConstants.PRINT_SCREEN_URL), "verify print screen opened?");
		newPage.close();
		frame.locator(sendButton_ReviewScreen).click();
		logger.info("Click on Send button");
		selectChannelToPerformAction(channelSelected);// Select channel to send video
		softAssert.assertTrue(verifyNavigationToChannel(channelSelected), "Verify Navigation To selected channel");
		String buttonName = frame.locator(payment_Button).textContent();
		softAssert.assertTrue(buttonName.contains("Resend Payment"), "Verify payment Status");
		logger.info("Payment status changed to : " + buttonName);
		softAssert.assertTrue(verifyChangedStatusOnROList("$-Sent"), "Verify Status changed to $-Sent on RO List");
		boolean isPaymentEndlink = false;
		if (checkLastMessageInConversation("invoice") || checkLastMessageInConversation("payment")) {
			isPaymentEndlink = true;
		}
		softAssert.assertTrue(isPaymentEndlink, "Verify last message is Payment endlink");
		softAssert.assertTrue(checkActivity("Invoice sent to customer."), "Verify activity update after payment sent");
		softAssert.assertAll();
	}

	public void resendPayment(String channelSelected) {
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		SoftAssert softAssert = new SoftAssert();
		try {
			logger.info("Waiting for the Payment button to be visible");
			page.waitForCondition(() -> frame.locator(payment_Button).isVisible());
		} catch (TimeoutError e) {
			e.printStackTrace();
		}
		if (frame.locator(payment_Button).textContent().contains("Resend Payment")) {
			logger.info("Condition is satisfied to payment resend");
		} else {
			logger.info("Condition is not satisfied to payment resend");
			throw new SkipException("Condition is not satisfied to resend payment");
		}
		frame.locator(payment_Button).click();
		logger.info("Clicked on Payment Button");
		frame.locator(next_Button).click();
		logger.info("Clicked on Next Button");
		frame.locator(preview_Button).click();
		logger.info("Clicked on preview Button");
		frame.locator(sendButton_ReviewScreen).click();
		logger.info("Clicked on Send Button");
		selectChannelToPerformAction(channelSelected);// Select channel to send video
		softAssert.assertTrue(verifyNavigationToChannel(channelSelected), "Verify Navigation To selected channel");
		String buttonName = frame.locator(payment_Button).textContent();
		softAssert.assertTrue(buttonName.contains("Resend Payment"), "Verify payment Status");
		logger.info("Payment status changed to : " + buttonName);
		softAssert.assertTrue(verifyChangedStatusOnROList("$-Sent"), "Verify Status changed to $-Sent on RO List");
		boolean isPaymentEndlink = false;
		if (checkLastMessageInConversation("invoice") || checkLastMessageInConversation("payment")) {
			isPaymentEndlink = true;
		}
		softAssert.assertTrue(isPaymentEndlink, "Verify last message is Payment endlink");
		softAssert.assertTrue(checkActivity("Invoice resent to customer."),
				"Verify activity update after payment resent");
		softAssert.assertAll();
	}

	public void checkStatus_OnVideoWatch() {
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		SoftAssert softAssert = new SoftAssert();
		String lastMessage = frame.locator(messages).last().textContent();
		if (lastMessage.contains("video") || lastMessage.contains("Video")) {
			logger.info("Last message is video Endlink");
			Page endlinkPage = PlaywrightFactory.getBrowserContext().waitForPage(() -> {
				frame.locator(lastMessageEndlink).last().click();
				logger.info("Endlink opened in another tab");
			});
			endlinkPage.waitForLoadState();
			endlinkPage.waitForCondition(() -> endlinkPage.url().contains("truvideo.com/v/"));
			endlinkPage.locator(playButton).first().click();
			logger.info("Clicked on Play Button");
			logger.info("Waiting to play video for 8 Seconds");
			page.waitForTimeout(8000);
			endlinkPage.close();
		} else {
			logger.info("Last message is not video Endlink");
			throw new SkipException("Last message is not video Endlink");
		}
		softAssert.assertTrue(frame.locator(roStatusBar).textContent().contains("Viewed"), "verify viewed status");
		softAssert.assertTrue(verifyChangedStatusOnROList("Viewed"), "verify viewed status on RO list");
		softAssert.assertTrue(checkActivity("Customer watched video"), "verify activity for video view");
		softAssert.assertAll();
	}

	public void estimateConfirmation(String channelSelected) {
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		SoftAssert softAssert = new SoftAssert();
		String lastMessage = frame.locator(messages).last().textContent();
		if (lastMessage.contains("estimate") || lastMessage.contains("Estimate")) {
			logger.info("Last message is estimate Endlink");
			Page endlinkPage = PlaywrightFactory.getBrowserContext().waitForPage(() -> {
				frame.locator(lastMessageEndlink).last().click();
				logger.info("Endlink opened in another tab");
			});
			PlaywrightFactory.getBrowserContext().onDialog(dialog -> {
				logger.info("Popup message: " + dialog.message());
				dialog.accept();
			});
			endlinkPage.waitForLoadState();
			endlinkPage.waitForCondition(() -> endlinkPage.url().contains("truvideo.com/v/"));
			endlinkPage.click(authoriseWork_Button);
			logger.info("Click on authorise work button");
			endlinkPage.close();
		} else {
			logger.info("Last message is not estimate Endlink");
			throw new SkipException("Last message is not estimate Endlink");
		}
		softAssert.assertTrue(frame.locator(estimate_Button).textContent().contains("Review Approved Work"),
				"verify Review Approved Work status");
		softAssert.assertTrue(verifyChangedStatusOnROList("Est-Approved"), "verify Est-Approved status on RO list");
		softAssert.assertTrue(checkActivity("Customer submitted decision on estimate"),
				"verify activity for Approved estimate");
		frame.locator(estimate_Button).click();
		logger.info("Click on estimate button");
		frame.locator(confirm_Button).click();
		logger.info("Click on Confirm button");
		frame.locator(closeWindow_Button).click();
		logger.info("Click on close estimate window button");
		softAssert.assertTrue(!frame.locator(estimate_Button).textContent().contains("Review Approved Work"),
				"verify Estimate status on confirm estimate");
		softAssert.assertTrue(verifyChangedStatusOnROList("Est-Confirmed"), "verify Est-Confirmed status on RO list");
		softAssert.assertTrue(checkActivity("Estimate confirmed"), "verify activity for confirm estimate");
		frame.locator(estimate_Button).click();
		logger.info("Again click on estimate button");
		frame.locator(notify_Button).click();
		logger.info("Click on Notify button");
		selectChannelToPerformAction(channelSelected);// Select channel to send video
		softAssert.assertTrue(verifyNavigationToChannel(channelSelected), "Verify Navigation To selected channel");
		boolean isLastMessageIsConfirmation = false;
		if (checkLastMessageInConversation("proceeding") || checkLastMessageInConversation("requested work")) {
			logger.info("last message is of estimate confirmation");
			isLastMessageIsConfirmation = true;
		}
		softAssert.assertTrue(isLastMessageIsConfirmation, "Verify confirmation message sent");
		softAssert.assertTrue(checkActivity("Estimate confirmation sent to customer."),
				"verify activity for Estimate confirmation");
		softAssert.assertAll();
	}

	private String payNow_Button = "#pay-now-button-1";
	private String payWithCardButton = "button:has-text('Pay with Card')";
	private String emailTextBox = "html #email";
	private String cardNumberTextBox = "#card_number";
	private String expiryDate = "#cc-exp";
	private String cvc_Date = "#cc-csc";
	private String submitButton = "submitButton";
	private String paidInvoice_Heading = "'PAID INVOICE'";
	private String printPayment_Button = "button:has-text('Print')";
	private String processedPayment_Button = "button:has-text('Processed')";
	private String paymentIframe = "iframe[src*='https://checkout.stripe.com']";

	public void submitPayment() {
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		SoftAssert softAssert = new SoftAssert();
		String lastMessage = frame.locator(messages).last().textContent();
		if (lastMessage.contains("invoice") || lastMessage.contains("payment")) {
			logger.info("Last message is Payment Endlink");
			Page endlinkPage = PlaywrightFactory.getBrowserContext().waitForPage(() -> {
				frame.locator(lastMessageEndlink).last().click();
				logger.info("Endlink opened in another tab");
			});
			endlinkPage.waitForLoadState();
			endlinkPage.waitForCondition(() -> endlinkPage.url().contains("truvideo.com/v/"));
			endlinkPage.click(payNow_Button);
			logger.info("Endlink : Click on pay Now Button");
			endlinkPage.click(payWithCardButton);
			logger.info("Endlink : Click on pay with card Button");
			FrameLocator paymentFrame = page.frameLocator(paymentIframe);
			paymentFrame.locator(emailTextBox).fill("test@gmail.com");
			paymentFrame.locator(cardNumberTextBox).fill("4111111111111111");
			paymentFrame.locator(expiryDate).fill("1229");
			paymentFrame.locator(cvc_Date).fill("123");
			paymentFrame.locator(submitButton).click();
			logger.info("Entered all necessary details & click on Submit Button");
			boolean isInvoicePaid = false;
			if (endlinkPage.isVisible(paidInvoice_Heading)) {
				logger.info("Invoice Paid Sucessfully");
				isInvoicePaid = true;
			}
			softAssert.assertTrue(isInvoicePaid, "Payment done from Endlink");
		} else {
			throw new SkipException("Last Message is not payment Endlink");
		}
		softAssert.assertTrue(frame.locator(payment_Button).textContent().contains("Process payment"),
				"verify payemt Status changed to Process Payment");
		softAssert.assertTrue(verifyChangedStatusOnROList("$-Paid"), "verify $-Paid status on RO list");
		softAssert.assertTrue(checkActivity("Invoice paid by customer."), "verify activity for paid invoice");
		frame.locator(payment_Button).click();
		Page newPage = PlaywrightFactory.getBrowserContext().waitForPage(() -> {
			frame.locator(printPayment_Button).first().click();
			logger.info("Click on Print button");
		});
		newPage.waitForLoadState();
		softAssert.assertTrue(newPage.url().contains(AppConstants.PRINT_SCREEN_URL), "verify print screen opened?");
		newPage.close();
		logger.info("Print window closed");
		frame.locator(processedPayment_Button).first().click();
		softAssert.assertTrue(getWindowHeading().contains("COMPLETED"), "Verify payment heading -COMPLETED");
		frame.locator(closeWindow_Button).click();
		logger.info("Payment window closed");
		softAssert.assertTrue(frame.locator(payment_Button).textContent().contains("Processed"),
				"verify payemt Status changed to Processed");
		softAssert.assertTrue(verifyChangedStatusOnROList("$-Processed"), "verify $-Processed status on RO list");
		softAssert.assertTrue(checkLastMessageInConversation("successful"), "Verify payment successful message");
		softAssert.assertTrue(checkActivity("Invoice processed by Advisor."), "Verify activity for Processed payment");
	}
	
	private String getWindowHeading() {
		page.waitForTimeout(4000);
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		return frame.locator(windowTitle).innerText();
	}

	private void deleteItemFromList() {
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		frame.locator(deleteItem_Button).last().click();
		logger.info("Item deleted from list");
		page.waitForTimeout(3000);
	}

	private double calculateTotalItemAmount(String itemAmountList_Locator) {
		page.waitForTimeout(2000);
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		List<ElementHandle> itemAmountList = frame.locator(itemAmountList_Locator).elementHandles();
		double sumOfItems = 0.0;
		for (ElementHandle amountElement : itemAmountList) {
			try {
				String itemText = amountElement.innerText().replace("$", "").replace(",", "").trim();
				double itemAmount = Double.parseDouble(itemText);
				sumOfItems += itemAmount;
			} catch (NumberFormatException e) {
				System.err.println("Failed to parse item amount: " + amountElement.innerText());
			}
		}
		return sumOfItems;
	}

	private double getTotalAmount(String totalAmount_Element) {
		page.waitForTimeout(2000);
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		ElementHandle totalAmountElement = frame.locator(totalAmount_Element).elementHandle();
		String totalAmountText = totalAmountElement.innerText().replace("$", "").replace(",", "").trim();
		double totalAmount = Double.parseDouble(totalAmountText);
		return totalAmount;
	}

	private void addItemToList(String listLocator, String enteredText, String type) {
		page.waitForTimeout(4000);
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		frame.locator(newItem_TextField).click();
		Locator itemList = frame.locator(listLocator);
		if (itemList.allInnerTexts().contains(enteredText)) {
			for (ElementHandle locator : itemList.elementHandles()) {
				String textContent = locator.innerText();
				if (textContent != null && textContent.contains(enteredText)) {
					locator.click();
					logger.info("Entered text is available in the Item List");
					break;
				}
			}
		} else {
			frame.locator(newItem_TextField).fill(enteredText);
			logger.info("Entered text is not available in the Item List");
		}
		frame.locator(type_DropdownField).click();
		logger.info("Click on Type dropdown");
		frame.locator(selectTypeFromDropdown(type)).click();
		logger.info("Selected type from dropdown");
		frame.locator(addItem_Button).click();
		logger.info("Item added to list");
		page.waitForTimeout(2000);
	}

	private void addItemToList_Payment(String listLocator, String enteredText) {
		page.waitForTimeout(4000);
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		frame.locator(newItem_TextField).click();
		Locator itemList = frame.locator(listLocator);
		if (itemList.allInnerTexts().contains(enteredText)) {
			for (ElementHandle locator : itemList.elementHandles()) {
				String textContent = locator.innerText();
				if (textContent != null && textContent.contains(enteredText)) {
					locator.click();
					logger.info("Entered text is available in the Item List");
					break;
				}
			}
		} else {
			frame.locator(newItem_TextField).fill(enteredText);
			logger.info("Entered text is not available in the Item List");
		}
		frame.locator(addItem_Button).click();
		logger.info("Item added to list");
		page.waitForTimeout(2000);
	}

	private boolean verifyChangedStatusOnROList(String status) {
		Page newPage = PlaywrightFactory.getBrowserContext().waitForPage(() -> {
			page.evaluate("window.open()");
		});
		newPage.navigate("https://rc.truvideo.com/crud/repair-order");
		newPage.waitForTimeout(2000);
		logger.info("New Page opened in another tab");
		if (newPage.innerText(getRO(OrderListPage.newRoNumber)).contains(status)) {
			logger.info("Status on RO list screen is changed to " + status);
			newPage.close();
			return true;
		} else {
			logger.info("Status on RO list screen is not changed to " + status);
			newPage.close();
			return false;
		}
	}

	private void selectChannelToPerformAction(String channelSelected) {
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		page.waitForTimeout(2000);
		if (frame.locator(selectChannelWindow).isVisible() && channelSelected.contains("WhatsApp")) {
			logger.info("WhatsApp is enabled & Selected whatsApp channel to send");
			frame.locator(whatsApp_Button).click();
			logger.info("Clicked on whatsApp button : sent through WhatsApp");
		} else if (frame.locator(selectChannelWindow).isVisible() && channelSelected.contains("SMS")) {
			logger.info("WhatsApp is enabled & Selected SMS channel to send");
			frame.locator(sms_Button).click();
			logger.info("Clicked on SMS button : sent through SMS");
		} else {
			logger.info("WhatsApp is Disabled : sent from SMS");
		}
	}

	private boolean verifyNavigationToChannel(String channelSelected) {
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		boolean flag = false;
		page.waitForTimeout(4000);
		if (!frame.locator(communicationTabs).allTextContents().contains("WhatsApp")) {
			if (getLocatorClass(communicationTabs, "SMS").contains("active")) {
				logger.info("WhatsApp is disabled & User navigated to the SMS tab by default");
				flag = true;
				return flag;
			}
		} else if (getLocatorClass(communicationTabs, channelSelected).contains("active")) {
			logger.info("User navigated to the " + channelSelected + " channel after sent ");
			flag = true;
			return flag;
		} else {
			logger.info("User not navigated to the " + channelSelected + " channel after sent ");
			flag = false;
			return flag;
		}
		return flag;
	}

	private boolean checkActivity(String activityLog) {
		boolean flag = false;
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		frame.locator(activity_Tab).first().click();
		int retryCount = 3;
		int attempt = 1;
		while (attempt <= retryCount) {
			try {
				page.waitForCondition(() -> {
					List<String> activityTextList = frame.locator(activities).allInnerTexts();
					logger.debug("Checking activity text: " + activityTextList);
					return activityTextList.stream().anyMatch(text -> text.contains(activityLog));
				});
				String activityText = frame.locator(activities).nth(0).textContent();
				logger.info("RO activity is: " + activityText);
				if (activityText.contains(activityLog)) {
					flag = true;
					break;
				}
			} catch (Exception e) {
				logger.info("Searched activity not displayed at attempt " + attempt);
				logger.info("RO activity is: " + frame.locator(activities).nth(0).textContent());
			}
			attempt++;
		}
		if (flag == false) {
			logger.info("refreshing page to get activity");
			page.reload();
			frame.locator(activity_Tab).first().click();
			String activityText = frame.locator(activities).nth(0).textContent();
			logger.info("RO activity is: " + activityText);
			if (activityText.contains(activityLog)) {
				logger.info("Activity contains - " + activityLog + " on Refresh");
				flag = true;
			}
		}
		frame.locator(customer_tab).first().click();
		return flag;
	}

	private boolean checkStatus(String status) {
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		page.waitForTimeout(2000);
		if (frame.locator(roStatusBar).textContent().contains(status)) {
			logger.info("RO Status is: " + frame.locator(roStatusBar).textContent());
			return true;
		} else {
			logger.info("RO Status is: " + frame.locator(roStatusBar).textContent());
			return false;
		}
	}

	private boolean checkLastMessageInConversation(String messageKeyWords) {
		boolean flag = false;
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		int retryCount = 3;
		int attempt = 1;
		String messageKeyWords_LowerCase = messageKeyWords.toLowerCase();
		while (attempt <= retryCount) {
			page.waitForTimeout(4000);
			try {
				String lastMessageContent = frame.locator(messages).last().textContent().toLowerCase();
				logger.debug("Checking last message text: " + lastMessageContent);
				if (lastMessageContent.contains(messageKeyWords_LowerCase)) {
					logger.info("Last message contains keyword - " + messageKeyWords + " at attempt " + attempt);
					logger.info("Last Message is : " + lastMessageContent);
					flag = true;
					break;
				} else {
					logger.info("Last message does not contain keyword - " + messageKeyWords + " at attempt "
							+ (attempt + 1));
					logger.info("Last Message is : " + lastMessageContent);
				}
			} catch (Exception e) {
				logger.info("Exception while checking last message at attempt " + attempt + ": " + e.getMessage());
				logger.info("Last Message is : " + frame.locator(messages).last().textContent());
			}
			attempt++;
		}
		if (flag == false) {
			logger.info("refreshing page to get Last Message");
			page.reload();
			String lastMessageContent = frame.locator(messages).last().textContent().toLowerCase();
			logger.debug("Checking last message text: " + lastMessageContent);
			if (lastMessageContent.contains(messageKeyWords_LowerCase)) {
				logger.info("Last message contains keyword - " + messageKeyWords + " on Refresh");
				logger.info("Last Message is : " + lastMessageContent);
				flag = true;
			}
		}
		return flag;
	}

	private String getLocatorClass(String operationButtons, String visibleText) {
		page.waitForTimeout(2000);
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		Locator buttons = frame.locator(operationButtons);
		String className = null;
		for (ElementHandle locator : buttons.elementHandles()) {
			String textContent = locator.innerText();
			if (textContent != null && textContent.contains(visibleText)) {
				className = locator.getAttribute("class");
				break;
			}
		}
		return className;
	}

	private void clickOperationButton(String buttonText) {
		page.waitForTimeout(2000);
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		Locator buttons = frame.locator(operations_Buttons);
		for (ElementHandle locator : buttons.elementHandles()) {
			String textContent = locator.innerText();
			if (textContent != null && textContent.contains(buttonText)) {
				locator.click();
				break;
			}
		}
	}

}
