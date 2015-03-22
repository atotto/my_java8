package com.github.atotto.java8horstmann.ch09.ex10;

public class LabeledPoint implements Comparable<LabeledPoint> {
	private String label;
	private int x;
	private int y;

	public LabeledPoint(String label, int x, int y) {
		this.label = label;
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(LabeledPoint point) {
		int diff = label.compareTo(point.label);
		if (diff != 0) {
			return diff;
		} else {
			diff = Integer.compare(x, point.x);
			if (diff != 0) {
				return diff;
			} else {
				return Integer.compare(y, point.y);
			}
		}
	}
}
