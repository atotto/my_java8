package com.github.atotto.java8horstmann.ch04.ex05;

import static com.github.atotto.java8horstmann.ch04.ex05.LazyBinder.observe;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.BooleanPropertyBase;
import javafx.beans.property.SimpleIntegerProperty;

import org.junit.Test;

public class LazyBinderTest {
	class MyProperties {
		private boolean disabled = false;

		private BooleanProperty disable = new BooleanPropertyBase() {
			@Override
			protected void invalidated() {
				disabled = this.get();
			};

			@Override
			public String getName() {
				return "disable";
			}

			@Override
			public Object getBean() {
				return null;
			}
		};

		public final BooleanProperty disableProperty() {
			return disable;
		}

		public final boolean isDisable() {
			return disabled;
		}
	}

	@Test
	public void test_one_observe() {
		SimpleIntegerProperty val = new SimpleIntegerProperty();

		MyProperties props = new MyProperties();
		props.disableProperty().bind(observe((t) -> t.intValue() >= 100, val));

		val.set(99);
		assertThat(props.isDisable(), is(false));

		val.set(100);
		assertThat(props.isDisable(), is(true));

		val.set(0);
		assertThat(props.isDisable(), is(false));
	}

	@Test
	public void test_two_observe() {
		SimpleIntegerProperty val1 = new SimpleIntegerProperty();
		SimpleIntegerProperty val2 = new SimpleIntegerProperty();

		MyProperties props = new MyProperties();
		props.disableProperty().bind(
				observe((t, u) -> t.intValue() >= 100 || u.intValue() >= 50,
						val1, val2));

		val1.set(99);
		val2.set(49);
		assertThat(props.isDisable(), is(false));

		val1.set(100);
		assertThat(props.isDisable(), is(true));

		val1.set(0);
		assertThat(props.isDisable(), is(false));

		val2.set(50);
		assertThat(props.isDisable(), is(true));

		val2.set(0);
		assertThat(props.isDisable(), is(false));
	}
}
