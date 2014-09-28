package com.github.atotto.myutil.javafx;

import java.util.concurrent.CountDownLatch;

import javafx.application.Application;
import javafx.stage.Stage;

public class ApplicationTest extends Application {
	private static final CountDownLatch latch = new CountDownLatch(1);
	private static boolean started = false;

	public synchronized static void launch() {
		if (started) {
			return;
		}
		Runnable r = () -> {
			Application.launch(new String());
			started = true;
		};
		new Thread(r).start();
		try {
			latch.await();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void start(Stage stage) throws Exception {
		latch.countDown();
	}
}
