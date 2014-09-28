package com.github.atotto.java8horstmann.ch03.ex08;

import static com.github.atotto.java8horstmann.ch03.ex05.ImageTransformTest.transform;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import org.junit.BeforeClass;
import org.junit.Test;

import com.github.atotto.java8horstmann.ch03.ex05.ColorTransformer;
import com.github.atotto.myutil.javafx.ApplicationTest;
import com.github.atotto.myutil.javafx.ImageUtil;

public class ImageTransformTest {

	@BeforeClass
	public static void setup() {
		ApplicationTest.launch();
	}

	public static ColorTransformer setFrame(Image in, Color color, int width) {
		return (x, y, c) -> {
			if (x < width || x > in.getWidth() - width || y < width
					|| y > in.getHeight() - width) {
				return color;
			} else {
				return c;
			}
		};
	}

	@Test
	public void testImage() {
		String path = getClass().getResource("/images/image01.jpg").toString();
		Image src = new Image(path);
		Image dst = transform(src, setFrame(src, Color.RED, 5));

		ImageUtil.assertEquals("/fixture/images/ch03.frame02.png", dst);
	}
}
