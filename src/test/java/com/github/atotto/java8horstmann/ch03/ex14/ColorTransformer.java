package com.github.atotto.java8horstmann.ch03.ex14;

import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;

@FunctionalInterface
public interface ColorTransformer {
	Color apply(int x, int y, PixelReader reader);
}
