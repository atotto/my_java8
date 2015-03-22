package com.github.atotto.java8horstmann.ch09.ex06;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class TacTest {

	public static void tac(String path, String storepath) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get(path),
				StandardCharsets.UTF_8);
		reverse(lines);
		Files.write(Paths.get(storepath), lines, StandardCharsets.UTF_8,
				StandardOpenOption.CREATE);
	}

	private static void reverse(List<String> lines) {
		Collections.reverse(lines);
	}

	@Test
	public void testReverse() throws Exception {
		List<String> lines = Arrays.asList(new String[] { "hello", "bye!" });
		reverse(lines);

		assertThat(lines.toArray(), is(new String[] { "bye!", "hello" }));
	}
}
