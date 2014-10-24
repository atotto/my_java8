## ex01

[Comparator<T>](http://docs.oracle.com/javase/jp/8/api/java/util/Comparator.html)

コンパレータは実行したスレッドでコールしているので同じスレッド内で実行される。

## ex02

[java.io.File](http://docs.oracle.com/javase/jp/8/api/java/io/File.html)

指定されたディレクトリの下のすべてのサブディレクトリを返す以下のメソッドを作る:

1. listFiles(FileFilter)メソッドとisDirectoryメソッドを使用
2. FileFilterオブジェクトではなく、ラムダ式を使用
3. FileFilterオブジェクトではなく、メソッド参照を使用

## ex03

[java.io.File](http://docs.oracle.com/javase/jp/8/api/java/io/File.html)

指定されたディレクトリの下の指定された拡張子を持つ、すべてのファイルを返すメソッドを作る:

1. list(FilenameFilter)を使用
2. FilenameFilterではなく、ラムダ式を使用

## ex04

Fileオブジェクトの配列を以下の条件でソートする:

* ファイルの前にディレクトリが来るようにする
* ファイルとディレクトリのそれぞれのグループではパス名でソートされるようする
* Comparatorではなく、ラムダ式を使用する

## ex05

適当なプロジェクトのActionListenerやRunnableといったインタフェースをラムダ式で置き換える:

* 置き換えた結果、コードが何行短くなるか
* コードは読みやすくなるか
* メソッド参照を使用することができたか

## ex06

Runnable内でチェックされるすべての例外をキャッチし、それをチェックされない例外へ変えるuncheckメソッドを作る

## ex07

最初のRunnableを実行した後に2つ目のRunnableを実行するRunnableを返す、staticメソッドandThenを作る

[BiFunction](http://docs.oracle.com/javase/jp/8/api/java/util/function/BiFunction.html)に少し似ている？

## ex08

```java
String[] names = {"Peter", "Paul", "Mary"};
List<Runnable> runners = new ArrayList<>();
for (String name : names)
    runners.add(() -> System.out.println(name));
```

ラムダ式は拡張forループ内の値をそれぞれキャプチャできる。
従来の`for (int i = 0; i < names.length; i++)`ループを使用すると、変数iがラムダ式変化するためコンパイルエラーになる。

## ex09

```java
	public interface Collection2<E> extends Collection<E> {
		default void forEachIf(Consumer<E> action, Predicate<E> filter) {
			this.forEach(act -> {
				if (filter.test(act)) {
					action.accept(act);
				}
			});
		}
	}
```

Collection2はArrayListなどのCollectionを実装したクラスとは別のインターフェースとなるため、Collection2で実装したクラスを準備する必要がある。
あまり使いどころがわからなかった・・

参考資料:

* http://docs.oracle.com/javase/8/docs/api/java/util/Collection.html
* http://docs.oracle.com/javase/8/docs/api/java/util/function/Predicate.html
* http://docs.oracle.com/javase/8/docs/api/java/util/function/Consumer.html

## ex10

[Collectionsクラス](http://docs.oracle.com/javase/jp/8/api/java/util/Collections.html)

このままのメソッドであれば、すべてstaticなメソッドで関連するインターフェースへ移動する。
関連するインターフェースとは、第一引数、戻り値で判断できると思う。

## ex11

組み合わせ:

Interface x Interface

| I::f()   | J::f()   |
| -------- | -------- |
| abstract | abstract |
| abstract | default  |
| abstract | static   |
| default  | default  |
| default  | static   |
| static   | static   |

SuperClass x Interface

| S::f()   | I::f()   |
| -------- | -------- |
| abstract | abstract |
| abstract | default  |
| abstract | static   |
| class    | abstract |
| class    | default  |
| class    | static   |
| static   | abstract |
| static   | default  |
| static   | static   |

## ex12

Collectionにあるdefault methodと同じシグニチャを持つメソッドを定義してあるとエラーとなる

```java
	class MyArrayList<T> extends ArrayList<T> {
		/*
		 * Error: Description Resource Path Location Type The return type is
		 * incompatible with Collection<T>.stream()
		 */
		public Stream<T> stream() {
			return new Stream<T>();
		}

		public Stream<T> parallelStream() {
			return new Stream<T>();
		}
	}
```

Java SE 7とのバイナリ互換性は基本的にある 
http://www.oracle.com/technetwork/jp/java/javase/overview/8-compatibility-guide-2156366-ja.html

なぜか:

どのメソッドが呼ばれるかいつ決まるかを考える

* 実行時
  * Overrideされているメソッド:どのクラスのものが呼ばれるか決まる
* コンパイル時
  * シグニチャと戻り値でどのメソッドを呼ぶか決まる

（クラスファイルには戻り値の異なるメソッドを書ける）
