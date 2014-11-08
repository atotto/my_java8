package com.github.atotto.java8horstmann.ch03.ex15;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.UnaryOperator;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import com.github.atotto.java8horstmann.ch03.ex14.ColorTransformer;
import com.github.atotto.java8horstmann.ch03.ex14.LazyImage;

public class LatentImage {
	private Image in;
	private List<ColorTransformer> pendingOperations;

	public static LatentImage from(Image in) {
		LatentImage result = new LatentImage();
		result.in = in;
		result.pendingOperations = new ArrayList<>();
		return result;
	}

	LatentImage transform(UnaryOperator<Color> f) {
		pendingOperations.add((x, y, reader) -> f.apply(reader.getColor(x, y)));
		return this;
	}

	LatentImage transform(ColorTransformer f) {
		pendingOperations.add(f);
		return this;
	}

	LatentImage filter(ColorTransformer f) {
		pendingOperations.add(f);
		return this;
	}

	public Image toImage() {
		int n = Runtime.getRuntime().availableProcessors();
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		WritableImage out = new WritableImage(in.getPixelReader(), width,
				height);

		PixelReader reader = in.getPixelReader();
		for (ColorTransformer f : pendingOperations) {
			LazyImage image = new LazyImage(reader, width, height, f);
			reader = image.getPixelReader();
		}

		try {
			final PixelReader latestReader = reader;
			ExecutorService pool = Executors.newCachedThreadPool();
			for (int i = 0; i < n; i++) {
				int fromY = i * height / n;
				int toY = (i + 1) * height / n;
				pool.submit(() -> {
					for (int x = 0; x < width; x++) {
						for (int y = fromY; y < toY; y++) {
							try {
								Color c = latestReader.getColor(x, y);
								out.getPixelWriter().setColor(x, y, c);
							} catch (IndexOutOfBoundsException e) {
								out.getPixelWriter().setColor(x, y,
										Color.TRANSPARENT);
							}
						}
					}
				});
			}
			pool.shutdown();
			pool.awaitTermination(1, TimeUnit.HOURS);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		return out;
	}
}
