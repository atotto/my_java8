package com.github.atotto.java8horstmann.ch03.ex18;

@FunctionalInterface
public interface BiCallable<T, R> {
	R call(T t) throws Exception;
}
