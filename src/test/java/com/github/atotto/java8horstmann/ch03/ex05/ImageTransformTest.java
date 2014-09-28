package com.github.atotto.java8horstmann.ch03.ex05;

import java.io.IOException;
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

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				out.getPixelWriter().setColor(x, y,
						f.apply(in.getPixelReader().getColor(x, y)));
			}
		}
		return out;
	}

	public static Image transform(Image in, ColorTransformer f) {
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		WritableImage out = new WritableImage(width, height);

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				out.getPixelWriter().setColor(x, y,
						f.apply(x, y, in.getPixelReader().getColor(x, y)));
			}
		}
		return out;
	}

	public static ColorTransformer setFrame(Image in) {
		return (x, y, c) -> {
			if (x < 10 || x > in.getWidth() - 10 || y < 10
					|| y > in.getHeight() - 10) {
				return Color.GRAY;
			} else {
				return c;
			}
		};
	}

	@Test
	public void testImage() throws IOException {
		String path = getClass().getResource("/images/image01.jpg").toString();
		Image src = new Image(path);
		Image dst = transform(src, setFrame(src));

		ImageUtil.assertEquals("/fixture/images/ch03.frame01.png", dst);
	}
}
