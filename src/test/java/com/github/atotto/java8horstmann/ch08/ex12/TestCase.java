package com.github.atotto.java8horstmann.ch08.ex12;

import java.lang.annotation.Repeatable;

@Repeatable(TestCases.class)
public @interface TestCase {

	String params();

	String expected();
}
