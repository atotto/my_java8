package com.github.atotto.java8horstmann.ch03.ex13;


@FunctionalInterface
public interface ConvolutionFilter {
	double apply(double[][] mat);
}
