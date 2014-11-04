package com.github.atotto.java8horstmann.ch03.ex14;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

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
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();

		return filter(f, 0, 0, width, height);
	}

	LatentImage filter3x3(ColorTransformer f) {
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();

		return filter(f, 0, 0, width - 2, height - 2);
	}

	LatentImage filter(ColorTransformer f, int start_x, int start_y, int width,
			int height) {
		this.in = this.toImage();
		this.pendingOperations.clear();

		WritableImage out = new WritableImage(width, height);
		PixelReader reader = in.getPixelReader();
		for (int x = start_x; x < width; x++) {
			for (int y = start_y; y < height; y++) {
				Color c = f.apply(x, y, reader);
				out.getPixelWriter().setColor(x, y, c);
			}
		}
		this.in = out;
		return this;
	}

	public Image toImage() {
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		WritableImage out = new WritableImage(in.getPixelReader(), width,
				height);
		PixelReader reader = in.getPixelReader();
		for (ColorTransformer f : pendingOperations) {
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					Color c = f.apply(x, y, reader);
					out.getPixelWriter().setColor(x, y, c);
				}
			}
			out = new WritableImage(out.getPixelReader(), width, height);
			reader = out.getPixelReader();
		}
		return out;
	}
}
