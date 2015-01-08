package com.github.atotto.java8horstmann.ch06.ex09;

import java.util.Arrays;

public class Fibonacci {

	static final int[][] F = new int[][] { { 1, 1 }, { 1, 0 } };

	public static int fibonacci(int n) {
		if (n < 0) {
			throw new IllegalArgumentException("Negative n value");
		}
		if (n == 0) {
			return 0;
		}
		int[][][] array = new int[n][2][2];
		Arrays.parallelSetAll(array, i -> F);
		Arrays.parallelPrefix(array, (m1, m2) -> Matrix.multiply2x2(m1, m2));
		return array[n - 1][0][1];
	}
}
