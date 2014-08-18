package com.github.atotto.java8horstmann.ch02.ex05;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Random;
import java.util.stream.Stream;

import org.junit.Test;

public class RandomStreamTest {

	// Liner Congruential Generator
	public static Stream<Long> myRandomStream(long a, long c, long m, long seed) {
		return Stream.iterate(seed, xn -> (a * xn + c) % m);
	}

	@Test
	public void testRandomStream() {
		long a = 25214903917L; // = 0x5DEECE66DL
		long c = 11L; // = 0xBL
		long m = 1L << 48;

		Stream<Long> stream = myRandomStream(a, c, m, 0L);
		Long[] actual = stream.limit(5).toArray(Long[]::new);

		Long[] expected = { 0L, 11L, 277363943098L, 11718085204285L,
				49720483695876L };

		assertThat(actual, is(expected));
	}

	@Test
	public void testRandom_longs() throws Exception {
		Random r = new Random();
		r.setSeed(0L);
		// r.longs(5).forEach(System.out::println);
		r.longs(5);
	}
}
