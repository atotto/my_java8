package com.github.atotto.java8horstmann.ch03.ex06;

import java.util.function.BiFunction;
import java.util.function.UnaryOperator;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import org.junit.BeforeClass;
import org.junit.Test;

import com.github.atotto.myutil.javafx.ApplicationTest;
import com.github.atotto.myutil.javafx.ImageUtil;

public class ImageTransformTest {

	@BeforeClass
	public static void setup() {
		ApplicationTest.launch();
	}

	public static Image transform(Image in, UnaryOperator<Color> f) {
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		WritableImage out = new WritableImage(width, height);

		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				out.getPixelWriter().setColor(x, y,
						f.apply(in.getPixelReader().getColor(x, y)));
		return out;
	}

	public static <T> Image transform(Image in, BiFunction<Color, T, Color> f,
			T arg) {
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		WritableImage out = new WritableImage(width, height);

		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				out.getPixelWriter().setColor(x, y,
						f.apply(in.getPixelReader().getColor(x, y), arg));
		return out;
	}

	public static UnaryOperator<Color> brighten(double factor) {
		return c -> c.deriveColor(0, 1, factor, 1);
	}

	@Test
	public void testImage() {
		String path = getClass().getResource("/images/image01.jpg").toString();
		Image src = new Image(path);
		Image dst = transform(src,
				(c, factor) -> c.deriveColor(0, 1, factor, 1), 1.2);

		ImageUtil.assertEquals("/fixture/images/ch03.brighten.png", dst);

		Image expected = transform(src, brighten(1.2));
		ImageUtil.assertEquals(expected, dst);
	}
}
