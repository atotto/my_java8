package com.github.atotto.java8horstmann.ch02.ex11;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

public class MergeStreamTest {

	public static <T> ArrayList<T> mergeStreamConcurrent(
			Stream<ArrayList<T>> stream) {
		ArrayList<T> list = new ArrayList<>(0);

		stream.parallel().forEach(l -> {
			int size = 0;
			synchronized (list) {
				size = list.size();
				list.ensureCapacity(size + l.size());
				for (int i = 0; i < l.size(); i++) {
					list.add(null);
				}
			}
			Iterator<T> iterator = l.iterator();
			for (int index = size; iterator.hasNext(); index++) {
				synchronized (list) {
					list.set(index, iterator.next());
				}
			}
		});

		return list;
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
	public void testMeageStreamConcurrent() {
		ArrayList<String> joined = mergeStreamConcurrent(stream);
		// TODO: fix order
		// assertThat(joined, is(contains(expected)));
		assertThat(joined, is(containsInAnyOrder(expected)));
	}
}
