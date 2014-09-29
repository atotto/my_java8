package com.github.atotto.java8horstmann.ch03.ex12;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import org.junit.BeforeClass;
import org.junit.Test;

import com.github.atotto.myutil.javafx.ApplicationTest;
import com.github.atotto.myutil.javafx.ImageUtil;

public class LatentImageTest {

	@BeforeClass
	public static void setup() {
		ApplicationTest.launch();
	}

	@Test
	public void testLatentImage_with_UnaryOperatorColor() {
		Image image = new Image(getClass().getResource("/images/image01.jpg")
				.toString());
		Image finalImage = LatentImage.from(image).transform(Color::brighter)
				.transform(Color::grayscale).toImage();

		ImageUtil.assertEquals("/fixture/images/ch03.ex12.png", finalImage);
	}

	@Test
	public void testLatentImage_with_ColorTransformer() {
		Image image = new Image(getClass().getResource("/images/image01.jpg")
				.toString());
		Image finalImage = LatentImage.from(image).transform((x, y, c) -> {
			return c.brighter();
		}).transform((x, y, c) -> {
			return c.grayscale();
		}).toImage();

		ImageUtil.assertEquals("/fixture/images/ch03.ex12.png", finalImage);
	}
}