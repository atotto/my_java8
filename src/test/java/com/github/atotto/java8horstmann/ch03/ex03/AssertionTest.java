package com.github.atotto.java8horstmann.ch03.ex03;

import java.util.function.Supplier;

import org.junit.Test;

public class AssertionTest {

	// must add `-ea` in VM arguments.

	@Test(expected = AssertionError.class)
	public void testMyAssertion_with_AssertionError() {
		assertion(() -> false);
	}

	@Test
	public void testMyAssertion() {
		assertion(() -> true);
	}

	@Test(expected = AssertionError.class)
	public void testNormalAssert() throws Exception {
		assert false;
	}

	static void assertion(Supplier<Boolean> condition) {
		if (AssertionTest.class.desiredAssertionStatus() && !condition.get()) {
			throw new AssertionError();
		}
	}
}
