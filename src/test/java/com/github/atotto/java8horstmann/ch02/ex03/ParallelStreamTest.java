package com.github.atotto.java8horstmann.ch02.ex03;

import java.io.IOException;
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
				.getResource("/alice.txt").getFile())), StandardCharsets.UTF_8);
		words = Arrays.asList(contents.split("[\\P{L}]+"));
	}

	@Test
	public void benchStream() throws IOException {
		class B extends Benchmark {
			public void benchmark() {
				words.stream().filter(w -> w.length() > 12).count();
			}
		}

		int n = 10;
		long time = new B().repeat(n);
		System.out.printf("BenchStream        \t%10d ns/op \n", time / n);
	}

	@Test
	public void benchParallelStream() throws IOException {
		class B extends Benchmark {
			public void benchmark() {
				words.parallelStream().filter(w -> w.length() > 12).count();
			}
		}

		int n = 10;
		long time = new B().repeat(n);
		System.out.printf("BenchParallelStream\t%10d ns/op \n", time / n);
	}
}
