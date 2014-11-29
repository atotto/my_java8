package com.github.atotto.java8horstmann.ch05.ex07;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class TimeIntervalTest {

	@Test
	public void testOverlap() {
		TestCase[] tests = new TestCase[] {
				testcase(TimeInterval.of("11:00", "12:00"),
						TimeInterval.of("10:00", "11:00"), false),
				testcase(TimeInterval.of("11:00", "12:00"),
						TimeInterval.of("10:00", "11:01"), true),
				testcase(TimeInterval.of("11:00", "12:00"),
						TimeInterval.of("12:00", "13:00"), false),
				testcase(TimeInterval.of("11:00", "12:00"),
						TimeInterval.of("11:59", "13:00"), true),
				testcase(TimeInterval.of("11:00", "12:00"),
						TimeInterval.of("11:00", "12:00"), true),
				testcase(TimeInterval.of("11:00", "12:00"),
						TimeInterval.of("11:10", "11:50"), true),
				testcase(TimeInterval.of("11:00", "12:00"),
						TimeInterval.of("10:00", "13:00"), true), };

		for (TestCase tt : tests) {
			assertThat(tt.toString(),
					TimeInterval.isOverlap(tt.first, tt.second), is(tt.expect));
		}
	}

	static TestCase testcase(TimeInterval start, TimeInterval end,
			boolean expect) {
		return new TestCase(start, end, expect);
	}

	static class TestCase {
		TimeInterval first;
		TimeInterval second;
		boolean expect;

		public TestCase(TimeInterval first, TimeInterval second, boolean expect) {
			this.first = first;
			this.second = second;
			this.expect = expect;
		}

		@Override
		public String toString() {
			return String.format("[%s] and [%s] should be overlap %b", first,
					second, expect);
		}
	}
}
