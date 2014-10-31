package com.github.atotto.java8horstmann.ch04.ex02;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class MyJavaFXProperties {
	private double _height;
	private DoubleProperty height;

	public void setHeight(double value) {
		if (height == null) {
			_height = value;
		} else {
			height.set(value);
		}
	}

	public final double getHeight() {
		if (height == null) {
			return _height;
		} else {
			return height.get();
		}
	}

	public final DoubleProperty heightProperty() {
		if (height == null) {
			height = new SimpleDoubleProperty(_height);
		}
		return height;
	}
}
