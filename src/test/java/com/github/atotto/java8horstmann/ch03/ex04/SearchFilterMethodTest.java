package com.github.atotto.java8horstmann.ch03.ex04;

import static org.reflections.ReflectionUtils.getAllMethods;
import static org.reflections.ReflectionUtils.withModifier;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.junit.Test;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

public class SearchFilterMethodTest {
	@SuppressWarnings("unchecked")
	@Test
	public void searchFilterMethod() throws Exception {
		Collection<URL> urls = new ArrayList<>();
		urls.add(ClasspathHelper.forClass(FunctionalInterface.class));

		TypeAnnotationsScanner scanner = new TypeAnnotationsScanner();
		Reflections reflections = new Reflections(new ConfigurationBuilder()
				.addUrls(urls)
				.addClassLoader(new URLClassLoader(urls.toArray(new URL[] {})))
				.setScanners(scanner, new SubTypesScanner()));

		Set<Class<?>> annotated = reflections
				.getTypesAnnotatedWith(FunctionalInterface.class);

		annotated
				.stream()
				.filter((c) -> {
					return c.isInterface() && c.getName().endsWith("Filter");
				})
				.sorted((c1, c2) -> c1.getName().compareTo(c2.getName()))
				.forEach(
						(c) -> {
							Set<Method> methods = getAllMethods(c,
									withModifier(Modifier.PUBLIC));
							System.out.println(c);
							methods.forEach((m) -> System.out.printf("\t%s\n",
									m));
						});
	}
}
