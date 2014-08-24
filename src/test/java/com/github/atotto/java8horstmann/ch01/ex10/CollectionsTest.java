package com.github.atotto.java8horstmann.ch01.ex10;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

import org.junit.Test;

public class CollectionsTest {

	@Test
	public void generateMySolution() {
		Arrays.asList(Collections.class.getMethods())
				.stream()
				.filter(m -> {
					return !m.getDeclaringClass().toGenericString()
							.contains("java.lang.Object");
				})
				.collect(
						Collectors.groupingBy((Method m) -> {
							Class<?>[] params = m.getParameterTypes();
							if (params.length > 0) {
								if (params[0].getName().contentEquals(
										"java.lang.Object")
										|| params[0].getName().contentEquals(
												"int")) {
									return m.getReturnType();
								}
								return params[0];
							}
							return m.getReturnType();
						}))
				.forEach(
						(group, methods) -> {
							System.out.printf("\n## to %s:\n", group);
							for (Method m : methods) {
								System.out.printf("* public static %s\n",
										toMethodName(m));
							}
						});
	}

	static String toShortName(String name) {
		return name.replaceFirst(".+[.]", "");
	}

	static String toMethodName(Method m) {
		StringBuilder sb = new StringBuilder();
		sb.append(toShortName(m.getGenericReturnType().toString()));
		sb.append(' ');
		sb.append(m.getName());

		sb.append('(');
		Type[] params = m.getGenericParameterTypes();
		for (int i = 0; i < params.length; i++) {
			sb.append(toShortName(params[i].getTypeName()));
			if (i < params.length - 1) {
				sb.append(',');
			}
		}
		sb.append(')');

		return sb.toString();
	}
}
