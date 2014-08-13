package com.github.atotto.java8horstmann.ch01.ex01;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.Test;

public class SortTest {

	@Test
	public void testSortThread() throws Exception {
		String[] strings = "aaaa a aaa aa".split(" ");

		String expect = Thread.currentThread().getName();

		Arrays.sort(strings, (first, second) -> {
			String actual = Thread.currentThread().getName();
			assertThat(actual, is(expect));

			return Integer.compare(first.length(), second.length());
		});
		// Arrays.stream(strings).forEach(System.out::println);
	}
}
