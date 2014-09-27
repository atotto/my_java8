package com.github.atotto.java8horstmann.ch01.ex01;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.Test;

public class SortTest {

	@Test
	public void testComparator() throws Exception {
		String[] strings = "aaaa a aaa aa".split(" ");

		String expect = Thread.currentThread().getName();

		Arrays.sort(strings, (first, second) -> {
			String actual = Thread.currentThread().getName();
			assertThat(actual, is(expect));

			return Integer.compare(first.length(), second.length());
		});
	}

	@Test
	public void testRunnable() throws Exception {
		String expect = Thread.currentThread().getName();

		Runnable runner = () -> {
			String actual = Thread.currentThread().getName();
			assertThat(actual, is(not(expect)));
		};

		Thread th = new Thread(runner);
		th.start();
		th.join();
	}
}
