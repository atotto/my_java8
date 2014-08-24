package com.github.atotto.java8horstmann.ch01.ex12;

import java.util.ArrayList;

public class Java8CompatibilityErrorTest {

	class Stream<T> {
	}

	@SuppressWarnings("serial")
	class MyArrayList<T> extends ArrayList<T> {
		// Collectionにあるdefault methodと同じシグニチャを持つメソッドを定義してあるとエラーとなる
		// If the method has the signature as same as default method at the
		// Collection interface, that code become the error.
		/*
		 * Error: Description Resource Path Location Type The return type is
		 * incompatible with Collection<T>.stream()
		 */
		// public Stream<T> stream() {
		// return new Stream<T>();
		// }

		// public Stream<T> parallelStream() {
		// return new Stream<T>();
		// }
	}

	/*
	 * Java SE 7とのバイナリ互換性は基本的にある see:
	 * http://www.oracle.com/technetwork/jp/java/javase/overview/8-compatibility-guide-2156366-ja.html
	 */
}
