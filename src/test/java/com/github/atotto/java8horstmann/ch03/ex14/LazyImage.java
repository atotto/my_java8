package com.github.atotto.java8horstmann.ch03.ex14;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritablePixelFormat;
import javafx.scene.paint.Color;

public class LazyImage {
	private Color[] image;
	private Object[] lock;
	private final int width, height;
	private final int pixelNum;

	private final ColorTransformer f;

	private PixelReader in;
	private PixelReader reader;

	private boolean evaluated = false;
	private AtomicLong evaluatedPixelCount = new AtomicLong();

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
		this.width = width;
		this.height = height;
		this.pixelNum = width * height;

		this.in = reader;

		this.f = f;
		this.image = new Color[width * height];
		this.lock = new Object[pixelNum];
		Arrays.fill(lock, new Object());
	}

	public boolean isEvaluated() {
		if (evaluated == false) {
			evaluated = evaluatedPixelCount.get() == pixelNum;
		}
		return evaluated;
	}

	public double evaluateRate() {
		return evaluatedPixelCount.get() / pixelNum;
	}

	public final PixelReader getPixelReader() {
		if (reader == null) {
			reader = new PixelReader() {
				@Override
				public Color getColor(int x, int y) {
					if (x < 0 || y < 0 || x >= width || y >= height) {
						throw new IndexOutOfBoundsException("(x,y)=" + x + y);
					}
					int index = x + y * width;
					if (image[index] != null) {
						return image[index];
					}
					synchronized (lock[index]) {
						if (image[index] != null) {
							return image[index];
						}
						evaluatedPixelCount.incrementAndGet();
						Color c = f.apply(x, y, in);
						image[index] = c;
						return c;
					}
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
