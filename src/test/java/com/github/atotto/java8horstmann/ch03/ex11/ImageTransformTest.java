package com.github.atotto.java8horstmann.ch03.ex11;

import static com.github.atotto.java8horstmann.ch03.ex05.ImageTransformTest.setFrame;
import static com.github.atotto.java8horstmann.ch03.ex05.ImageTransformTest.transform;

import java.util.function.UnaryOperator;

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

	public static ColorTransformer compose(ColorTransformer tr1,
			ColorTransformer tr2) {
		return (x, y, c) -> {
			return tr2.apply(x, y, tr1.apply(x, y, c));
		};
	}

	public static ColorTransformer allover(UnaryOperator<Color> op) {
		return (x, y, c) -> op.apply(c);
	}

	@Test
	public void testImage() {
		String path = getClass().getResource("/images/image01.jpg").toString();
		Image src = new Image(path);
		Image dst = transform(src,
				compose(setFrame(src), allover(Color::brighter)));

		ImageUtil.assertEquals("/fixture/images/ch03.frame03.png", dst);
	}
}
