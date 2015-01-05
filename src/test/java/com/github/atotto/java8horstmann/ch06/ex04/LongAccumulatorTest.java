package com.github.atotto.java8horstmann.ch06.ex04;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.concurrent.atomic.LongAccumulator;

import org.junit.Test;

public class LongAccumulatorTest {

	@Test
	public void testMax() {
		LongAccumulator max = new LongAccumulator(Long::max, 1L);

		assertThat(max.get(), is(1L));

		max.accumulate(10);
		assertThat(max.get(), is(10L));

		max.accumulate(-10);
		assertThat(max.get(), is(10L));
	}

	@Test
	public void testMin() {
		LongAccumulator min = new LongAccumulator(Long::min, -1L);

		assertThat(min.get(), is(-1L));

		min.accumulate(10);
		assertThat(min.get(), is(-1L));

		min.accumulate(-10);
		assertThat(min.get(), is(-10L));
	}
}
