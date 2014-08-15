package com.github.atotto.java8horstmann.ch02.ex06;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

public class CharacterStreamTest {

	public static Stream<Character> characterStream(String s) {
		List<Character> result = new ArrayList<>();
		for (char c : s.toCharArray())
			result.add(c);
		return result.stream();
	}

	@Test
	public void testCharacterStream() {
		Stream<String> song = Stream.of("row", "row", "row", "your", "boat",
				"gently", "down", "the", "stream");

		Stream<Character> letters = song.flatMap(w -> characterStream(w));

		assertThat(letters.toArray(Character[]::new), is(new Character[] { 'r',
				'o', 'w', 'r', 'o', 'w', 'r', 'o', 'w', 'y', 'o', 'u', 'r',
				'b', 'o', 'a', 't', 'g', 'e', 'n', 't', 'l', 'y', 'd', 'o',
				'w', 'n', 't', 'h', 'e', 's', 't', 'r', 'e', 'a', 'm' }));
	}
}
