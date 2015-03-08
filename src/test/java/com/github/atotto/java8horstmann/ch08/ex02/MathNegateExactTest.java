package com.github.atotto.java8horstmann.ch08.ex02;

import org.junit.Test;

public class MathNegateExactTest {

	@Test(expected = ArithmeticException.class)
	public void test() {
		Math.negateExact(Integer.MIN_VALUE);
	}
}
