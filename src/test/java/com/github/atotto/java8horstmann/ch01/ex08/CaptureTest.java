package com.github.atotto.java8horstmann.ch01.ex08;

import static com.github.atotto.myutil.Uncheck.uncheck;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class CaptureTest {

	@Test
	public void testEnhancedForLoop() throws InterruptedException {
		String[] names = { "Peter", "Paul", "Mary" };
		List<Runnable> runners = new ArrayList<>();
		List<String> finishers = Collections
				.synchronizedList(new ArrayList<>());

		for (String name : names) {
			runners.add(uncheck(() -> {
				Thread.sleep((long) (name.length() * 10));
				finishers.add(name);
			}));
		}

		List<Thread> tasks = runners.stream().map(Thread::new)
				.collect(Collectors.toList());
		tasks.forEach(Thread::start);
		for (Thread th : tasks) {
			th.join();
		}

		assertThat(finishers, is(containsInAnyOrder(names)));
	}

	@Test
	public void testTraditionalForLoop() {
		String[] names = { "Peter", "Paul", "Mary" };
		@SuppressWarnings("unused")
		List<Runnable> runners = new ArrayList<>();

		for (int i = 0; i < names.length; i++) {
			// runners.add(() -> System.out.println(names[i]));
			// value i got the following error:
			// `Local variable i defined in an enclosing scope must be final or
			// effectively final`
		}
	}
}
