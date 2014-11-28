package com.github.atotto.java8horstmann.ch02.ex09;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MergeStreamTest {

	public static <T> ArrayList<T> mergeStream1(Stream<ArrayList<T>> stream) {
		// T reduce(T identity, BinaryOperator<T> accumulator)
		return stream.sequential().reduce(new ArrayList<T>(),
				(list1, list2) -> {
					list1.addAll(list2);
					return list1;
				});
	}

	public static <T> ArrayList<T> mergeStream2(Stream<ArrayList<T>> stream) {
		// Optional<T> reduce(BinaryOperator<T> accumulator)
		return stream.sequential().reduce((list1, list2) -> {
			ArrayList<T> list = new ArrayList<T>();
			list.addAll(list1);
			list.addAll(list2);
			return list;
		}).orElse(new ArrayList<T>());
	}

	public static <T> ArrayList<T> mergeStream3(Stream<ArrayList<T>> stream) {
		// <U> U reduce(U identity, BiFunction<U,? super T,U> accumulator,
		// BinaryOperator<U> combiner)
		return stream.sequential().reduce(new ArrayList<T>(),
				(total, list) -> {
					total.addAll(list);
					return total;
				}, (list1, list2) -> {
					return list1;
				});
	}

	Stream<ArrayList<String>> stream = null;
	String[] expected = { "a", "b", "c", "d", "e", "f" };
	ArrayList<String> test1;
	ArrayList<String> test2;
	ArrayList<String> test3;

	@Before
	public void setUp() {
		test1 = new ArrayList<>(Arrays.asList("a", "b"));
		test2 = new ArrayList<>(Arrays.asList("c", "d"));
		test3 = new ArrayList<>(Arrays.asList("e", "f"));

		stream = Stream.of(test1, test2, test3).parallel();
	}

	@After
	public void teadDown() {
		assertThat(test1, is(Arrays.asList("a", "b")));
		assertThat(test2, is(Arrays.asList("c", "d")));
		assertThat(test3, is(Arrays.asList("e", "f")));
	}

	@Test
	public void testMeageStream1() {
		ArrayList<String> joined = mergeStream1(stream);
		assertThat(joined, is(contains(expected)));
	}

	@Test
	public void testMeageStream2() {
		ArrayList<String> joined = mergeStream2(stream);
		assertThat(joined, is(contains(expected)));
	}

	@Test
	public void testMeageStream3() {
		ArrayList<String> joined = mergeStream3(stream);
		assertThat(joined, is(contains(expected)));
	}
}
