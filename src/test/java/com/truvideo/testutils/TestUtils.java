package com.truvideo.testutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.microsoft.playwright.Page;
import com.truvideo.utility.JavaUtility;
import io.appium.java_client.AppiumDriver;

public class TestUtils extends JavaUtility {
static ExtentReports extent;
	
	public static ExtentReports getReporterObject() {
		String path = System.getProperty("user.dir") + "/Reports";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("RC Truvideo Web App");
		reporter.config().setDocumentTitle("Web Automation Test Report");
		reporter.config().setTheme(Theme.STANDARD);
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("5 Exceptions", "RC Truvideo");
		extent.setSystemInfo("Browser", "chrome");
		return extent;
	}

	public String getScreenShotPath(String testCaseName, AppiumDriver driver) throws IOException {
		File source = driver.getScreenshotAs(OutputType.FILE);
		String destinationField = System.getProperty("user.dir") + "/Reports/ScreenShots/" + testCaseName
				+ ".png";
		FileUtils.copyFile(source, new File(destinationField));
		return destinationField;
	}
	
	public String getScreenShotPath(String testCaseName, Page page) throws IOException {
	    String destinationField = System.getProperty("user.dir") + "/Reports/ScreenShots/" + testCaseName + ".png";
	    // Capture the screenshot and save it to the destination path
	    page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(destinationField)));
	    return destinationField;
	}

	public void sendReportToEmail() {
		//try {
			// Create the attachment
			EmailAttachment attachment = new EmailAttachment();
			attachment.setPath(System.getProperty("user.dir") + "/Reports/index.html");
			attachment.setDisposition(EmailAttachment.ATTACHMENT);
			attachment.setDescription("Extent Report");
			
			try {
		        String reportPath;

		        // Determine the path based on the environment
		        if (System.getProperty("os.name").toLowerCase().contains("win")) {
		            reportPath = "D:/TruvideoWeb_Playwright/Reports/Index.html"; // Adjust for Windows
		        } else {
		            reportPath = System.getProperty("user.dir") + "/Reports/Index.html"; // Default relative path
		        }

		        File reportFile = new File(reportPath);
		        if (!reportFile.exists()) {
		            System.err.println("Report file not found at: " + reportPath);
		            return;
		        }

		        System.out.println("Report file found: " + reportPath);
			
			
			// Create the email message
			MultiPartEmail email = new MultiPartEmail();
			email.setHostName("smtp.mail.yahoo.com");
			email.setSmtpPort(465); // port
			email.setTLS(true);
			email.setAuthenticator(new DefaultAuthenticator("sandip.chopkar@5exceptions.com", "scusooxsoohzvlfz")); // pass=AppPass
			email.setSSLOnConnect(true); // Use SSL
			email.setFrom("sandip.chopkar@5exceptions.com");
			email.addTo("rahul.kapse@5exceptions.com");
			email.addTo("sandipchopkar789@gmail.com");
			email.setSubject("Web App Automation Report");
			email.setMsg("Please find the attached Automation Report For Truvideo Web App.");
			// Attach the file
			email.attach(attachment);
			// Send the email
			email.send();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<String> getTicketID(String testCaseName) {
		String filePath = System.getProperty("user.dir") + "/src/test/resources/TestCaseData/ManualTestCases.xlsx";
		String searchValue = testCaseName;
		List<String> tags = new ArrayList<>();
		String testCaseDescription = ""; // To store data from the third column
		try {
			FileInputStream file = new FileInputStream(new File(filePath));
			Workbook workbook = WorkbookFactory.create(file);
			Sheet sheet = workbook.getSheetAt(0); // Reading the first sheet
			// Iterate through each row
			for (Row row : sheet) {
				Cell firstCell = row.getCell(0); // First column
				Cell secondCell = row.getCell(1); // Second column
				Cell thirdCell = row.getCell(2); // Third column
				if (firstCell != null && firstCell.getCellType() == CellType.STRING) {
					String cellValue = firstCell.getStringCellValue();
					if (cellValue.contains(searchValue)) {
						if (secondCell != null && secondCell.getCellType() == CellType.STRING) {
							String[] tagArray = secondCell.getStringCellValue().split(",");
							tags.addAll(Arrays.asList(tagArray));
						}
						if (thirdCell != null && thirdCell.getCellType() == CellType.STRING) {
							testCaseDescription = thirdCell.getStringCellValue();
							tags.add(testCaseDescription);
						}
					}
				}
			}
			workbook.close();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tags;
	}
}
