package com.github.atotto.java8horstmann.ch03.ex19;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

public class BiFunctionTest {

	class Foo<T> {
		Stream<T> stream;

		public Foo(Stream<T> stream) {
			this.stream = stream;
		}

		@SuppressWarnings("unchecked")
		<U> U reduce(U identity,
				BiFunction<? super U, ? super T, U> accumulator,
				BinaryOperator<U> combiner) {
			return stream.reduce(identity,
					(BiFunction<U, ? super T, U>) accumulator, combiner); // cast...
		}
	}
}
