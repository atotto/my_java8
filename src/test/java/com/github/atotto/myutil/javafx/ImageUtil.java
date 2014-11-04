package com.github.atotto.myutil.javafx;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import javax.imageio.ImageIO;

import org.junit.Assert;

public class ImageUtil {

	/**
	 * Write image to file system as a .png image
	 * 
	 * @param path
	 *            output file path
	 * @param image
	 *            output image
	 */
	public static void savePngImage(String path, Image image) {
		File file = new File(path);
		savePngImage(file, image);
	}

	/**
	 * Write image to file system as a .png image
	 * 
	 * @param file
	 *            output file
	 * @param image
	 *            output image
	 */
	public static void savePngImage(File file, Image image) {
		try {
			ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
		} catch (IOException e) {
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Asserts that two images are equal.
	 * 
	 * @param expectedImagePath
	 * @param actual
	 */
	public static void assertEquals(String expectedImagePath, Image actual) {
		Image expected = new Image(ImageUtil.class.getResource(
				expectedImagePath).toString());
		assertEquals(expected, actual);
	}

	/**
	 * Asserts that two images are equal.
	 * 
	 * @param expected
	 * @param actual
	 */
	public static void assertEquals(Image expected, Image actual) {
		int width = (int) actual.getWidth();
		int height = (int) actual.getHeight();

		assertThat("Image width is not equal", width,
				is((int) expected.getWidth()));
		assertThat("Image height is not equal", height,
				is((int) expected.getHeight()));

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				Color actualColor = actual.getPixelReader().getColor(x, y);
				Color expectedColor = expected.getPixelReader().getColor(x, y);
				assertThat(String.format(
						"Image color(x,y)=(%d,%d) is not equal", x, y),
						actualColor, is(expectedColor));
			}
		}
	}
}
