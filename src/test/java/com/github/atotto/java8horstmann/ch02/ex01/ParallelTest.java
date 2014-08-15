package com.github.atotto.java8horstmann.ch02.ex01;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.atotto.myutil.Lists;

public class ParallelTest {
	String contents;
	List<String> words;

	@Before
	public void setUp() throws Exception {
		contents = new String(Files.readAllBytes(Paths.get(getClass()
				.getResource("/alice.txt").getFile())), StandardCharsets.UTF_8);
		words = Arrays.asList(contents.split("[\\P{L}]+"));
	}

	@Test
	public void testForLoop() throws IOException {
		long count = 0;
		for (String w : words) {
			if (w.length() > 12)
				count++;
		}

		assertThat(count, is(33L));
	}

	/*
	 * The thread version are too complicated, so we should use the
	 * parallelStream.
	 */
	@Test
	public void testParallelVersionOfForLoop() throws IOException,
			InterruptedException {

		class Counter extends Thread {
			long count = 0;
			List<String> words;

			Counter(List<String> words) {
				this.words = words;
			}

			@Override
			public void run() {
				for (String w : words) {
					if (w.length() > 12)
						count++;
				}
			}
		}

		List<List<String>> pwords = Lists.partition(words, 10000);

		List<Counter> counters = new ArrayList<>();
		for (List<String> words : pwords) {
			counters.add(new Counter(words));
		}

		for (Counter counter : counters) {
			counter.start();
		}

		for (Counter counter : counters) {
			counter.join();
		}

		long count = 0;
		for (Counter counter : counters) {
			count += counter.count;
		}

		assertThat(count, is(33L));
	}

	@Test
	public void testStream() throws IOException {
		long count = words.stream().filter(w -> w.length() > 12).count();

		assertThat(count, is(33L));
	}

	@Test
	public void testParallelVersionOfStream() throws IOException {
		long count = words.parallelStream().filter(w -> w.length() > 12)
				.count();

		assertThat(count, is(33L));
	}
}
