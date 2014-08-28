package com.github.atotto.java8horstmann.ch03.ex02;

import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

public class SyncTest {

	@Test
	public void testBefore() {
		ReentrantLock myLock = new ReentrantLock();
		myLock.lock();
		try {
			// do something
		} finally {
			myLock.unlock();
		}
	}

	@Test
	public void testAfter() throws Exception {
		ReentrantLock myLock = new ReentrantLock();
		withLock(myLock, () -> {
			// do something
			});
	}

	static void withLock(ReentrantLock l, Runnable action) {
		l.lock();
		try {
			action.run();
		} finally {
			l.unlock();
		}
	}
}
