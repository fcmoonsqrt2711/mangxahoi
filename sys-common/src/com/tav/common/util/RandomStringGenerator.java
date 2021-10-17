package com.tav.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 
 * @author truongtx5
 * 
 */
public class RandomStringGenerator {

	private static final String CAPTCHA_CHARACTER = "abcdefghijklmnopqrstuvwxyz0123456789";
	private static final String[] PASSWORD_CHARACTER = {
			"abcdefghijklmnopqrstuvwxyz", "ABCDEFGHIJKLMNOPQRSTUVWXYZ",
			"0123456789", "!@#$%^&*()-=_+[]{}\\|;':\",./<>?`~" };
	private static final int DEFAULT_PASSWORD_LENGTH = 8;
	private static final int DEFAULT_CAPTCHA_LENGTH = 6;
	private final Random rn = new Random();
	private int length = 0;

	public RandomStringGenerator() {
	}

	public RandomStringGenerator(int length) {
		if (length <= 0)
			throw new IllegalArgumentException(
					"Length cannot be less than or equal to 0");
		this.length = length;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * Get random CAPTCHA
	 * 
	 * @return
	 */
	public String getRandomCaptcha() {
		int defaultCaptchaLength = this.length > 0 ? this.length
				: DEFAULT_CAPTCHA_LENGTH;
		int captchaLength = defaultCaptchaLength;
		StringBuilder sb = new StringBuilder(captchaLength);
		for (int i = 0; i < captchaLength; i++) {
			sb.append(CAPTCHA_CHARACTER.charAt(rn.nextInt(CAPTCHA_CHARACTER
					.length())));
		}
		return sb.toString();
	}

	/**
	 * Get random password
	 * 
	 * @return
	 */
	public String getRandomPassword() {
		int numCharType = PASSWORD_CHARACTER.length;
		int defaultPasswordLength = this.length > 0 ? this.length
				: DEFAULT_PASSWORD_LENGTH;
		int passwordLength = defaultPasswordLength > numCharType ? defaultPasswordLength
				: numCharType;
		StringBuilder sb = new StringBuilder(passwordLength);
		int sumPos = 0;
		List<Integer> listPos = new ArrayList<Integer>();
		for (int i = 0; i < passwordLength; i++) {
			listPos.add(i);
		}
		Integer[] randPos = new Integer[passwordLength];
		int numPos;
		int pos;
		for (int i = 0; i < numCharType; i++) {
			if (i == numCharType - 1)
				numPos = defaultPasswordLength - sumPos;
			else
				numPos = rn.nextInt(defaultPasswordLength
						- (numCharType - i - 1) - sumPos) + 1;
			sumPos += numPos;
			for (int j = 0; j < numPos; j++) {
				pos = rn.nextInt(listPos.size());
				randPos[listPos.get(pos)] = i;
				listPos.remove(pos);
			}
		}
		for (int i = 0; i < passwordLength; i++) {
			int charType = randPos[i];
			sb.append(PASSWORD_CHARACTER[charType].charAt(rn
					.nextInt(PASSWORD_CHARACTER[charType].length())));
		}
		return sb.toString();
	}

}
