package com.github.atotto.java8horstmann.ch03.ex23;

import java.util.function.Function;

public class Pair<T> {
	public T first;
	public T second;

	public Pair(T first, T second) {
		this.first = first;
		this.second = second;
	}

	public static <T, U> Pair<U> map(Pair<T> pair, Function<T, U> function) {
		return new Pair<U>(function.apply(pair.first), function.apply(pair.second));
	}
}
