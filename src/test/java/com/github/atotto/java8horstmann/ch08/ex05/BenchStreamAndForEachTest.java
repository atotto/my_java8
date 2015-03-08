package com.github.atotto.java8horstmann.ch08.ex05;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.Test;

import com.github.atotto.myutil.Benchmark;

public class BenchStreamAndForEachTest {

	final int N = 100;

	@Test
	public void benchStream_short() throws IOException {
		List<String> words = setupShortList();

		Benchmark b = () -> {
			long count = words.stream().filter(w -> w.length() > 12).count();

			assertThat(count, is(33L));
		};
		b.wakeUp(3);

		long time = b.run(N);
		System.out.printf("BenchStream short \t%10d ns/op \n", time / N);
	}

	@Test
	public void benchStream_long() throws IOException {
		List<String> words = setupLongList();

		Benchmark b = () -> {
			long count = words.stream().filter(w -> w.length() > 12).count();

			assertThat(count, is(1946L));
		};
		b.wakeUp(3);

		long time = b.run(N);
		System.out.printf("BenchStream long  \t%10d ns/op \n", time / N);
	}

	@Test
	public void benchForEach_short() throws IOException {
		List<String> words = setupShortList();

		Benchmark b = () -> {
			AtomicLong count = new AtomicLong();
			words.forEach(w -> {
				if (w.length() > 12) {
					count.incrementAndGet();
				}
			});

			assertThat(count.get(), is(33L));
		};
		b.wakeUp(3);

		long time = b.run(N);
		System.out.printf("BenchForEach short \t%10d ns/op \n", time / N);
	}

	@Test
	public void benchForEach_long() throws IOException {
		List<String> words = setupLongList();

		Benchmark b = () -> {
			AtomicLong count = new AtomicLong();
			words.forEach(w -> {
				if (w.length() > 12) {
					count.incrementAndGet();
				}
			});

			assertThat(count.get(), is(1946L));
		};
		b.wakeUp(3);

		long time = b.run(N);
		System.out.printf("BenchForEach long  \t%10d ns/op \n", time / N);
	}

	private List<String> setupShortList() throws IOException {
		String contents = new String(Files.readAllBytes(Paths.get(getClass()
				.getResource("/alice.txt").getFile())), StandardCharsets.UTF_8);
		return Arrays.asList(contents.split("[\\P{L}]+"));
	}

	private List<String> setupLongList() throws IOException {
		String contents = new String(Files.readAllBytes(Paths.get(getClass()
				.getResource("/war-and-peace.txt").getFile())),
				StandardCharsets.UTF_8);
		return Arrays.asList(contents.split("[\\P{L}]+"));
	}
}
