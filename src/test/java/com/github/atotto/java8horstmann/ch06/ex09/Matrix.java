package com.github.atotto.java8horstmann.ch06.ex09;

import java.util.Objects;

public class Matrix {

	public static int[][] require2x2matrix(int[][] m) {
		Objects.requireNonNull(m);
		if (m.length != 2) {
			throw new IllegalArgumentException("Matrix col size must be 2");
		}
		if (m[0].length != 2 || m[1].length != 2) {
			throw new IllegalArgumentException("Matrix row size must be 2");
		}
		return m;
	}

	public static int[][] multiply2x2(int[][] m1, int[][] m2) {
		require2x2matrix(m1);
		require2x2matrix(m2);
		int[][] out = new int[2][2];
		out[0][0] = m1[0][0] * m2[0][0] + m1[1][0] * m2[0][1];
		out[1][0] = m1[0][0] * m2[1][0] + m1[1][0] * m2[1][1];
		out[0][1] = m1[0][1] * m2[0][0] + m1[1][1] * m2[0][1];
		out[1][1] = m1[0][1] * m2[1][0] + m1[1][1] * m2[1][1];
		return out;
	}
}
