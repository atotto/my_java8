package com.github.atotto.java8horstmann.ch04.ex03;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import javafx.beans.property.DoubleProperty;

import org.junit.Test;

public class MyJavaFXPropertiesTest {

	@Test
	public void test_default_value() {
		// prepare
		MyJavaFXProperties props = new MyJavaFXProperties();

		// verify
		assertThat(props.getHeight(), is(23.4));
		assertThat(props.heightProperty().get(), is(23.4));
	}

	@Test
	public void test_can_set_default_value() {
		// prepare
		MyJavaFXProperties props = new MyJavaFXProperties();

		// verify
		props.setHeight(23.4);
		assertThat(props.getHeight(), is(23.4));

		props.heightProperty().set(23.4);
		assertThat(props.heightProperty().get(), is(23.4));
	}

	@Test
	public void test_setHeight_can_set_another_value() {
		// prepare
		MyJavaFXProperties props = new MyJavaFXProperties();

		// verify
		props.setHeight(10.0);
		assertThat(props.getHeight(), is(10.0));
	}

	@Test
	public void test_heightProperty_can_set_another_value() {
		// prepare
		MyJavaFXProperties props = new MyJavaFXProperties();

		// prepare
		DoubleProperty height = props.heightProperty();

		// verify
		height.set(10.0);
		assertThat(height.get(), is(10.0));
		assertThat(props.getHeight(), is(10.0));
	}

	@Test
	public void test_can_set_default_value_later() {
		// prepare
		MyJavaFXProperties props = new MyJavaFXProperties();
		DoubleProperty height = props.heightProperty();

		// prepare
		props.setHeight(12.0);

		// verify
		height.set(23.4);
		assertThat(props.getHeight(), is(23.4));
		assertThat(height.get(), is(23.4));

		// prepare
		height.set(11.0);

		// verify
		props.setHeight(23.4);
		assertThat(props.getHeight(), is(23.4));
		assertThat(height.get(), is(23.4));
	}
}
