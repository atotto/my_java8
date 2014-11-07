package com.github.atotto.java8horstmann.ch03.ex14;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.concurrent.atomic.AtomicLong;

import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.image.WritablePixelFormat;
import javafx.scene.paint.Color;

public class LazyImage {
	private WritableImage image;
	private final ColorTransformer f;
	private boolean[][] evaluatedPixels;

	private PixelReader inReader;
	private PixelReader reader;

	private AtomicLong evaluatedPixelCount = new AtomicLong();
	private final long allPixelNum;

	/**
	 * 
	 * @param reader
	 *            image reader
	 * @param width
	 *            the width of reader
	 * @param height
	 *            the height of reader
	 * @param f
	 *            transform function
	 */
	public LazyImage(PixelReader reader, int width, int height,
			ColorTransformer f) {
		if (reader == null) {
			throw new NullPointerException("URL must not be null");
		}
		if (width <= 0 || height <= 0) {
			throw new IllegalArgumentException(
					"Image dimensions must be positive (w,h > 0)");
		}

		this.inReader = reader;
		this.allPixelNum = width * height;

		this.f = f;
		this.image = new WritableImage(width, height);
		this.evaluatedPixels = new boolean[width][height];
	}

	public boolean isEvaluated() {
		return evaluatedPixelCount.get() == allPixelNum;
	}

	public double evaluateRate() {
		return evaluatedPixelCount.get() / allPixelNum;
	}

	public final PixelReader getPixelReader() {
		if (reader == null) {
			reader = new PixelReader() {
				@Override
				public Color getColor(int x, int y) {
					synchronized (image) {
						if (evaluatedPixels[x][y]) {
							return image.getPixelReader().getColor(x, y);
						}
						evaluatedPixelCount.incrementAndGet();
						evaluatedPixels[x][y] = true;
					}
					Color c = f.apply(x, y, inReader);
					image.getPixelWriter().setColor(x, y, c);
					return c;
				}

				@Override
				public int getArgb(int x, int y) {
					throw new AssertionError("not implemented yet");
				}

				@Override
				public PixelFormat<?> getPixelFormat() {
					throw new AssertionError("not implemented yet");
				}

				@Override
				public <T extends Buffer> void getPixels(int x, int y, int w,
						int h, WritablePixelFormat<T> pixelformat, T buffer,
						int scanlineStride) {
					throw new AssertionError("not implemented yet");
				}

				@Override
				public void getPixels(int x, int y, int w, int h,
						WritablePixelFormat<ByteBuffer> pixelformat,
						byte[] buffer, int offset, int scanlineStride) {
					throw new AssertionError("not implemented yet");
				}

				@Override
				public void getPixels(int x, int y, int w, int h,
						WritablePixelFormat<IntBuffer> pixelformat,
						int[] buffer, int offset, int scanlineStride) {
					throw new AssertionError("not implemented yet");
				}
			};
		}
		return reader;
	}
}
