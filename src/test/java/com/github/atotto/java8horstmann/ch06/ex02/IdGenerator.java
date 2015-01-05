package com.github.atotto.java8horstmann.ch06.ex02;

import java.util.concurrent.atomic.LongAdder;

public class IdGenerator {
	final LongAdder adder;

	public IdGenerator() {
		adder = new LongAdder();
	}

	public synchronized long get() {
		// synchronizedが必要であるためイマイチ。
		adder.increment();
		return adder.longValue();
	}

	public long current() {
		return adder.longValue();
	}

	public void reset() {
		adder.reset();
	}
}
