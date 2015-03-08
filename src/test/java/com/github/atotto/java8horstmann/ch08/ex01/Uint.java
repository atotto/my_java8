package com.github.atotto.java8horstmann.ch08.ex01;

public class Uint {
	/**
	 * 2<sup>32</sup>-1.
	 */
	public static final int MAX_VALUE = 0xffffffff;
	public static final int MIN_VALUE = 0x00000000;

	public static long add(int x, int y) {
		return Integer.toUnsignedLong(x) + Integer.toUnsignedLong(y);
	}

	public static long sub(int x, int y) {
		return Integer.toUnsignedLong(x) - Integer.toUnsignedLong(y);
	}

	public static int div(int x, int y) {
		return Integer.divideUnsigned(x, y);
	}

	public static int compare(int x, int y) {
		return Integer.compare(x, y);
	}
}
