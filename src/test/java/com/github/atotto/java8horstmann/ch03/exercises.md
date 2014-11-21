## ex01

[Loggerパッケージ](http://docs.oracle.com/javase/jp/8/api/java/util/logging/Logger.html)を見るとSupplier<String>を引数に取るメソッドがjava8で導入されている。

課題のlogIfメソッドを作ってみたものの、ラムダ式に変更される可能性のある変数をそのまま入れられない＆配列の中身が並行して変更されると問題があるので、あまり使い勝手が良くない。

[Java8のlambda構文がどのようにクロージャーではないか](http://d.hatena.ne.jp/nowokay/20130522) で話があるように、問題になりやすい箇所を許さない設計になっているが配列で渡されるとイマイチな感じ。

ちなみにGoだと全然気にせずに書けてしまうので、この点を気をつけておく必要がある。

## ex02

[ReentrantLockクラス](http://docs.oracle.com/javase/jp/8/api/java/util/concurrent/locks/ReentrantLock.html))

## ex03

ここに記載がある: ["なぜアサーションの実装に、ライブラリの変更ではなく、言語の変更を採用したのですか。"](http://docs.oracle.com/javase/jp/7/technotes/guides/language/assert.html#design-faq)

アサーションを無効にした場合にランタイムの実行速度の低下を抑えるために言語の変更としている。ライブラリにした場合、実行時に条件式を評価する必要が出る。

ラムダ式で遅延評価ができるため、アサーションが無効の場合にその式を評価しないようなアサーションメソッドを作れば解決できる。

## ex04

skip

## ex05

画像の周りに10pxの枠をつける。
UnaryOperator<Color>をColorTransformerで書きなおす。

## ex06

`public static <T> Image transform(Image in, BiFunction<Color, T, Color> f, T arg)` を実装する。

本文は`BiFunction<Color, T> f`となっているが`BiFunction<Color, T, Color> f`では？

実際には関数を返すメソッドを使う（P.66）：

```java
	public static UnaryOperator<Color> brighten(double factor) {
		return c -> c.deriveColor(0, 1, factor, 1);
	}
```

## ex07

`Comparator<String>` を返すメソッド:

* normal
* reversed
* case-sensitive
* case-insensitive
* space-sensitive
* space-insensitive

## ex08

画像に任意の幅と色の枠をつける`ColorTransformer`を生成するメソッドを作る。

## ex09

`Comparator<Object> lexicographicComparator(String... fieldNames)`メソッドを作る。

## ex10

```java
UnaryOperator op = Color::brighter;
Image finalImage = transform(image, op.compose(Color::grayscale));
```

```java
default <V> Function<V,R> compose(Function<? super V,? extends T> before)
```

* structural type: 名前が異なっていても構造が同じであれば同じ型であるとみなす
* nominal type: 名前が同じであれば同じ型であるとみなす

composeで`Function<Color,Color>`が戻り値となる。
transformは`UnaryOperator<Color>`を受けるようになっているため、これを受け付けることができない。
構造を考えると同じであるため、ストラクチャル型であれば受け付けられるが、Javaはノミナル型であるため、これを受け付けられない。

## ex11

2つの`ColorTransformer`を処理できる`ColorTransformer compose(ColorTransformer tr1, ColorTransformer tr2)`メソッドを作る。

`UnaryOperator<Color>`を全体に適用する`ColorTransformer`を返すメソッドを作る

## ex12

`LatentImage`を拡張して`UnaryOperator<Color>`と`ColorTransformer`を受け付けるように。

## ex13

3x3のサイズで畳み込みする。
LaplacianフィルタとMeanフィルタ。

## ex14

ピクセル単位での遅延評価を扱う。

* PixelReaderを渡すようにして画像内の他のピクセルを読み込めるようにする
* PixelReaderはパイプライン中の特定のlevelにあるため、それぞれのlevelで読み込まれたピクセルのcacheを保持する
* ピクセルを要求されるとreaderはchcheを調べ（level0であれば元画像からピクセルを調べる）、ピクセルがなければその前からピクセルを要求する

## ex15

遅延評価と並列評価を組み合わせる。

toImageを並列化する。

## ex16

firstがresultをsupplyし、secondがconsumeする。
handlerでexpectionを処理する。

```java
	public static <T> void doInOrderAsync(Supplier<T> first,
			Consumer<T> second, Consumer<Throwable> handler) {
		Thread t = new Thread() {
			public void run() {
				try {
					T result = first.get();
					second.accept(result);
				} catch (Throwable t) {
					handler.accept(t);
				}
			}
		};
		t.start();
	}
```

`BiConsumer<T, Throwable>`で書きなおす。

firstでsupplyし、secondでconsumeする。
secondでfirstのexceptionを処理する。

```java
	public static <T> void doInOrderAsync(Supplier<T> first,
			BiConsumer<T, Throwable> second) {
		Thread t = new Thread() {
			public void run() {
				try {
					T result = first.get();
					second.accept(result, null);
				} catch (Throwable t) {
					second.accept(null, t);
				}
			}
		};
		t.start();
	}
```

## ex17

`doInParallelAsync(Runnable first, Runnable second, Consumer<Throwable> handler)`を実装する。

## ex18

```java
	public static <T> Supplier<T> unchecked(Callable<T> f) {
		return () -> {
			try {
				return f.call();
			} catch (Exception e) {
				throw new RuntimeException(e);
			} catch (Throwable t) {
				throw t;
			}
		};
	}
```

`Function<T, R>`を生成するようにしたバージョンを実装する。

[Function<T, R>](http://docs.oracle.com/javase/8/docs/api/java/util/function/Function.html)は、1つの引数を受け取って結果を生成する関数を表す。

任意の例外をスローする抽象メソッドを持つ関数型インターフェース:

* [Callable<V>](http://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Callable.html)

これとFunction<T, R>を統合すると:

```java
@FunctionalInterface
interface BiCallable<T, R> {
	R call(T t) throws Exception;
}
```

のような関数型インターフェースが必要になる。が、存在しない？

## ex19

[`Stream<T>`](http://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html)
の
[`<U> U reduce(U identity, BiFunction<U,? super T,U> accumulator, BinaryOperator<U> combiner)`](http://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#reduce-U-java.util.function.BiFunction-java.util.function.BinaryOperator-)

skip

## ex20

`static <T, U> List<U> map(List<T> list, Function<T, U> function)`を実装する。

`List<T>`の`list`を`function`で`List<U>`へ変換する。

* [Collectors](http://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html)

## ex21

`static <T, U> Future<U> map(Future<T> future, Function<T, U> function)`を実装する。

* [Future](http://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Future.html)は6章参照。

## ex22

skip

## ex23

Pair<T>に対するmap操作を定義する。

参考資料:

* http://www.cplusplus.com/reference/utility/pair/

## ex24

flatMapメソッドは定義できるか。
