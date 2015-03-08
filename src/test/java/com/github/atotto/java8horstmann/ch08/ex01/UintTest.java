package com.github.atotto.java8horstmann.ch08.ex01;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class UintTest {

	@Test
	public void testAdd() {
		assertThat(Uint.add(0, 0), is(0L));
		assertThat(Uint.add(0, 1), is(1L));
		assertThat(Uint.add(Uint.MAX_VALUE, 1), is(1L << 32));
	}

	@Test
	public void testSub() {
		assertThat(Uint.sub(0, 0), is(0L));
		assertThat(Uint.sub(0, 1), is(-1L));
		assertThat(Uint.sub(Uint.MAX_VALUE, 1), is((1L << 32) - 2));
	}

	@Test
	public void testDiv() {
		assertThat(Uint.div(0, 1), is(0));
		assertThat(Uint.div(100, 3), is(33));
		assertThat(Uint.div(Uint.MAX_VALUE, 1), is(Uint.MAX_VALUE));
		assertThat(Uint.div(Uint.MAX_VALUE, Uint.MAX_VALUE), is(1));
	}

	@Test
	public void testComp() {
		assertThat(Uint.compare(0x00000001, 1), is(0));
		assertThat(Uint.compare(0xFFFFFFFF, -1), is(0));
	}
}
