package com.github.atotto.myutil;

public class Uncheck {
	public interface RunnableEx {
		public void run() throws Exception;
	}

	public static Runnable uncheck(RunnableEx runner) {
		return () -> {
			try {
				runner.run();
			} catch (Exception ex) {
				// uncheck exception
				throw new RuntimeException(ex);
			}
		};
	}
}
