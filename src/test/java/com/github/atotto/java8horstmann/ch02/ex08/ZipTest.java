package com.github.atotto.java8horstmann.ch02.ex08;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

import org.junit.Test;

public class ZipTest {

	public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
		Builder<T> builder = Stream.builder();

		Iterator<T> it1 = first.iterator();
		Iterator<T> it2 = second.iterator();

		while (it1.hasNext() && it2.hasNext()) {
			builder.add(it1.next());
			builder.add(it2.next());
		}

		return builder.build();
	}

	@Test
	public void test() {
		Stream<String> st1 = Stream.of(new String[] { "s", "re", "!!" });
		Stream<String> st2 = Stream.of(new String[] { "t", "am" });

		Stream<String> zipped = zip(st1, st2);
		String message = zipped.reduce("", (x, y) -> x + y);

		assertThat(message, is("stream"));
	}
}
