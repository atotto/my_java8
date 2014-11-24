package com.github.atotto.java8horstmann.ch02;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.stream.Stream;

import org.junit.Test;

public class ReduceExample {

	@Test
	public void test() {
		Integer[] digits = { 3, 1, 4, 1, 5, 9 };
		{
			Stream<Integer> values = Stream.of(digits);
			Integer sum = values.reduce(0, (x, y) -> x + y);
			assertThat(sum, is(23));
		}
		{
			Stream<Integer> values = Stream.of(digits).parallel();
			Integer sum = values
					.reduce(0, (s, w) -> s + w, (s1, s2) -> s1 + s2);
			assertThat(sum, is(23));
		}
	}
}
