package com.github.atotto.java8horstmann.ch03.ex18;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.function.Function;

import org.junit.Test;

public class UncheckTest {

	public static <T, R> Function<T, R> unchecked(BiCallable<T, R> f) {
		return (T arg) -> {
			try {
				return f.call(arg);
			} catch (Exception e) {
				throw new RuntimeException(e);
			} catch (Throwable t) {
				throw t;
			}
		};
	}

	@Test
	public void testUncheckBiCallable() {
		Function<String, String> res = unchecked((message) -> {
			return String.format("Hello %s!", message);
		});

		String expected = "Hello world!";
		String actual = res.apply("world");

		assertThat(actual, is(expected));
	}

	@Test
	public void testUncheckBiCallable_with_exception() {
		Function<String, String> res = unchecked((message) -> {
			throw new IllegalStateException(String.format("Hello %s!", message));
		});

		String expected = "Hello world!";
		try {
			res.apply("world");
		} catch (Exception e) {
			assertThat(e.getMessage(), is(containsString(expected)));
		}
	}
}
