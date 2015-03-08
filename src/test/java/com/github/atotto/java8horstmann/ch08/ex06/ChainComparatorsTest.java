package com.github.atotto.java8horstmann.ch08.ex06;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Comparator;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

import org.junit.Test;

public class ChainComparatorsTest {

	public static int compare(Point2D a, Point2D b) {
		return Comparator.comparing(Point2D::getX).thenComparing(Point2D::getY)
				.compare(a, b);
	}

	// Comparing rectangle size
	public static int compare(Rectangle2D a, Rectangle2D b) {
		return Comparator.comparing(Rectangle2D::getWidth)
				.thenComparing(Rectangle2D::getHeight).compare(a, b);
	}

	private static Point2D P(double x, double y) {
		return new Point2D(x, y);
	}

	private static Rectangle2D R(double minX, double minY, double width,
			double height) {
		return new Rectangle2D(minX, minY, width, height);
	}

	@Test
	public void testPoint2DTotalOrdering() {
		assertThat(compare(P(1, 1), P(1, 1)), is(0));

		assertThat(compare(P(1, 1), P(1, 2)), is(-1));
		assertThat(compare(P(1, 1), P(2, 1)), is(-1));
		assertThat(compare(P(1, 1), P(2, 2)), is(-1));

		assertThat(compare(P(1, 2), P(1, 1)), is(1));
		assertThat(compare(P(1, 2), P(1, 1)), is(1));
		assertThat(compare(P(2, 2), P(1, 1)), is(1));
	}

	@Test
	public void testRectangle2DTotalOrdering() {
		assertThat(compare(R(0, 0, 1, 1), R(0, 0, 1, 1)), is(0));
		assertThat(compare(R(0, 0, 1, 1), R(1, 1, 1, 1)), is(0));

		assertThat(compare(R(0, 0, 1, 1), R(0, 0, 1, 2)), is(-1));
		assertThat(compare(R(0, 0, 1, 1), R(0, 0, 2, 1)), is(-1));
		assertThat(compare(R(0, 0, 1, 1), R(0, 0, 2, 2)), is(-1));

		assertThat(compare(R(0, 0, 1, 2), R(0, 0, 1, 1)), is(1));
		assertThat(compare(R(0, 0, 2, 1), R(0, 0, 1, 1)), is(1));
		assertThat(compare(R(0, 0, 2, 2), R(0, 0, 1, 1)), is(1));
	}
}
