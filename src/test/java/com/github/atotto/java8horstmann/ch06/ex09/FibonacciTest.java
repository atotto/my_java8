package com.github.atotto.java8horstmann.ch06.ex09;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class FibonacciTest {

	@Test
	public void testFibonacci() {
		int[] expect_fibonacci = new int[] { 0, 1, 1, 2, 3, 5, 8, 13, 21, 34,
				55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946 };

		for (int i = 0; i < expect_fibonacci.length; i++) {
			assertThat(Fibonacci.fibonacci(i), is(expect_fibonacci[i]));
		}
	}
}
