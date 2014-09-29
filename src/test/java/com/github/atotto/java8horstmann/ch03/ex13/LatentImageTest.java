package com.github.atotto.java8horstmann.ch03.ex13;

import javafx.scene.image.Image;

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
	public void testLatentImage_with_3x3_LaplacianFilter() {
		Image image = new Image(getClass().getResource("/images/image01.jpg")
				.toString());
		Image finalImage = LatentImage
				.from(image)
				.filter((mat) -> {
					double c = mat[1][1] * 4 - mat[1][0] - mat[0][1]
							- mat[1][2] - mat[2][1];
					return c;
				}).toImage();

		ImageUtil.assertEquals(
				"/fixture/images/ch03.convolution_laplacianFilter.png",
				finalImage);
	}

	@Test
	public void testLatentImage_with_3x3_MeanFilter() {
		Image image = new Image(getClass().getResource("/images/image01.jpg")
				.toString());
		Image finalImage = LatentImage.from(image).filter((mat) -> {
			double c = 0.0;
			for (double[] line : mat) {
				for (double p : line) {
					c += p;
				}
			}
			return c / 9.0;
		}).toImage();

		ImageUtil.assertEquals(
				"/fixture/images/ch03.convolution_meanFilter.png", finalImage);
	}
}