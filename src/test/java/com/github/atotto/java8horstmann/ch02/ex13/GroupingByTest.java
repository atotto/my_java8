package com.github.atotto.java8horstmann.ch02.ex13;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class GroupingByTest {
	String contents;
	List<String> words;
	int kWORD_SIZE = 12;
	long[] expected = { 1, 1826, 4999, 7637, 6166, 3589, 2203, 1867, 831, 697,
			358, 149 };

	@Before
	public void setUp() throws Exception {
		contents = new String(Files.readAllBytes(Paths.get(getClass()
				.getResource("/alice.txt").getFile())), StandardCharsets.UTF_8);
		words = Arrays.asList(contents.split("[\\P{L}]+"));
	}

	@Test
	public void testCounter_ParallelStreamWithAtomicInteger()
			throws IOException {

		Map<Integer, Long> results = words.parallelStream()
				.filter(s -> s.length() < kWORD_SIZE)
				.collect(groupingBy(String::length, counting()));

		for (int i = 0; i < kWORD_SIZE; i++) {
			assertThat("word size:" + i, results.get(i), is(expected[i]));
		}
	}
}
