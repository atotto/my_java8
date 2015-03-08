package com.github.atotto.java8horstmann.ch08.ex11;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;

import org.junit.Test;

import com.github.atotto.myutil.Resource;

public class Base64WebScrapTest {

	public static InputStream connect(URL url, String username, String password)
			throws IOException {
		byte[] bytes = new String(username + ":" + password).getBytes();
		URLConnection connection = url.openConnection();
		Base64.Encoder encoder = Base64.getEncoder();
		connection.setRequestProperty("Authorization",
				"Basic " + encoder.encodeToString(bytes));
		connection.connect();
		return connection.getInputStream();
	}

	@Test
	public void testConnect() throws MalformedURLException, IOException {
		String username = "alice";
		String password = "secret";
		URL url = Resource.path("/alice.txt").toUri().toURL();

		// TODO: test
		InputStream input = connect(url, username, password);
		byte[] buf = new byte[1024];
		while (input.read(buf) != -1) {
		}
	}
}
