package com.github.atotto.java8horstmann.ch03.ex24;

import java.util.function.Function;

public class Pair<T> {
	public T first;
	public T second;

	public Pair(T first, T second) {
		this.first = first;
		this.second = second;
	}

	public static <T, U> U flatMap(Pair<T> pair, Function<Pair<T>, U> function) {
		return function.apply(pair); // Cannot define flatMap method for Pair<T>.
	}
}
