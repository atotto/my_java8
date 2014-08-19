package com.github.atotto.java8horstmann.ch01.ex11;

import org.junit.Test;

public class CombinationTest {

	public static class InterfacexInterface {

		public static class AxA {
			@FunctionalInterface
			interface I {
				abstract void f();
			}

			@FunctionalInterface
			interface J {
				abstract void f();
			}

			class IJ implements I, J {
				// interfaceの実装が必要
				@Override
				public void f() {
					System.out.println(IJ.class.getCanonicalName());
				}
			}

			@Test
			public void test() throws Exception {
				new IJ().f(); // called AxA.IJ
			}
		}

		public static class AxD {
			@FunctionalInterface
			interface I {
				abstract void f();
			}

			interface J {
				default void f() {
					System.out.println(J.class.getCanonicalName());
				};
			}

			class IJ implements I, J {
				// どちらのfを呼ぶのかわからないのでfを実装して明らかにする必要がある
				@Override
				public void f() {
					System.out.println(IJ.class.getCanonicalName());
				}
			}

			@Test
			public void test() throws Exception {
				new IJ().f(); // called AxD.IJ
			}
		}

		public static class AxS {
			@FunctionalInterface
			interface I {
				abstract void f();
			}

			interface J {
				static void f() {
					System.out.println(J.class.getCanonicalName());
				};
			}

			class IJ implements I, J {
				// interfaceの実装が必要
				@Override
				public void f() {
					System.out.println(IJ.class.getCanonicalName());
				}
			}

			@Test
			public void test() throws Exception {
				new IJ().f(); // called AxS.IJ
			}
		}

		public static class DxD {
			interface I {
				default void f() {
					System.out.println(I.class.getCanonicalName());
				};
			}

			interface J {
				default void f() {
					System.out.println(J.class.getCanonicalName());
				};
			}

			class IJ implements I, J {
				// どちらのfを呼ぶのかわからないのでfを実装して明らかにする必要がある
				@Override
				public void f() {
					System.out.println(IJ.class.getCanonicalName());
				}
			}

			@Test
			public void test() throws Exception {
				new IJ().f(); // called DxD.IJ
			}
		}

		public static class DxS {
			interface I {
				default void f() {
					System.out.println(I.class.getCanonicalName());
				};
			}

			interface J {
				static void f() {
					System.out.println(J.class.getCanonicalName());
				};
			}

			class IJ implements I, J {
				// 実装は不要
			}

			@Test
			public void test() throws Exception {
				new IJ().f(); // called DxS.I
			}
		}

		public static class SxS {
			interface I {
				static void f() {
					System.out.println(I.class.getCanonicalName());
				};
			}

			interface J {
				static void f() {
					System.out.println(J.class.getCanonicalName());
				};
			}

			class IJ implements I, J {
				// 実装は不要
			}

			@Test
			public void test() throws Exception {
				// 呼べない？
			}
		}
	}

	public static class SuperclassxInterface {

		public static class AxA {
			abstract class S {
				abstract public void f();
			}

			@FunctionalInterface
			interface I {
				abstract void f();
			}

			class SI extends S implements I {
				// abstractメソッドを実装する必要がある
				@Override
				public void f() {
					System.out.println(SI.class.getCanonicalName());
				}
			}

			@Test
			public void test() throws Exception {
				new SI().f(); // called AxA.SI
			}
		}

		public static class AxD {
			abstract class S {
				abstract public void f();
			}

			interface I {
				default void f() {
					System.out.println(I.class.getCanonicalName());
				};
			}

			class SI extends S implements I {
				// abstractメソッドを実装する必要がある
				@Override
				public void f() {
					System.out.println(SI.class.getCanonicalName());
				}
			}

			@Test
			public void test() throws Exception {
				new SI().f(); // called AxD.SI
			}
		}

		public static class AxS {
			abstract class S {
				abstract public void f();
			}

			interface I {
				static void f() {
					System.out.println(I.class.getCanonicalName());
				};
			}

			class SI extends S implements I {
				// abstractメソッドを実装する必要がある
				@Override
				public void f() {
					System.out.println(SI.class.getCanonicalName());
				}
			}

			@Test
			public void test() throws Exception {
				new SI().f(); // called AxS.SI
			}
		}

		public static class CxA {
			class S {
				public void f() {
					System.out.println(S.class.getCanonicalName());
				};
			}

			@FunctionalInterface
			interface I {
				abstract void f();
			}

			class SI extends S implements I {
				// 実装は不要
			}

			@Test
			public void test() throws Exception {
				new SI().f(); // called CxA.S
			}
		}

		public static class CxD {
			class S {
				public void f() {
					System.out.println(S.class.getCanonicalName());
				};
			}

			interface I {
				default void f() {
					System.out.println(I.class.getCanonicalName());
				};
			}

			class SI extends S implements I {
				// 実装は不要
			}

			@Test
			public void test() throws Exception {
				new SI().f(); // called CxD.S
			}
		}

		public static class CxS {
			class S {
				public void f() {
					System.out.println(S.class.getCanonicalName());
				};
			}

			interface I {
				static void f() {
					System.out.println(I.class.getCanonicalName());
				};
			}

			class SI extends S implements I {
			}

			@Test
			public void test() throws Exception {
				new SI().f(); // called CxS.S
			}
		}

		public static class SxA {
			public static class S {
				static public void f() {
					System.out.println(S.class.getCanonicalName());
				}
			}

			@FunctionalInterface
			interface I {
				abstract void f();
			}

			// error
			// class SI extends S implements I {
			//
			// }

			@Test
			public void test() throws Exception {

			}
		}

		public static class SxD {
			static class S {
				static public void f() {
					System.out.println(S.class.getCanonicalName());
				}
			}

			interface I {
				default void f() {
					System.out.println(I.class.getCanonicalName());
				};
			}

			// error
			// class SI extends S implements I {
			// }

			@Test
			public void test() throws Exception {
			}
		}

		public static class SxS {
			static class S {
				static public void f() {
					System.out.println(S.class.getCanonicalName());
				}
			}

			interface I {
				static void f() {
					System.out.println(I.class.getCanonicalName());
				};
			}

			class SI extends S implements I {
				// 実装は不要
			}

			@Test
			public void test() throws Exception {
				SI.f(); // called SxS.S
			}
		}
	}
}
