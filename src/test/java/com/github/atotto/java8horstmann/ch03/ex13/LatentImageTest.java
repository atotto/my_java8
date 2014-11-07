package com.github.atotto.java8horstmann.ch03.ex13;

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
	public void testLatentImage_with_3x3_LaplacianFilter() {
		Image image = new Image(getClass().getResource("/images/image01.jpg")
				.toString());
		ConvolutionFilter laplacian = (mat) -> {
			double c = mat[1][1].getBrightness() * 4
					- mat[1][0].getBrightness() - mat[0][1].getBrightness()
					- mat[1][2].getBrightness() - mat[2][1].getBrightness();
			return Color.gray(fitColorRange(c));
		};

		Image finalImage = LatentImage.from(image).filter(laplacian).toImage();

		ImageUtil.assertEquals(
				"/fixture/images/ch03.convolution_laplacianFilter.png",
				finalImage);
	}

	public static double fitColorRange(double c) {
		if (c < 0.0) {
			return 0.0;
		}
		if (c > 1.0) {
			return 1.0;
		}
		return c;
	}

	@Test
	public void testLatentImage_with_3x3_MeanFilter() {
		Image image = new Image(getClass().getResource("/images/image01.jpg")
				.toString());
		ConvolutionFilter mean = (mat) -> {
			double r = 0.0;
			double g = 0.0;
			double b = 0.0;
			for (Color[] line : mat) {
				for (Color p : line) {
					r += p.getRed();
					g += p.getGreen();
					b += p.getBlue();
				}
			}
			return Color.color(r / 9.0, g / 9.0, b / 9.0);
		};
		Image finalImage = LatentImage.from(image).filter(mean).toImage();
		ImageUtil.assertEquals(
				"/fixture/images/ch03.convolution_meanFilter.png", finalImage);
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

	@Test
	public void testLatentImage_toImage() {
		Image image = new Image(getClass().getResource("/images/image01.jpg")
				.toString());
		Image finalImage = LatentImage.from(image).toImage();

		ImageUtil.assertEquals("/images/image01.jpg", finalImage);
	}
}