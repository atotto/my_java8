package com.github.atotto.java8horstmann.ch02.ex10;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import org.junit.Test;

public class AverageTest {

	public static Double bad_average(Stream<Double> stream) {
		AtomicLong count = new AtomicLong(0);
		Double sum = stream.reduce(0.0, (val1, val2) -> {
			count.getAndIncrement();
			return val1 + val2;
		});
		// cannot obtain count() from used stream.
		return sum / count.get();
	}

	public static Double average(Stream<Double> stream) {
		return stream.mapToDouble((val) -> val).average().getAsDouble();
	}

	@Test
	public void testBadAverage() {
		DoubleStream stream = DoubleStream.of(10.0, 0.2, 0.4, 2.4);
		Double ave = bad_average(stream.boxed());

		assertThat(ave, is((10.0 + 0.2 + 0.4 + 2.4) / 4));
	}

	@Test
	public void testAverage() {
		DoubleStream stream = DoubleStream.of(10.0, 0.2, 0.4, 2.4);
		Double ave = average(stream.boxed());

		assertThat(ave, is((10.0 + 0.2 + 0.4 + 2.4) / 4));
	}
}
