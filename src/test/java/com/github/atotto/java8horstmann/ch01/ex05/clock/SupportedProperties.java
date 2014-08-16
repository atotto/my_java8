package com.github.atotto.java8horstmann.ch01.ex05.clock;

import java.awt.GraphicsEnvironment;

public class SupportedProperties {

	static private String[] fonts = GraphicsEnvironment
			.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

	static private int[] sizes = { 12, 24, 30, 45 };
	static private String[] colors = { "BLACK", "WHITE", "RED", "BLUE",
			"GREEN", "ORANGE", };

	// Return supported fonts
	public static String[] supportedFonts() {
		// Load System fonts.
		return fonts;
	}

	// Return supported sizes
	public static int[] supportedFontSizes() {
		return sizes;
	}

	// Return supported colors
	public static String[] supportedColors() {
		return colors;
	}

}
