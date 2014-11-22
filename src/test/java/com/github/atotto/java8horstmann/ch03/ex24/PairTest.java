package com.github.atotto.java8horstmann.ch03.ex24;

import org.junit.Ignore;
import org.junit.Test;

public class PairTest {

	@Test
	@Ignore("Cannot define flatMap method for Pair<T>.")
	public void testPairFlatMap() {
		/*
		Pair<Pair<Integer>> pint = new Pair<>(new Pair<>(1, 2),
				new Pair<>(3, 4));

		Pair<Integer> result = Pair.flatMap(pint, (p) -> ???);

		assertThat(result.first, is(3));
		assertThat(result.second, is(7));
		*/
	}
}
