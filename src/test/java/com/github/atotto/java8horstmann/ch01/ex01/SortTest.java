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

		Thread expect = Thread.currentThread();

		Arrays.sort(strings, (first, second) -> {
			Thread actual = Thread.currentThread();
			assertThat(actual, is(expect));

			return Integer.compare(first.length(), second.length());
		});
	}

	@Test
	public void testRunnable() throws Exception {
		Thread expect = Thread.currentThread();

		Runnable runner = () -> {
			Thread actual = Thread.currentThread();
			assertThat(actual, is(not(expect)));
		};

		Thread th = new Thread(runner);
		th.start();
		th.join();
	}
}
