package com.github.atotto.java8horstmann.ch02.ex09;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

public class ConbineStreamTest {

	public static <T> ArrayList<T> conbineStream1(Stream<ArrayList<T>> stream) {
		// T reduce(T identity, BinaryOperator<T> accumulator)
		return stream.reduce(new ArrayList<T>(), (list1, list2) -> {
			list1.addAll(list2);
			return list1;
		});
	}

	public static <T> ArrayList<T> conbineStream2(Stream<ArrayList<T>> stream) {
		// Optional<T> reduce(BinaryOperator<T> accumulator)
		Optional<ArrayList<T>> op = stream.reduce((list1, list2) -> {
			list1.addAll(list2);
			return list1;
		});

		if (op.isPresent()) {
			return op.get();
		}
		return new ArrayList<T>();
	}

	public static <T> ArrayList<T> conbineStream3(Stream<ArrayList<T>> stream) {
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
		ArrayList<String> test1 = new ArrayList<String>();
		test1.add("a");
		test1.add("b");
		ArrayList<String> test2 = new ArrayList<String>();
		test2.add("c");
		test2.add("d");
		ArrayList<String> test3 = new ArrayList<String>();
		test3.add("e");
		test3.add("f");

		stream = Stream.of(test1, test2, test3);
	}

	@Test
	public void test1() {
		ArrayList<String> conbined = conbineStream1(stream);
		assertThat(conbined, is(contains(expected)));
	}

	@Test
	public void test2() {
		ArrayList<String> conbined = conbineStream2(stream);
		assertThat(conbined, is(contains(expected)));
	}

	@Test
	public void test3() {
		ArrayList<String> conbined = conbineStream3(stream);
		assertThat(conbined, is(contains(expected)));
	}
}
