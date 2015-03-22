package com.github.atotto.java8horstmann.ch08.ex14;

import java.util.Objects;

public class RequireNonNullTest {

	public static void foo(String string) {
		Objects.requireNonNull(string, "string must not be null");
	}
}