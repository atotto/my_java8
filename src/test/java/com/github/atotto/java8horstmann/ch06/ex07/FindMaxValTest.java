package com.github.atotto.java8horstmann.ch06.ex07;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

public class FindMaxValTest {

	final long maximal_parallelism = 1; // see ConcurrentHashMap doc

	@Test
	public void testFindMaxVal() throws IOException {
		String contents = new String(Files.readAllBytes(Paths.get(getClass()
				.getResource("/war-and-peace.txt").getFile())),
				StandardCharsets.UTF_8);
		List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

		ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();

		words.forEach(w -> {
			map.put(w, (long) w.length());
		});

		Entry<String, Long> entry = map.reduceEntries(maximal_parallelism, (e1,
				e2) -> e1.getValue() > e2.getValue() ? e1 : e2);

		assertThat(entry.getKey(), is("characteristically"));
	}
}
