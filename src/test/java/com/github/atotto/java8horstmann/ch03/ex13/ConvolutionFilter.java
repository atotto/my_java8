package com.github.atotto.java8horstmann.ch03.ex13;

import javafx.scene.paint.Color;

@FunctionalInterface
public interface ConvolutionFilter {
	Color apply(Color[][] mat);
}
