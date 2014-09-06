package com.github.atotto.java8horstmann.ch03.ex07;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.util.Comparator;

import org.junit.Test;

public class StringComparatorTest {

	@Test
	public void testNormal() {
		Comparator<String> comp = (new StringComparatorBuilder()).comparator();
		assertThat(comp.compare("a", "a"), is(0));
		assertThat(comp.compare("a", "b"), is(-1));
		assertThat(comp.compare("b", "a"), is(1));
	}

	@Test
	public void testReverse() {
		Comparator<String> comp = (new StringComparatorBuilder()).reversed()
				.comparator();
		assertThat(comp.compare("a", "a"), is(0));
		assertThat(comp.compare("a", "b"), is(1));
		assertThat(comp.compare("b", "a"), is(-1));
	}

	@Test
	public void testCaseSensitive() {
		Comparator<String> comp = (new StringComparatorBuilder())
				.caseSensitive().comparator();
		assertThat(comp.compare("a", "A"), is(greaterThan(0)));
		assertThat(comp.compare("A", "a"), is(lessThan(0)));
	}

	@Test
	public void testCaseInsensitive() {
		Comparator<String> comp = (new StringComparatorBuilder()).comparator();
		assertThat(comp.compare("a", "A"), is(0));
		assertThat(comp.compare("A", "a"), is(0));
	}

	@Test
	public void testSpaceSensitive() {
		Comparator<String> comp = (new StringComparatorBuilder())
				.spaceSensitive().comparator();
		assertThat(comp.compare("a a", "aa"), is(not(0)));
		assertThat(comp.compare("aa", "a a"), is(not(0)));
	}

	@Test
	public void testSpaceInsensitive() {
		Comparator<String> comp = (new StringComparatorBuilder()).comparator();
		assertThat(comp.compare("a a", "aa"), is(0));
		assertThat(comp.compare("aa", "a a"), is(0));
	}

	@Test
	public void testCombination() throws Exception {
		Comparator<String> comp = (new StringComparatorBuilder())
				.caseSensitive().spaceSensitive().comparator();
		assertThat(comp.compare("a", "A"), is(not(0)));
		assertThat(comp.compare("aa", "a a"), is(not(0)));
	}
}
