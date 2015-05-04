package com.github.atotto.java8horstmann.ch09.ex03;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

import com.github.atotto.myutil.Resource;

public class MultipleExceptions {

	public void process() throws IOException {
		try {
			new Scanner(Resource.path("/alice.txt"));
			// ...
		} catch (FileNotFoundException | UnknownHostException ex) {
			// logger.log(Level.SEVERE, "...", ex);
			throw ex;
		}
	}
}
