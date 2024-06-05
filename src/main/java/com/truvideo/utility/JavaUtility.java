package com.truvideo.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;
import java.util.Properties;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Page;

public class JavaUtility {
	public static Properties prop;
	public Logger logger = LogManager.getLogger(this.getClass().getName());
	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	private static final SecureRandom RANDOM = new SecureRandom();

	public String getRandomString(int length) {
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			sb.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
		}
		return sb.toString();
	}

	public int getRandomNumber(int length) {
		Random random = new Random();
		int min = (int) Math.pow(10, length - 1); // Minimum value with specified length
		int max = (int) Math.pow(10, length) - 1; // Maximum value with specified length
		return random.nextInt(max - min + 1) + min;
	}

	// this method is used to initialize the properties from config file
	public Properties init_prop() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException f) {
			f.printStackTrace();
		}
		return prop;
	}

	public static String takeScreenshot(Page page) {
		String path = System.getProperty("user.dir" + "/screenshots/" + System.currentTimeMillis() + ".png");
		page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
		return path;
	}
	
	 public boolean isDateInCurrentWeek(String dateFromList) {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a", Locale.ENGLISH);
	        LocalDate givenDate = LocalDate.parse(dateFromList, formatter);
	        LocalDate now = LocalDate.now();
	        LocalDate startOfWeek = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
	        LocalDate endOfWeek = startOfWeek.plusDays(6);
	        return (givenDate.isEqual(startOfWeek) || givenDate.isAfter(startOfWeek)) && 
	               (givenDate.isEqual(endOfWeek) || givenDate.isBefore(endOfWeek));
	    }
	 
	 public boolean isDateInCurrentMonth(String dateFromList) {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a", Locale.ENGLISH);
	        LocalDate givenDate = LocalDate.parse(dateFromList, formatter);
	        LocalDate now = LocalDate.now();
	        return givenDate.getYear() == now.getYear() && givenDate.getMonth() == now.getMonth();
	    }
	 
	 public static boolean isDateInRange(String dateFromList, String fromDateStr, String toDateStr) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a", Locale.ENGLISH);
			LocalDate givenDate = LocalDate.parse(dateFromList, formatter);
			LocalDate fromDate = LocalDate.parse(fromDateStr, formatter);
			LocalDate toDate = LocalDate.parse(toDateStr, formatter);
			return (givenDate.isEqual(fromDate) || givenDate.isAfter(fromDate))
					&& (givenDate.isEqual(toDate) || givenDate.isBefore(toDate));
		}
}
