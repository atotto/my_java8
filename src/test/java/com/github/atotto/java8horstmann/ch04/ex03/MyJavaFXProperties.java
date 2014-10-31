package com.github.atotto.java8horstmann.ch04.ex03;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class MyJavaFXProperties {
	private final double heightDefault = 23.4;
	private DoubleProperty height;

	protected void setHeight(double value) {
		if (height == null) {
			if (heightDefault == value) {
				return;
			}
			height = new SimpleDoubleProperty(value);
		}
		height.set(value);
	}

	public final double getHeight() {
		if (height == null) {
			return heightDefault;
		}
		return height.get();
	}

	public final DoubleProperty heightProperty() {
		if (height == null) {
			height = new SimpleDoubleProperty(heightDefault);
		}
		return height;
	}
}
