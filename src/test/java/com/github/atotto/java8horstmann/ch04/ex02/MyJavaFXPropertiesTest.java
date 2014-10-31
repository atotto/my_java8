package com.github.atotto.java8horstmann.ch04.ex02;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import javafx.beans.property.DoubleProperty;

import org.junit.Test;

public class MyJavaFXPropertiesTest {

	@Test
	public void test_can_set_height() {
		// prepare
		MyJavaFXProperties props = new MyJavaFXProperties();

		// verify
		props.setHeight(10.0);
		assertThat(props.getHeight(), is(10.0));
		assertThat(props.heightProperty().get(), is(10.0));

		// verify
		props.heightProperty().set(11.0);
		assertThat(props.getHeight(), is(11.0));
		assertThat(props.heightProperty().get(), is(11.0));
	}

	@Test
	public void test_can_add_listener() {
		// prepare
		MyJavaFXProperties props = new MyJavaFXProperties();
		DoubleProperty height = props.heightProperty();

		// prepare
		height.addListener((h) -> {
			// verify
			assertThat(height.get(), is(11.0));
			assertThat(props.getHeight(), is(11.0));
		});

		// prepare
		height.set(11.0);

		// verify
		assertThat(height.get(), is(11.0));
		assertThat(props.getHeight(), is(11.0));
	}
}
