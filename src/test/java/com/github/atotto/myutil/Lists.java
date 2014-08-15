package com.github.atotto.myutil;

import java.util.ArrayList;
import java.util.List;

public class Lists {

	public static <T> List<List<T>> partition(List<T> list, int size) {
		int block = list.size() / size + (list.size() % size > 0 ? 1 : 0);
		List<List<T>> devidedList = new ArrayList<List<T>>(block);

		for (int index = 0; index < block; index++) {
			int start = index * size;
			int end = Math.min(start + size, list.size());

			devidedList.add(new ArrayList<T>(list.subList(start, end)));
		}
		return devidedList;
	}

}
