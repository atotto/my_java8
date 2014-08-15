package com.github.atotto.java8horstmann.ch02.ex07;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.stream.Stream;

import org.junit.Ignore;
import org.junit.Test;

public class IsFiniteTest {

	public static <T> boolean isFinite(Stream<T> stream) {
		// We cannot check the infinite stream.
		return false;
	}

	@Ignore("isFinite method is impossible to implement.")
	@Test
	public void testInfiniteStream() {
		Stream<Double> randoms = Stream.generate(Math::random);
		boolean actual = isFinite(randoms);
		assertThat(actual, is(false));
	}

	@Ignore("isFinite method is impossible to implement.")
	@Test
	public void testFiniteStream() {
		Stream<Double> randoms = Stream.generate(Math::random).limit(10);
		boolean actual = isFinite(randoms);
		assertThat(actual, is(true));
	}

}
