package com.github.atotto.java8horstmann.ch09.ex08;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PointTest {

	static class Point {
		private int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int compareToOriginal(Point other) {
			int diff = Integer.compare(x, other.x); // No risk of overflow
			if (diff != 0)
				return diff;
			return Integer.compare(y, other.y);
		}

		public int compareTo(Point other) {
			long diff = (long) x - other.x;
			if (diff != 0)
				return diff > 0 ? 1 : -1;

			diff = (long) y - other.y;
			if (diff == 0)
				return 0;
			return diff > 0 ? 1 : -1;
		}
	}

	static Point P(int x, int y) {
		return new Point(x, y);
	}

	@Test
	public void testCompareTo() throws Exception {
		assertThat(P(1, 1).compareTo((P(1, 1))), is(0));
		assertThat(P(1, 1).compareTo((P(1, 2))), is(lessThan(0)));
		assertThat(P(2, 1).compareTo((P(1, 1))), is(greaterThan(0)));
	}
}
