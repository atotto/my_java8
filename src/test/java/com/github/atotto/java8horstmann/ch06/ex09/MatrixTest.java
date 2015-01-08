package com.github.atotto.java8horstmann.ch06.ex09;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class MatrixTest {

	@Test
	public void testMultiply2x2() {
		int[][] m1 = new int[2][2];
		m1[0][0] = 1;
		m1[1][0] = 2;
		m1[0][1] = 3;
		m1[1][1] = 4;

		int[][] m2 = new int[2][2];
		m2[0][0] = 5;
		m2[1][0] = 6;
		m2[0][1] = 7;
		m2[1][1] = 8;

		int[][] expect = new int[2][2];
		expect[0][0] = 19;
		expect[1][0] = 22;
		expect[0][1] = 43;
		expect[1][1] = 50;

		int[][] out = Matrix.multiply2x2(m1, m2);
		assertThat(out, is(expect));
	}
}
