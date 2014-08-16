package com.github.atotto.java8horstmann.ch02.ex12;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class AtomicIntegerTest {
	String contents;
	List<String> words;
	int kWORD_SIZE = 12;
	int[] expected = { 1, 1826, 4999, 7637, 6166, 3589, 2203, 1867, 831, 697,
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
		AtomicInteger[] shortWordCounters = new AtomicInteger[kWORD_SIZE];
		for (int i = 0; i < shortWordCounters.length; i++)
			shortWordCounters[i] = new AtomicInteger();

		words.parallelStream().forEach(w -> {
			if (w.length() < kWORD_SIZE) {
				shortWordCounters[w.length()].getAndIncrement();
			}
		});

		for (int i = 0; i < kWORD_SIZE; i++) {
			assertThat("word size:" + i, shortWordCounters[i].get(),
					is(expected[i]));
		}
	}

	@Ignore("This is bad example")
	@Test
	public void testBadCounter_ParallelStream() throws IOException {
		Integer[] shortWordCounters = new Integer[kWORD_SIZE];
		for (int i = 0; i < shortWordCounters.length; i++)
			shortWordCounters[i] = new Integer(0);

		words.parallelStream().forEach(w -> {
			if (w.length() < kWORD_SIZE) {
				// bad increment.
				shortWordCounters[w.length()]++;
			}
		});

		// this assert may fail.
		for (int i = 0; i < kWORD_SIZE; i++) {
			assertThat("word size:" + i, shortWordCounters[i], is(expected[i]));
		}
	}
}
