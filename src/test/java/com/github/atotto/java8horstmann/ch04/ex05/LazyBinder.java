package com.github.atotto.java8horstmann.ch04.ex05;

import java.util.function.BiFunction;
import java.util.function.Function;

import javafx.beans.InvalidationListener;
import javafx.beans.property.ObjectPropertyBase;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class LazyBinder {

	public static <T, R> ObservableValue<R> observe(Function<T, R> f,
			ObservableValue<T> t) {
		return new ObjectPropertyBase<R>() {
			@Override
			public R getValue() {
				return f.apply(t.getValue());
			}

			@SuppressWarnings("unchecked")
			@Override
			public void addListener(ChangeListener<? super R> l) {
				t.addListener((ChangeListener<? super T>) l);
			}

			@SuppressWarnings("unchecked")
			@Override
			public void removeListener(ChangeListener<? super R> l) {
				t.removeListener((ChangeListener<? super T>) l);
			}

			@Override
			public void addListener(InvalidationListener l) {
				t.addListener(l);
			}

			@Override
			public void removeListener(InvalidationListener l) {
				t.removeListener(l);
			}

			@Override
			public Object getBean() {
				return null;
			}

			@Override
			public String getName() {
				return "observe";
			}
		};
	}

	public static <T, U, R> ObservableValue<R> observe(BiFunction<T, U, R> f,
			ObservableValue<T> t, ObservableValue<U> u) {
		return new ObjectPropertyBase<R>(f.apply(t.getValue(), u.getValue())) {
			@Override
			public R getValue() {
				return f.apply(t.getValue(), u.getValue());
			}

			@SuppressWarnings("unchecked")
			@Override
			public void addListener(ChangeListener<? super R> l) {
				t.addListener((ChangeListener<? super T>) l);
				u.addListener((ChangeListener<? super U>) l);
			}

			@SuppressWarnings("unchecked")
			@Override
			public void removeListener(ChangeListener<? super R> l) {
				t.removeListener((ChangeListener<? super T>) l);
				u.removeListener((ChangeListener<? super U>) l);
			}

			@Override
			public void addListener(InvalidationListener l) {
				t.addListener(l);
				u.addListener(l);
			}

			@Override
			public void removeListener(InvalidationListener l) {
				t.removeListener(l);
				u.removeListener(l);
			}

			@Override
			public Object getBean() {
				return null;
			}

			@Override
			public String getName() {
				return "observe";
			}
		};
	}
}
