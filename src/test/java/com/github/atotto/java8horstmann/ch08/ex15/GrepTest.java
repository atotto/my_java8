package com.github.atotto.java8horstmann.ch08.ex15;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import com.github.atotto.myutil.Resource;

public class GrepTest {

	@Test
	public void testGrep() throws IOException {
		Path path = Resource.path("/alice.txt");
		List<String> entries = grep(path, "Alice");

		entries.stream().forEach(entry -> {
			assertThat(entry, is(containsString("Alice")));
		});
	}

	public static List<String> grep(Path path, String regex) throws IOException {
		try (Stream<String> lines = Files.lines(path)) {
			return lines.filter(Pattern.compile(regex).asPredicate()).collect(
					Collectors.toList());
		}
	}
}
