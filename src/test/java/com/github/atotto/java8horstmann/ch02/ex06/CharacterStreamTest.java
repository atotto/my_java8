package com.github.atotto.java8horstmann.ch02.ex06;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

public class CharacterStreamTest {

	public static Stream<Character> characterStream(String s) {
		return IntStream.range(0, s.length()).mapToObj(s::charAt);
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
