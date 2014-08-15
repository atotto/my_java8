package com.github.atotto.java8horstmann.ch02.ex02;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class FilterTest {

	@Test
	public void test() {
		String[] testdata = { "found1", "skip", "found2", "found3", "skip",
				"found4", "skip", "found5", "found6", "skip" };

		List<String> words = Arrays.asList(testdata);
		List<String> log = new ArrayList<>();

		words.stream().filter(w -> {
			log.add(w);
			return w.length() > 4;
		}).limit(5).toArray();

		String[] expectedLog = { "found1", "skip", "found2", "found3", "skip",
				"found4", "skip", "found5" };
		assertThat(log, is(contains(expectedLog)));
	}
}
