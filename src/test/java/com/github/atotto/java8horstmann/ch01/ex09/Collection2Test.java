package com.github.atotto.java8horstmann.ch01.ex09;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.junit.Test;

public class Collection2Test {

	public interface Collection2<E> extends Collection<E> {
		default void forEachIf(Consumer<E> action, Predicate<E> filter) {
			this.forEach(act -> {
				if (filter.test(act)) {
					action.accept(act);
				}
			});
		}
	}

	@SuppressWarnings("serial")
	public class ArrayList2<E> extends ArrayList<E> implements Collection2<E> {
		public ArrayList2(Collection<? extends E> c) {
			super(c);
		}
	}

	@Test
	public void test() {
		String[] names = { "Peter", "Paul", "Mary" };
		Collection2<String> c = new ArrayList2<String>(Arrays.asList(names));

		List<String> longnames = Collections
				.synchronizedList(new ArrayList<>());

		c.forEachIf(longnames::add, (name) -> name.length() > 4);

		assertThat(1, is(longnames.size()));
		assertThat("Peter", is(longnames.get(0)));
	}
}
