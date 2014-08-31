package com.github.atotto.java8horstmann.ch02.ex03;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.atotto.myutil.Benchmark;

public class ParallelStreamTest {

	String contents;
	List<String> words;

	@Before
	public void setUp() throws Exception {
		contents = new String(Files.readAllBytes(Paths.get(getClass()
				.getResource("/war-and-peace.txt").getFile())),
				StandardCharsets.UTF_8);
		words = Arrays.asList(contents.split("[\\P{L}]+"));
	}

	static int N = 100;

	@Test
	public void benchSequentialStream() {

		Benchmark b = () -> {
			words.stream().sequential().filter(w -> w.length() > 12).count();
		};
		b.wakeUp(3);

		long time = b.run(N);
		System.out.printf("BenchSequentialStream\t%10d ns/op \n", time / N);
	}

	@Test
	public void benchParallelStream() {

		Benchmark b = () -> {
			words.stream().parallel().filter(w -> w.length() > 12).count();
		};
		b.wakeUp(3);

		long time = b.run(N);
		System.out.printf("BenchParallelStream\t%10d ns/op \n", time / N);
	}
}
