package com.github.atotto.java8horstmann.ch03.ex14;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import org.junit.Test;

public class LazyImageTest {

	@Test
	public void testNewLazyImage() {
		int width = 3;
		int height = 3;
		WritableImage src = new WritableImage(width, height);
		PixelWriter writer = src.getPixelWriter();
		writer.setColor(0, 0, Color.RED);
		writer.setColor(2, 0, Color.GREEN);

		ColorTransformer mirroring = (x, y, reader) -> reader.getColor(width
				- x - 1, y);

		LazyImage image = new LazyImage(src.getPixelReader(), width, height,
				mirroring);
		PixelReader reader = image.getPixelReader();

		assertThat(reader.getColor(0, 0), is(Color.GREEN));
		assertThat(reader.getColor(2, 0), is(Color.RED));

		assertThat(image.isEvaluated(), is(false));
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				reader.getColor(x, y);
			}
		}
		assertThat(image.isEvaluated(), is(true));
	}
}
