package com.github.atotto.java8horstmann.ch09.ex09;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class LabeledPointTest {

	@Test
	public void testEquals() {
		assertThat(LP("pt1", 1, 1), is(LP("pt1", 1, 1)));
		assertThat(LP("pt1", 1, 1), is(not(LP("pt2", 1, 1))));
		assertThat(LP("pt1", 1, 1), is(not(LP("pt1", 1, 2))));
		assertThat(LP("pt1", 2, 1), is(not(LP("pt1", 1, 1))));
	}

	@Test
	public void testHashCode() throws Exception {
		assertThat(LP("pt1", 1, 1).hashCode(), is(LP("pt1", 1, 1).hashCode()));
		assertThat(LP("pt1", 1, 1).hashCode(), is(not(LP("pt2", 1, 1)
				.hashCode())));
		assertThat(LP("pt1", 1, 1).hashCode(), is(not(LP("pt1", 1, 2)
				.hashCode())));
		assertThat(LP("pt1", 2, 1).hashCode(), is(not(LP("pt1", 1, 1)
				.hashCode())));
	}

	static LabeledPoint LP(String label, int x, int y) {
		return new LabeledPoint(label, x, y);
	}
}
