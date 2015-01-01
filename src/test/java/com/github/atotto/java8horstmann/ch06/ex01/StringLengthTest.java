package com.github.atotto.java8horstmann.ch06.ex01;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

import org.junit.Test;

public class StringLengthTest {

	@Test
	public void testUpdateAndGetLongestString() {
		StringLength sl = new StringLength();

		assertThat(sl.getLongestString(), is(""));

		sl.updateString("abc");
		assertThat(sl.getLongestString(), is("abc"));

		sl.updateString("ab");
		assertThat(sl.getLongestString(), is("abc"));
	}

	@Test
	public void testUpdateStringConcurrently() throws Exception {
		String contents = new String(Files.readAllBytes(Paths.get(getClass()
				.getResource("/war-and-peace.txt").getFile())),
				StandardCharsets.UTF_8);
		List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

		StringLength strlength = new StringLength();

		int n = Runtime.getRuntime().availableProcessors();
		ForkJoinPool forkJoinPool = new ForkJoinPool(n);
		forkJoinPool.submit(
				() -> {
					words.parallelStream().unordered()
							.forEach((w) -> strlength.updateString(w));
				}).get();

		assertThat(strlength.getLongestString(), is("characteristically"));
	}
}
