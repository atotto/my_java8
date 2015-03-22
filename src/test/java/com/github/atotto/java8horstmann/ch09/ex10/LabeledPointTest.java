package com.github.atotto.java8horstmann.ch09.ex10;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class LabeledPointTest {

	@Test
	public void testCompareTo() {
		assertThat(LP("pt1", 1, 1).compareTo((LP("pt1", 1, 1))), is(0));
		assertThat(LP("pt1", 1, 1).compareTo((LP("pt1", 1, 2))),
				is(lessThan(0)));
		assertThat(LP("pt1", 2, 1).compareTo((LP("pt1", 1, 1))),
				is(greaterThan(0)));
	}

	static LabeledPoint LP(String label, int x, int y) {
		return new LabeledPoint(label, x, y);
	}
}
