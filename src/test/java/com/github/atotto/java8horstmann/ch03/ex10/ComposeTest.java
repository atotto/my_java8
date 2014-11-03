package com.github.atotto.java8horstmann.ch03.ex10;

import static com.github.atotto.java8horstmann.ch03.ex05.ImageTransformTest.transform;

import java.util.function.Function;
import java.util.function.UnaryOperator;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import org.junit.BeforeClass;
import org.junit.Test;

import com.github.atotto.myutil.javafx.ApplicationTest;

public class ComposeTest {

	@BeforeClass
	public static void setup() {
		ApplicationTest.launch();
	}

	@SuppressWarnings("unused")
	@Test
	public void test() {
		String path = getClass().getResource("/images/image01.jpg").toString();
		Image image = new Image(path);

		UnaryOperator<Color> op = Color::brighter;
		// Image finalImage = transform(image, op.compose(Color::grayscale));

		// Error:
		// The method transform(Image, UnaryOperator<Color>) in the type
		// ImageTransformTest is not applicable for the arguments (Image,
		// Function<Color,Color>)

		Image finalImage = transform(image,
				funcToUnaryOp(op.compose(Color::grayscale)));
	}

	public static <T> UnaryOperator<T> funcToUnaryOp(Function<T, T> f) {
		return new UnaryOperator<T>() {
			@Override
			public T apply(T t) {
				return f.apply(t);
			}
		};
	}
}
