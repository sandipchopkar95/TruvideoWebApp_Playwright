package com.truvideo.utility;

import java.security.SecureRandom;
import java.util.Random;

public class JavaUtility {

	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    public  String getRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }
    
    public  int getRandomNumber(int length) {
        Random random = new Random();
        int min = (int) Math.pow(10, length - 1); // Minimum value with specified length
        int max = (int) Math.pow(10, length) - 1; // Maximum value with specified length
        return random.nextInt(max - min + 1) + min;
    }
}
