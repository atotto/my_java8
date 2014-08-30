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
  * エンクロージングスコープからキャプチャされる変数は？

## ex04

Fileオブジェクトの配列を以下の条件でソートする:

* ファイルの前にディレクトリが来るようにする
* ファイルとディレクトリのそれぞれのグループではパス名でソートされるようする
* Comparatorではなく、ラムダ式を使用する

## ex05

1. みなさんのプロジェクトの1つから、ActionListenerやRunnableといったインタフェースを多数使用しているファイルを1つ選んでください。それらのインタフェースの使用をラムダ式で置き換えなさい。
2. 置き換えた結果、コードが何行短くなりましたか。
3. コードは読みやすくなりましたか。
4. メソッド参照を使用することができましたか。

## ex06

1. Runnable内でチェックされる例外を処理しなければならないことが、いつも面倒だと思っていませんか。チェックされるすべての例外をキャッチし、それをチェックされない例外へ変えるuncheckメソッドを書きなさい。

例:

```java
new Thread(uncheck(() -> { System.out.println("Zzz"); Thread.sleep(1000); })).start();
// catch (InterruptedException) は必要ありません
```

ヒント: どのような例外でもスローできるrunメソッドを持つRunnableExインタフェースを定義します。そして、public static Runnable uncheck(RunnableEx runner)を実装します。uncheck関数内でラムダ式を使用します。なぜ、RunnableExの代わりにCallable<Void>を使用できないのでしょうか。

## ex07

1. 2つのRunnableインスタンスをパラメータとして受け取り、最初のRunnableを実行した後に2つ目のRunnableを実行するRunnableを返すように、staticメソッドandThenを書きなさい。mainメソッドでは、andThenへの呼び出しに2つのラムダ式を渡して、返されたインスタンスを実行しなさい。

## ex08

1. ラムダ式が次のような拡張forループ内の値をキャプチャした場合には、どうなりますか。

```java
String[] names = {"Peter", "Paul", "Mary"};
List<Runnable> runners = new ArrayList<>();
for (String name : names)
    runners.add(() -> System.out.println(name));
```

これは、正当なコードでしょうか。各ラムダ式は異なる値をキャプチャするのでしょうか。それとも、すべてのラムダ式が最後の値をキャプチャするのでしょうか。
2. 従来の`for (int i = 0; i < names.length; i++)`ループを使用すると、どうなるでしょうか。

## ex09

1. java.util.CollectionのサブインタフェースであるCollection2を作成して、デフォルトメソッドとして`void forEachIf(Consumer<T> action, Predicate<T> filter)`を追加しなさい。そのメソッドは、filterがtrueを返してきた個々の要素に対してactionを適用します。
2. どのような場面で、そのメソッドを活用できるでしょうか。

参考資料:

* http://docs.oracle.com/javase/8/docs/api/java/util/Collection.html
* http://docs.oracle.com/javase/8/docs/api/java/util/function/Predicate.html
* http://docs.oracle.com/javase/8/docs/api/java/util/function/Consumer.html

## ex10

Collectionsクラスのメソッドに目を通してください。みなさんが何でもできるとしたら、どのインタフェースにCollectionsクラスの各メソッドを入れますか。それぞれ、デフォルトメソッドになりますか。それとも、staticメソッドになりますか。

* http://docs.oracle.com/javase/jp/8/api/java/util/Collections.html

## ex11

1. `void f()`メソッドを持つ、IとJの2つのインタフェースがあり、両方を実装しているクラスがあるとします。Iインタフェースのfメソッドが抽象、デフォルト、staticのどれかであり、Jインタフェースのfメソッドが抽象、デフォルト、staticのどれかである場合、すべての組み合わせで何が起きるでしょうか。
2. 同じように、スーパークラスSを拡張し、Iインタフェースを実装した場合に、スーパークラスもインタフェースも`void f()`メソッドを持っていたらどうなるかを調べなさい。

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

インタフェースにメソッドを追加することは既存のコードを動作させなくするので、過去には悪いことであると言われていました。今では、デフォルト実装も提供するのであれは、新たなメソッドを追加することは問題ありません。そのような追加はどれだけ安全なのでしょうか。

1. Collectionインタフェースの新たなstreamメソッドが古いコードのコンパイルを失敗させるシナリオを述べなさい。
2. バイナリ互換性についてはどうでしょうか。
3. JARファイルからの古いコードは動作するでしょうか。


