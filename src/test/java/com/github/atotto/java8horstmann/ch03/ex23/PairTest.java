package com.github.atotto.java8horstmann.ch03.ex23;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PairTest {

	@Test
	public void testPair() {
		Pair<Integer> pint = new Pair<>(1, 2);
		Pair<Double> pdouble = Pair.map(pint, (i) -> i / 2.0);

		assertThat(pdouble.first, is(0.5));
		assertThat(pdouble.second, is(1.0));
	}
}
