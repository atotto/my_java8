package com.github.atotto.java8horstmann.ch03.ex13;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import com.github.atotto.java8horstmann.ch03.ex05.ColorTransformer;

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
		pendingOperations.add((x, y, c) -> {
			return f.apply(c);
		});
		return this;
	}

	LatentImage transform(ColorTransformer f) {
		pendingOperations.add(f);
		return this;
	}

	LatentImage filter(ConvolutionFilter f) {
		this.in = this.toImage();
		this.pendingOperations.clear();

		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		WritableImage out = new WritableImage(width - 2, height - 2);
		for (int x = 0; x < width - 2; x++) {
			for (int y = 0; y < height - 2; y++) {
				double[][] mat_r = new double[3][3];
				double[][] mat_g = new double[3][3];
				double[][] mat_b = new double[3][3];
				for (int m = 0; m < 3; m++) {
					for (int n = 0; n < 3; n++) {
						Color c = in.getPixelReader().getColor(x + m, y + n);
						mat_r[m][n] = c.getRed();
						mat_g[m][n] = c.getGreen();
						mat_b[m][n] = c.getBlue();
					}
				}
				double r = fitColorRange(f.apply(mat_r));
				double g = fitColorRange(f.apply(mat_g));
				double b = fitColorRange(f.apply(mat_b));
				Color c = Color.color(r, g, b);
				out.getPixelWriter().setColor(x, y, c);
			}
		}
		this.in = out;
		return this;
	}

	private double fitColorRange(double c) {
		if (c < 0.0) {
			return 0.0;
		}
		if (c > 1.0) {
			return 1.0;
		}
		return c;
	}

	public Image toImage() {
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		WritableImage out = new WritableImage(width, height);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				Color c = in.getPixelReader().getColor(x, y);
				for (ColorTransformer f : pendingOperations) {
					c = f.apply(x, y, c);
				}
				out.getPixelWriter().setColor(x, y, c);
			}
		}
		return out;
	}
}
