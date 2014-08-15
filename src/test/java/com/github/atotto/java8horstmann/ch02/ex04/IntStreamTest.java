package com.github.atotto.java8horstmann.ch02.ex04;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

@SuppressWarnings("unused")
public class IntStreamTest {

	@Test
	public void testBadIntStream() {
		int[] values = { 1, 4, 9, 16 };
		{
			Stream<int[]> istream = Stream.of(values);
		}
	}

	@Test
	public void testIntStream() throws Exception {
		int[] values = { 1, 4, 9, 16 };
		{
			IntStream istream = Arrays.stream(values);
		}
		{
			IntStream istream = IntStream.of(values);
		}
	}
}
