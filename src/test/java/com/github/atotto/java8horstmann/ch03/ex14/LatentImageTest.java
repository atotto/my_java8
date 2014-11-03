package com.github.atotto.java8horstmann.ch03.ex14;

import static com.github.atotto.java8horstmann.ch03.ex13.LatentImageTest.fitColorRange;
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
		ColorTransformer laplacian = (x, y, reader) -> {
			double c = reader.getColor(x + 1, y + 1).getBrightness() * 4
					- reader.getColor(x + 1, y).getBrightness()
					- reader.getColor(x, y + 1).getBrightness()
					- reader.getColor(x + 1, y + 2).getBrightness()
					- reader.getColor(x + 2, y + 1).getBrightness();
			return Color.gray(fitColorRange(c));
		};
		Image finalImage = LatentImage.from(image).filter3x3(laplacian)
				.toImage();

		ImageUtil.assertEquals(
				"/fixture/images/ch03.convolution_laplacianFilter.png",
				finalImage);
	}

	@Test
	public void testLatentImage_with_3x3_MeanFilter() {
		Image image = new Image(getClass().getResource("/images/image01.jpg")
				.toString());
		ColorTransformer mean = (x, y, reader) -> {
			double r = 0.0;
			double g = 0.0;
			double b = 0.0;
			for (int m = y; m < y + 3; m++) {
				for (int n = x; n < x + 3; n++) {
					Color c = reader.getColor(n, m);
					r += c.getRed();
					g += c.getGreen();
					b += c.getBlue();
				}
			}
			return Color.color(r / 9.0, g / 9.0, b / 9.0);
		};
		Image finalImage = LatentImage.from(image).filter3x3(mean).toImage();

		ImageUtil.assertEquals(
				"/fixture/images/ch03.convolution_meanFilter.png", finalImage);
	}

	@Test
	public void testLatentImage_with_mirroringFilter() {
		Image image = new Image(getClass().getResource("/images/image01.jpg")
				.toString());
		int width = (int) image.getWidth();
		ColorTransformer mirroring = (x, y, reader) -> reader.getColor(
				width - x - 1, y);
		Image finalImage = LatentImage.from(image).filter(mirroring).toImage();

		ImageUtil.assertEquals("/fixture/images/ch03.mirroringFilter.png",
				finalImage);
	}
}