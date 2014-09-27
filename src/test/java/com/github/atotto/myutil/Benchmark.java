package com.github.atotto.myutil;

@FunctionalInterface
public interface Benchmark {

	public void benchmark();

	/**
	 * Waking up the JIT compiler...
	 * 
	 * @param n
	 *            repeat number
	 */
	public default long wakeUp(int n) {
		return run(n);
	}

	/**
	 * Run benchmark
	 * 
	 * @param n
	 *            repeat number
	 */
	public default long run(int n) {
		long start = System.nanoTime();
		for (int i = 0; i < n; i++) {
			benchmark();
		}
		return (System.nanoTime() - start);
	}
}