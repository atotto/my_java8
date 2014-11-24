package com.github.atotto.java8horstmann.ch02;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.stream.Stream;

import org.junit.Test;

public class SpliteratorExample {

	@Test
	public void test() {
		Stream<Object> s = Stream.empty();
		long l = s.spliterator().estimateSize();
		assertThat(l, is(0));
	}
}
