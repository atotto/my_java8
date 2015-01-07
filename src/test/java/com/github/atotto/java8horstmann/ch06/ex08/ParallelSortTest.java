package com.github.atotto.java8horstmann.ch06.ex08;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

import com.github.atotto.myutil.Benchmark;

public class ParallelSortTest {

	static final int N = 133;
	static final int wakeup = 3;

	public static long sequentialSort(int[] data) {
		Benchmark b = () -> {
			int[] d = data.clone();
			Arrays.sort(d);
		};
		b.wakeUp(wakeup);

		return b.run(N) / N;
	}

	public static long parallelSort(int[] data) {
		Benchmark b = () -> {
			int[] d = data.clone();
			Arrays.parallelSort(d);
		};
		b.wakeUp(wakeup);

		return b.run(N) / N;
	}

	public static void benchParallelSort(PrintStream out) {
		out.println("#     size sequential   parallel");
		Random random = new Random(0);

		for (int size = 100; size <= 10000; size += 100) {
			int[] testArray = new int[size];
			random.setSeed(0);
			for (int i = 0; i < size; i++) {
				testArray[i] = random.nextInt();
			}

			long s_time = sequentialSort(testArray);
			long p_time = parallelSort(testArray);
			out.printf("%10d %10d %10d%n", size, s_time, p_time);
		}
	}

	@Test
	public void bench() throws FileNotFoundException {
		PrintStream ps = new PrintStream(new File("benchParallelSort.txt"));
		benchParallelSort(ps);
	}

	public static void main(String[] args) {
		benchParallelSort(System.out);
	}
}
