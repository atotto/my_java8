package com.github.atotto.java8horstmann.ch08.ex03;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

// gcd = greatest common divisor
public class EuclidGCDTest {

	public static int gcd1(int a, int b) {
		if (b == 0) {
			return Math.abs(a);
		} else {
			return gcd1(b, a % b);
		}
	}

	public static int gcd2(int a, int b) {
		if (b == 0) {
			return Math.abs(a);
		} else {
			return gcd2(b, Math.floorMod(a, b));
		}
	}

	public static int gcd3(int a, int b) {
		if (b == 0) {
			return Math.abs(a);
		} else {
			return gcd3(b, rem(a, b));
		}
	}

	private static int rem(int a, int b) {
		return Math.abs(a % b);
	}

	@Test
	public void test() {
		assertThat(gcd1(1071, 1029), is(21));
		assertThat(gcd2(1071, 1029), is(21));
		assertThat(gcd3(1071, 1029), is(21));

		assertThat(gcd1(27, -3), is(3));
		assertThat(gcd2(27, -3), is(3));
		assertThat(gcd3(27, -3), is(3));
	}
}
