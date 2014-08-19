package com.github.atotto.java8horstmann.ch02.ex09;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

public class MergeStreamTest {

	public static <T> ArrayList<T> mergeStream1(Stream<ArrayList<T>> stream) {
		// T reduce(T identity, BinaryOperator<T> accumulator)
		return stream.reduce(new ArrayList<T>(), (list1, list2) -> {
			list1.addAll(list2);
			return list1;
		});
	}

	public static <T> ArrayList<T> mergeStream2(Stream<ArrayList<T>> stream) {
		// Optional<T> reduce(BinaryOperator<T> accumulator)
		return stream.reduce((list1, list2) -> {
			list1.addAll(list2);
			return list1;
		}).orElse(new ArrayList<T>());
	}

	public static <T> ArrayList<T> mergeStream3(Stream<ArrayList<T>> stream) {
		// <U> U reduce(U identity, BiFunction<U,? super T,U> accumulator,
		// BinaryOperator<U> combiner)
		return stream.reduce(new ArrayList<T>(), (total, list) -> {
			total.addAll(list);
			return total;
		}, (list1, list2) -> {
			list1.addAll(list2);
			return list1;
		});
	}

	Stream<ArrayList<String>> stream = null;
	String[] expected = { "a", "b", "c", "d", "e", "f" };

	@Before
	public void setUp() {
		ArrayList<String> test1 = new ArrayList<>(Arrays.asList("a", "b"));
		ArrayList<String> test2 = new ArrayList<>(Arrays.asList("c", "d"));
		ArrayList<String> test3 = new ArrayList<>(Arrays.asList("e", "f"));

		stream = Stream.of(test1, test2, test3);
	}

	@Test
	public void testMeageStream1() {
		ArrayList<String> conbined = mergeStream1(stream);
		assertThat(conbined, is(contains(expected)));
	}

	@Test
	public void testMeageStream2() {
		ArrayList<String> conbined = mergeStream2(stream);
		assertThat(conbined, is(contains(expected)));
	}

	@Test
	public void testMeageStream3() {
		ArrayList<String> conbined = mergeStream3(stream);
		assertThat(conbined, is(contains(expected)));
	}
}